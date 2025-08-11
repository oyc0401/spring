pipeline {
    agent any

    environment {
        DOCKERHUB_USER = 'oyc0401'
        IMAGE_NAME     = 'spring-demo'
        IMAGE_TAG      = 'latest'
        FULL_IMAGE     = "${DOCKERHUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}"
        EC2_HOST = 'ubuntu@43.203.39.226'
        EC2_PATH = '/home/ubuntu'
    }

    stages {

        stage('Build JAR') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker buildx build --platform linux/amd64 -t ${FULL_IMAGE} ."
            }
        }

        stage('Push to DockerHub') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DH_USER',
                        passwordVariable: 'DH_TOKEN'
                    )
                ]) {
                    sh '''
                        echo $DH_TOKEN | docker login -u $DH_USER --password-stdin
                        docker push ${FULL_IMAGE}
                    '''
                }
            }
        }

        stage('Deploy on EC2') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DH_USER',
                        passwordVariable: 'DH_TOKEN'
                    )
                ]) {
                    sshagent(credentials: ['ec2-ssh-key']) {
                        sh """
                            ssh -o StrictHostKeyChecking=no ${EC2_HOST} '
                                echo ${DH_TOKEN} | docker login -u ${DH_USER} --password-stdin &&
                                docker pull ${FULL_IMAGE} &&
                                docker stop app || true &&
                                docker rm app   || true &&
                                docker run -d --name app \
                                    --restart=always \
                                    --env-file ${EC2_PATH}/injob/.env \
                                    -p 8080:8080 ${FULL_IMAGE}
                            '
                        """
                    }
                }
            }
        }
    }
}

pipeline {
    agent any

    environment {
        AWS_REGION = 'ap-northeast-2'
        REPO_NAME = 'demo-app'
        IMAGE_TAG = 'latest'
        ECR_REGISTRY = '<AWS_ACCOUNT_ID>.dkr.ecr.ap-northeast-2.amazonaws.com'
        EC2_HOST = 'ubuntu@3.37.136.153'
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
                sh 'docker build -t ${REPO_NAME}:${IMAGE_TAG} .'
            }
        }

        stage('Push to ECR') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'aws-ecr-creds', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                    sh '''
                        aws configure set aws_access_key_id $AWS_ACCESS_KEY_ID
                        aws configure set aws_secret_access_key $AWS_SECRET_ACCESS_KEY
                        aws configure set region ${AWS_REGION}

                        aws ecr get-login-password --region ${AWS_REGION} | \
                        docker login --username AWS --password-stdin ${ECR_REGISTRY}

                        docker tag ${REPO_NAME}:${IMAGE_TAG} ${ECR_REGISTRY}/${REPO_NAME}:${IMAGE_TAG}
                        docker push ${ECR_REGISTRY}/${REPO_NAME}:${IMAGE_TAG}
                    '''
                }
            }
        }

        stage('Deploy on EC2') {
            steps {
                sshagent(credentials: ['ec2-ssh-key']) {
                    sh """
                        ssh ${EC2_HOST} '
                            aws ecr get-login-password --region ${AWS_REGION} | \
                                docker login --username AWS --password-stdin ${ECR_REGISTRY} &&
                            docker pull ${ECR_REGISTRY}/${REPO_NAME}:${IMAGE_TAG} &&
                            docker stop app || true &&
                            docker rm app || true &&
                            docker run -d --name app \\
                                --env-file ${EC2_PATH}/.env \\
                                -p 8080:8080 ${ECR_REGISTRY}/${REPO_NAME}:${IMAGE_TAG}
                        '
                    """
                }
            }
        }
    }
}

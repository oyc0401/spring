pipeline {
    agent any

    environment {
        EC2_HOST = 'ubuntu@3.37.136.153'       // EC2 퍼블릭 IP 입력
        EC2_PATH = '/home/ubuntu/deploy'
        APP_NAME = 'myapp-0.0.1-SNAPSHOT.jar'   // 빌드 결과물 JAR 이름
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/oyc0401/your-repo.git'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Deploy') {
            steps {
                sshagent(credentials: ['ec2-ssh-key']) {
                    sh """
                        scp build/libs/${APP_NAME} ${EC2_HOST}:${EC2_PATH}/
                        ssh ${EC2_HOST} '${EC2_PATH}/run.sh'
                    """
                }
            }
        }
    }
}

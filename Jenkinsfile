pipeline {
    agent any

    environment {
        JAVA_HOME = "/opt/homebrew/opt/openjdk@17"
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
        EC2_HOST = 'ubuntu@3.37.136.153'
        EC2_PATH = '/home/ubuntu/deploy'
        APP_NAME = 'demo-0.0.1-SNAPSHOT.jar'
    }

    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build -x test'
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

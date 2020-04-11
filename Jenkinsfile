pipeline{
    agent any
    stages{
        stage('Build'){ 
            steps{
                sh 'echo "Start building app"'
                sh 'chmod +x gradlew'
                sh './gradlew clean assemble'
            }  
        }
        stage('Unit Test'){ 
            steps{
                sh 'echo "Running tests"'
                sh 'java -version'
                sh 'git --version'
            }  
        }
        stage('Deploy'){
            parallel {
                stage('DeployToDevEnv'){
                    steps{
                        sh 'echo "Deploying to Dev environment"'
                    }
                }
                stage('DeployToQaEnv'){
                    steps{
                        sh 'echo "Deploying to Qa environment"'
                    }
                }
            }
        } 
        /* stage('Stage For test Post messages'){
            steps {
                sh 'exit 1'
            }

        } */
    }
    environment {
        EMAIL_TEAM = 'juancitopinto236@gmail.com, kenshinmc23@gmail.com, guillermitomc3@gmail.com'
        EMAIL_ADMIN = 'kenshinmc23@gmail.com'
        EMAIL_ME = 'guillermitomc3@gmail.com'
    }
    post {
        always {
            mail to: "${EMAIL_ADMIN}", 
                 subject: "${currentBuild.currentResult} Pipeline: ${currentBuild.fullDisplayName}",
                 body: "The pipeline ${env.BUILD_URL} has been executed."
        }
        failure {
            mail to: "${EMAIL_TEAM}",
                 subject: "${currentBuild.currentResult} Pipeline: ${currentBuild.fullDisplayName}",
                 body: "Something is wrong with ${env.BUILD_URL}"
        }
        success {
            mail to: "${EMAIL_ME}", 
                 subject: "${currentBuild.currentResult} Pipeline: ${env.JOB_NAME}${env.BUILD_NUMBER}",
                 body: "The pipeline ${env.BUILD_URL} has been well executed"
        }
    }
}
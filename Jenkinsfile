pipeline{
    agent any
    stages{
        stage('Build'){ 
            steps{
                sh 'echo "Start building app"'
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
    }
    stage('Stage For test Post messages'){
            steps {
                sh 'exit -1'
            }

        }
    post {
        always {
            echo 'Execute Always message'
        }
        failure {
            mail to: 'juancitopinto236@gmail.com',
                 to: 'kenshinmc23@gamil.com',
                 subject: "The pipeline execute success!!!",
                 body: "The pipeline has been well executed"
        }
        success {
            mail to: 'juancitopinto236@gmail.com', 
                 subject: "The pipeline execute success!!!",
                 body: "The pipeline has been well executed"
        }
    }
}
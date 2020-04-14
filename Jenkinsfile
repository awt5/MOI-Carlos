pipeline{
    agent any
    stages{
        stage ('Deploy MOI'){
            parallel{
                stage('Build'){ 
                    steps{
                        sh 'echo "Start building app"'
                        sh 'chmod +x gradlew'
                        sh './gradlew clean build'
                    }  
                }
                stage('Sonar Scan'){ 
                    steps{
                        sh 'echo "Running SonarQube"'
                        //sh './gradlew sonarqube'
                    }  
                }
            }
        }
    }
    environment {
        EMAIL_TEAM = 'juancitopinto236@gmail.com, kenshinmc23@gmail.com, guillermitomc3@gmail.com'
        EMAIL_ADMIN = 'kenshinmc23@gmail.com'
        EMAIL_ME = 'guillermitomc3@gmail.com'
    }
    post {
        always {
            mail to: "${EMAIL_ADMIN}", 
                 subject: "${currentBuild.currentResult} Pipeline: ${currentBuild.projectName}",
                 body: "The pipeline: ${currentBuild.fullDisplayName}, has been ${currentBuild.currentResult} executed. Description: ${currentBuild.description} More details: ${env.BUILD_URL} ."
        }
        failure {
            mail to: "${EMAIL_TEAM}",
                 subject: "${currentBuild.currentResult} Pipeline: ${currentBuild.fullDisplayName}",
                 body: "Something is wrong with ${env.BUILD_URL}"
        }
        success {
            mail to: "${EMAIL_ME}", 
                 subject: "${currentBuild.currentResult} Pipeline: ${currentBuild.fullDisplayName}",
                 body: "The pipeline ${env.BUILD_URL} has been well executed"
        }
    }
}
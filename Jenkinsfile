pipeline{
    agent any
    stages{
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
                sh './gradlew sonarqube'
            }  
        }
        post {
                always {
                    sh 'touch build/test-results/*.xml'
                    junit 'build/test-results/*.xml'
                }
                success {
                    archiveArtifacts artifacts: 'build/**/*.jar', fingerprint: true
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
                 subject: "${currentBuild.currentResult} Pipeline in ${currentBuild.fullDisplayName}",
                 body: "The pipeline: ${currentBuild.fullDisplayName}, has been ${currentBuild.currentResult} executed. More details: ${env.BUILD_URL} ."
        }
        failure {
            mail to: "${EMAIL_TEAM}",
                 subject: "${currentBuild.currentResult} Pipeline in ${currentBuild.fullDisplayName}",
                 body: "The pipeline: ${currentBuild.fullDisplayName}, has been ${currentBuild.currentResult} executed. More details: ${env.BUILD_URL} ."
        }
        success {
            mail to: "${EMAIL_ME}", 
                 subject: "${currentBuild.currentResult} Pipeline in ${currentBuild.fullDisplayName}",
                 body: "The pipeline: ${currentBuild.fullDisplayName}, has been ${currentBuild.currentResult} executed. More details: ${env.BUILD_URL} ."
        }
    }
}
pipeline{
    agent any
    environment {
        EMAIL_TEAM = 'juancitopinto236@gmail.com, kenshinmc23@gmail.com, guillermitomc3@gmail.com'
        EMAIL_ADMIN = 'kenshinmc23@gmail.com'
        EMAIL_ME = 'guillermitomc3@gmail.com'
    }
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
        stage('PublishReport') {
            steps {
                publishHTML (target: [
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'build/reports/tests/test',
                reportFiles: 'index.html',
                reportName: "MOI-project test Report"
                ])   
            }
        }
    }
    
    post {
        always {
            sh 'touch build/test-results/test/*.xml'
            junit 'build/test-results/test/*.xml'
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
            archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            mail to: "${EMAIL_ME}", 
                 subject: "${currentBuild.currentResult} Pipeline in ${currentBuild.fullDisplayName}",
                 body: "The pipeline: ${currentBuild.fullDisplayName}, has been ${currentBuild.currentResult} executed. More details: ${env.BUILD_URL} ."
        }
    }
}
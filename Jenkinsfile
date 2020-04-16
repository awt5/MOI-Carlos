pipeline{
    agent any
    environment {
        EMAIL_TEAM = 'juancitopinto236@gmail.com, guillermitomc3@gmail.com'
        EMAIL_ADMIN = 'kenshinmc23@gmail.com'
    }
    stages{
        stage('Build'){ 
            steps{
                sh 'echo "Start building app"'
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
            post {
                always{
                    sh 'touch build/test-results/test/*.xml'
                    junit 'build/test-results/test/*.xml'
                    publishHTML (target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'build/reports/tests/test',
                    reportFiles: 'index.html',
                    reportName: "MOI-project test Report"
                    ])
                    publishHTML (target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: "MOI-project test Coverage"
                    ])
                }
                success {
                    archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
                }
            }  
        }
        stage('Sonar Scan'){ 
            steps{
                sh 'echo "Running SonarQube"'
                sh './gradlew sonarqube'
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
    }
}
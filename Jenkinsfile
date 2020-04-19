pipeline{
    agent any
    environment {
        EMAIL_TEAM = 'juancitopinto236@gmail.com, guillermitomc3@gmail.com'
        EMAIL_ADMIN = 'kenshinmc23@gmail.com'
    }
    stages{
        stage('Build'){ 
            steps{
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
            post {
                always{
                    sh 'touch build/test-results/test/*.xml'
                    junit 'build/test-results/test/*.xml'
                    publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: "MOI-project test Report"])
                    publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'build/reports/jacoco/test/html', reportFiles: 'index.html', reportName: "MOI-project test Coverage"])
                }
                success {
                    archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
                }
            }  
        }
        stage('CodeQuality'){ 
            steps{
                sh './gradlew sonarqube'
            }  
        }
        stage('DeployToDev'){
            steps{
                sh 'docker-compose config'
                sh 'docker-compose build'
                sh 'docker-compose up -d'
            }
           
        }
        stage('Publish'){ 
            when {
                branch 'develop'
            }
            steps{
                sh './gradlew artifactoryPublish'
            }         
        }
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
    }
}
pipeline{
    agent any
    environment {
        EMAIL_TEAM = 'juancitopinto236@gmail.com, guillermitomc3@gmail.com'
        EMAIL_ADMIN = 'kenshinmc23@gmail.com'
        PROJECT_NAME = 'moi-project'
        PROJECT_VERS = '1.0'
        USER_DOCKER_HUB = 'carlosmc23'

    }
    stages{
        // stage('Build'){ 
        //     steps{
        //         sh 'chmod +x gradlew'
        //         sh './gradlew clean build'
        //     }
        //     post {
        //         always{
        //             sh 'touch build/test-results/test/*.xml'
        //             junit 'build/test-results/test/*.xml'
        //             publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: "MOI-project test Report"])
        //             publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'build/reports/jacoco/test/html', reportFiles: 'index.html', reportName: "MOI-project test Coverage"])
        //         }
        //         success {
        //             archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
        //         }
        //     }  
        // }
        // stage('Code Quality'){ 
        //     steps{
        //         sh './gradlew sonarqube'
        //     }  
        // }
        // stage('Deploy To Dev'){
        //     steps{
        //         sh 'docker-compose config'
        //         sh 'docker-compose build'
        //         sh 'docker-compose up -d'
        //     }   
        // }
        // stage('Run Acceptance Tests'){
        //     steps{
        //         echo 'Running acceptance test'
        //     }
        // }
        // stage('Publish To Artifactory'){ 
        //     when {
        //         branch 'develop'
        //     }
        //     steps{
        //         sh './gradlew artifactoryPublish'
        //     }
        // }
        stage('Publish To Docker Hub'){ 
            // when {
            //     branch 'develop'
            // }
            steps{
                withDockerRegistry([ credentialsId: "carlosmc23", url: "https://index.docker.io/v1/" ]) {
                    sh 'docker tag ${PROJECT_NAME}:latest ${USER_DOCKER_HUB}/${PROJECT_NAME}:${PROJECT_VERS}'
                    sh 'docker push ${USER_DOCKER_HUB}/${PROJECT_NAME}'
                }
            }
        }
        // stage('Promote To QA'){
        //     steps{
        //         sh 'docker-compose -f docker-compose-qa.yml config'
        //         sh 'docker-compose -f docker-compose-qa.yml build'
        //         sh 'docker-compose -f docker-compose-qa.yml up -d'
        //     }
        // }
        stage('Automation Testing'){
            steps{
                echo 'Running automation test'
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
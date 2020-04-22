pipeline{
    agent any
    environment {
        EMAIL_TEAM = 'juancitopinto236@gmail.com, guillermitomc3@gmail.com'
        EMAIL_ADMIN = 'kenshinmc23@gmail.com'
        PROJECT_NAME = 'moi-project'
        DOCKER_CREDS = 'docker-credis'
        USER_DOCKER_HUB = 'carlosmc23'
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
        stage('Code Quality'){ 
            steps{
                sh './gradlew sonarqube'
            }  
        }
        stage('Deploy To Dev'){
            environment {
                APP_PORT=9092
            }
            steps{
                sh 'docker-compose config'
                sh 'docker-compose build'
                sh 'docker-compose up -d'
            }   
        }
        stage('Run Acceptance Tests'){
            steps{
                echo 'Running acceptance test'
            }
        }
        stage('Publish To Artifactory'){ 
            when {
                branch 'develop'
            }
            steps{
                sh './gradlew artifactoryPublish'
            }
            // when {
            //     branch 'master'
            // }
            // steps{
            //     sh './gradlew -Partifactory_repokey=libs-release-local artifactoryPublish'
            // }
        }
        stage('Publish To Docker Hub'){ 
            when {
                branch 'develop'
            }
            steps{
                withDockerRegistry([ credentialsId: "${DOCKER_CREDS}", url: "https://index.docker.io/v1/" ]) {
                    sh 'docker tag ${PROJECT_NAME}:latest ${USER_DOCKER_HUB}/${PROJECT_NAME}:v1.0-$BUILD_NUMBER'
                    sh 'docker push ${USER_DOCKER_HUB}/${PROJECT_NAME}'
                }
            }
        }
        stage('Promote To QA'){
            environment {
                APP_PORT=9093
                QA_HOME='/home/carlos/awt05/carlos-MOI/deployments/qa'
            }
            // when {
            //     branch 'develop'
            // }
            steps{
                sh 'docker-compose -f $QA_HOME/docker-compose.yml down -v'
                sh 'cp docker-compose.yml $QA_HOME'
                sh 'docker-compose -f $QA_HOME/docker-compose.yml config'
                sh 'docker-compose -f $QA_HOME/docker-compose.yml up -d'
            }
        }
        stage('Automation Testing'){
            when {
                branch 'develop'
            }
            steps{
                echo 'Running automation test'
            }
        }
        // stage('Deploy To Staging'){
        //     environment {
        //         APP_PORT=9094
        //         STG_HOME='/home/carlos/awt05/carlos-MOI/deployments/staging'
        //     }
        //     // when {
        //     //     branch 'develop'
        //     // }
        //     steps{
        //         sh 'cp docker-compose-qa.yml $STG_HOME'
        //         sh 'docker-compose -f $STG_HOME/docker-compose-qa.yml down -v'
        //         sh 'docker-compose -f $STG_HOME/docker-compose-qa.yml config'
        //         sh 'docker-compose -f $STG_HOME/docker-compose-qa.yml up -d'
        //     }
        // }
        // stage('Cleaning WorkSpace'){
        //     environment {
        //         APP_PORT=9092
        //     }
        //     steps{
        //         sh 'docker-compose down -v'
        //         sh 'docker rmi $(docker images -aq -f dangling=true)'
        //         // deleteDir()
        //         // dir("${workspace}@tmp") {
        //         //     deleteDir()
        //         // }
        //     }
        // }
    }
    post {
        always {
            mail to: "${EMAIL_ADMIN}",
                 subject: "${currentBuild.currentResult} Pipeline in ${currentBuild.fullDisplayName}",
                 body: "The pipeline: ${currentBuild.fullDisplayName}, has been executed with the next result: ${currentBuild.currentResult} Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}.\nMore details: ${env.BUILD_URL}."
        }
        failure {
            mail to: "${EMAIL_TEAM}",
                 subject: "${currentBuild.currentResult} Pipeline in ${currentBuild.fullDisplayName}",
                 body: "The pipeline: ${currentBuild.fullDisplayName}, has been executed with the next result: ${currentBuild.currentResult} Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}.\nMore details: ${env.BUILD_URL}."
        }
    }
}


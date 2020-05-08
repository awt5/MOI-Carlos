pipeline{
    agent any
    environment {
        EMAIL_TEAM = 'juancitopinto236@gmail.com, guillermitomc3@gmail.com'
        EMAIL_ADMIN = 'kenshinmc23@gmail.com'
        PROJECT_NAME = 'moi-project'
        PROJECT_VER = '1.0'
        DOCKER_CREDIS = 'docker-credis'
        DOCKER_USER = 'carlosmc23'
    }
    stages{
        stage('Build'){ 
            steps{
                sh 'chmod +x gradlew'
                script {
                    if (env.BRANCH_NAME == 'master') {
                        sh './gradlew -Pmoi_version=${PROJECT_VER} clean build'
                    } else {
                        sh './gradlew clean build'
                    }
                }
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
                APP_PORT=9091
            }
            steps{
                sh 'docker-compose config'
                sh 'docker-compose build'
                sh 'docker-compose up -d'
            }   
        }
        stage('Run Acceptance Tests'){
            steps {  
                build job: "cucumber-demo/jenkinsfile", propagate: true, wait: true,
                parameters: [
                    string(name: 'TAG_NAME', value: "@acceptance"),
                    string(name: 'ENV_NAME', value: "DEV")
                ]   
            }
        }
        // stage('Publish Artifactory SnapshotLibs'){ 
        //     when {
        //         branch 'develop'
        //     }
        //     steps{
        //         sh './gradlew artifactoryPublish'
        //     }
        // }
        // stage('Publish Artifactory ReleaseLibs'){
        //     when {
        //         branch 'master'
        //     }
        //     steps{
        //         sh './gradlew -Pmoi_version=${PROJECT_VER} -Partifactory_repokey=libs-release-local artifactoryPublish'
        //     }
        // }
        stage('Publish DockerHub Develop'){ 
            when {
                branch 'develop'
            }
            steps{
                withDockerRegistry([ credentialsId: "${DOCKER_CREDIS}", url: "https://index.docker.io/v1/" ]) {
                    sh 'docker tag ${PROJECT_NAME}:latest ${DOCKER_USER}/${PROJECT_NAME}:$BUILD_NUMBER'
                    sh 'docker push ${DOCKER_USER}/${PROJECT_NAME}'
                }
            }
        }
        stage('Publish DockerHub Release'){ 
            when {
                branch 'master'
            }
            steps{
                withDockerRegistry([ credentialsId: "${DOCKER_CREDIS}", url: "https://index.docker.io/v1/" ]) {
                    sh 'docker tag ${PROJECT_NAME}:latest ${DOCKER_USER}/${PROJECT_NAME}:${PROJECT_VER}'
                    sh 'docker push ${DOCKER_USER}/${PROJECT_NAME}'
                }
            }
        }
        stage('Promote To QA'){
            environment {
                APP_PORT=9092
                QA_HOME='/deployments/qa'
            }
            when {
                branch 'develop'
            }
            steps{
                sh 'cp docker-compose.yml $QA_HOME'
                sh 'docker-compose -f $QA_HOME/docker-compose.yml down -v'
                sh 'docker-compose -f $QA_HOME/docker-compose.yml up -d'
            }
        }
        stage('Deploy To Staging'){
            environment {
                APP_PORT=9093
                STG_HOME='/deployments/staging'
            }
            when {
                branch 'master'
            }
            steps{
                sh 'cp docker-compose.yml $STG_HOME'
                sh 'docker-compose -f $STG_HOME/docker-compose.yml down -v'
                sh 'docker-compose -f $STG_HOME/docker-compose.yml up -d'
            }
        }
        stage('Automation Testing'){
            when {
                anyOf{
                    branch 'develop'
                    branch 'master'
                }
            }
            steps{  
                build job: "cucumber-demo/jenkinsfile", propagate: true, wait: true,
                parameters: [
                    string(name: 'TAG_NAME', value: " "),
                    string(name: 'ENV_NAME', value: "QA")
                ]    
            }
        }
        stage('Cleaning WorkSpace'){
            environment {
                APP_PORT=9091
            }
            steps{
                sh 'docker-compose down -v'
                //sh 'docker image prune -a'
                // deleteDir()
                // dir("${workspace}@tmp") {
                //     deleteDir()
                // }
                // dir("${workspace}@script") {
                //     deleteDir()
                // }
            }
        }
    }
    post {
        always {
            mail to: "${EMAIL_ADMIN},${EMAIL_TEAM}",
                 subject: "${currentBuild.currentResult} Pipeline in ${currentBuild.fullDisplayName}",
                 body: "The pipeline: ${currentBuild.fullDisplayName}, has been executed with the next result: ${currentBuild.currentResult} Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}.\nMore details: ${env.BUILD_URL}."
        }
    }
}


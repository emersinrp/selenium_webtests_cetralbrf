pipeline {
    agent any
    environment {
        PATH = "/opt/homebrew/Cellar/maven/3.8.3/bin:$PATH"
    }
    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                sh 'echo Clone repositorio Git:'
                // Run Maven on a Unix agent.
                sh 'echo Execucao de testes:'
                sh 'mvn clean install test'

            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
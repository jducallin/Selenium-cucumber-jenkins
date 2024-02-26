pipeline {
    agent any

    stages {

        stage('Clean Stage') {
            steps {
                script {
                    def mvnHome = tool name: 'maven_3_9_6', type: 'maven'
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat "${mvnHome}\\bin\\mvn clean"
                    }
                }
            }
        }

        stage('Compile Stage') {
            steps {
                script {
                    def mvnHome = tool name: 'maven_3_9_6', type: 'maven'
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat "${mvnHome}\\bin\\mvn clean compile"
                    }
                }

            }
        }

        stage('Test Stage') {
            steps {
                script {
                    def mvnHome = tool name: 'maven_3_9_6', type: 'maven'
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                            sh "${mvnHome}/bin/mvn clean verify -Dcucumber.filter.tags=\"@PRUEBA1\"" // Use sh instead of bat for Unix-like systems
                        }
                    }
                }
            }
        }

        stage('Cucumber Reports') {
            steps {
                cucumber buildStatus: "UNSTABLE",
                        fileIncludePattern: "**/cucumber.json",
                        jsonReportDirectory: 'target'
            }
        }

    }

}

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
                        def result = bat(script: "${mvnHome}\\bin\\mvn clean verify -Dcucumber.filter.tags=\"@PRUEBA1\"", returnStatus: true)
                        if (result != 0) {
                            error "Las pruebas han fallado."
                        }
                    }
                }
            }
        }

        stage('Cucumber Reports'){
            steps{
                post {
                    always {
                        cucumber fileIncludePattern: "**/cucumber.json",
                                jsonReportDirectory: 'target'
                    }
                }
            }
        }

    }

}

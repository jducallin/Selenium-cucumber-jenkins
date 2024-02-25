pipeline{

    agent any

    stages{

        steps ('Compile Stage') {
            withMaven ('manven_3_9_6'){
            sh 'mvn clean compile'
            }

        }

    }

    stage ('Test Stage'){
        steps{
            withMaven ('manven_3_9_6'){
                sh 'mvn clean verify -Dcucumber.filter.tags="@PRUEBA1"'
            }

        }

    }

    stage ('Cucumber Reports'){
        steps{
            cucumber buildStatus: "UNSTABLE",
            fileIncludePattern: "**/cucumber.json",
            sonReportDirectory: 'target'
        }
    }

}
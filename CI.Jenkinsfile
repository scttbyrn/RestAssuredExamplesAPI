pipeline {
    agent any

    stages {

        stage('Checkout Test Repo') {
            steps {
                git url: 'https://github.com/scttbyrn/ExtentReport_Parallel.git',
                    branch: 'master'
            }
        }

        stage('Run Smoke') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
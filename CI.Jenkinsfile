pipeline {
    agent any

    parameters {
        choice(name: 'BRANCH_NAME', choices: ['GraphQL', 'OAuth', 'origin/DeserializationPOJO'], description: 'Select branch')
    }

    stages {

        stage('Checkout') {
            steps {
                git url: 'https://github.com/scttbyrn/JenkinsCI-Job-Pipeline-.git',
                    branch: 'master'
            }
        }

        stage('Debug') {
            steps {
                echo "Selected branch: ${params.BRANCH_NAME}"
            }
        }

        stage('GraphQL Smoke') {
            when {
                expression { params.BRANCH_NAME == 'GraphQL' }
            }
            steps {
                bat 'mvn test -PSmoke -Dbrowser=edge'
            }
        }

        stage('OAuth Regression') {
            when {
                expression { params.BRANCH_NAME == 'OAuth' }
            }
            steps {
                bat 'mvn test -PRegression -Dbrowser=chrome'
            }
        }

        stage('Deserialization Sanity') {
            when {
                expression { params.BRANCH_NAME == 'DeserializationPOJO' }
            }
            steps {
                bat 'mvn test -PSanity -Dbrowser=edge'
            }
        }
    }
    
}
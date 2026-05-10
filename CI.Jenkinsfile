pipeline {
    agent any

    stages {

        stage('Checkout Test Repo') {
            steps {
                git url: 'https://github.com/scttbyrn/JenkinsCI-Job-Pipeline-.git',
                    branch: 'master'
                    
                    bat 'mvn clean install -DskipTests'
            }
        }
        
        stage('Debug Branch') {
            steps {
                echo "Current branch is: ${env.BRANCH_NAME}"
            }
        }

        stage('Develop - GraphQL') {
			when {
                expression {
                    env.BRANCH_NAME == 'GraphQL'
                }
            }
            steps {
				echo "Running Smoke Testing..."
                bat 'mvn test -PSmoke -Dbrowser=edge'
            }
        }
        
        stage('QA - OAuth') {
			when {
                expression {
                    env.BRANCH_NAME == 'OAuth'
                }
            }
            steps {
				echo "Running Regression Testing... "
                bat 'mvn test -PRegression -Dbrowser=chrome'
            }
        }
        
        stage('Release - DeserializationPOJO') {
			when {
                expression {
                    env.BRANCH_NAME == 'origin/DeserializationPOJO'
                }
            }
            steps {
				echo "Running Sanity Testing... "
                bat 'mvn test -PSanity -Dbrowser=edge'
            }
        }
    }
}
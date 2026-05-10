/**This Pipeline is work only on this Branch

Note:
- To make this dynamically with auto detect other branches you must use "MultiBranch Pipeline".

There is a sample of MultiBranch Pipelin groovy setup on branch of "OAuth".

**/

pipeline {
	
    agent any

	    stages {
	
	        stage('Checkout Smoke and Regression Repo..') {
	            steps {
	                git url: 'https://github.com/scttbyrn/JenkinsCI-Job-Pipeline-.git',
	                    branch: 'master'
	                    
	                echo "Checkout Automation Test Cases.."
	                    
	                bat 'mvn clean install -DskipTests'
	            }
	        }
	
	       stage('Smoke Tests') {
            	steps {
					
				echo "Running Smoke Tests.."
					
                script {
                    try {
                        bat 'mvn test -PSmoke -Dbrowser=edge'
                        env.SMOKE_STATUS = "PASSED"
                        
                    } catch (err) {
                        env.SMOKE_STATUS = "FAILED"
                        error "Smoke tests failed, stopping pipeline"
                        
                    }
                }
            }
        }

        stage('Regression Tests') {
            when {
                expression { env.REGRESSION_STATUS == "PASSED" }
            }
            steps {
				
				echo "Running Regression Tests.."
				
				script {
                    try {
                        bat 'mvn test -PRegression -Dbrowser=chrome'
                        env.REGRESSION_STATUS = "PASSED"
                        
                    } catch (err) {
                        env.REGRESSION_STATUS = "FAILED"
                        error "Regression tests failed, stopping pipeline"
                        
                    }
                }
                
            }
        }
        
        stage('Sanity Tests') {
			
			when {
                expression { env.SMOKE_STATUS == "PASSED" && env.REGRESSION_STATUS = "PASSED" }
            }
            
            echo "Running Sanity Tests.."

            steps {
                bat 'mvn test -PSanity -Dbrowser=edge'
            }
        }
	
	  }
}
/**This Pipeline is work only on this Branch

Note:
- To make this dynamically with auto detect other branches you must use "MultiBranch Pipeline".

There is a sample of MultiBranch Pipelin groovy setup on branch of "OAuth".

s
**/

pipeline {

    agent any

    environment {
        EMAIL_RECIPIENTS = 'scttsmrfng2@gmail.com, scttsmrfng@gmail.com'
    }

    stages {

        stage('Checkout Smoke and Regression Repo..') {

            steps {

                echo "Checkout Automation Test Cases.."

                git url: 'https://github.com/scttbyrn/ExtentReport_Parallel.git',
                    branch: 'master'

                bat 'mvn clean install -DskipTests'
                 
            }
        }

        stage('Smoke Test') {

            steps {

                echo "Running Smoke Tests.."

                script {

                    try {

                        bat 'mvn test -PSmoke -Dbrowser=edge "-DreportName=SmokeReport"'
                        env.SMOKE_STATUS = "PASSED"

                    } catch (err) {

                        env.SMOKE_STATUS = "FAILED"
                        error "Smoke tests failed, stopping pipeline"

                    }
                    
                    echo "Sending Email Report.."

        emailext(

            subject: "Jenkins Build Report - ${currentBuild.currentResult}",

            body: """
                <h2>Automation Test Execution Result</h2>

                <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                <p><b>Build Status:</b> ${currentBuild.currentResult}</p>
                <p><b>Job Name:</b> ${env.JOB_NAME}</p>

                <h3>Test Status</h3>

                <ul>
                    <li>Smoke Test: ${env.SMOKE_STATUS}</li>
                    
                </ul>

                <p>
                    Check Jenkins Console Output:
                    <a href="${env.BUILD_URL}">
                        Open Build
                    </a>
                </p>
            """,

            mimeType: 'text/html',

            to: "${EMAIL_RECIPIENTS}",

            attachLog: true,

            attachmentsPattern: 'reports/index.html'
        )
                    
                }
            }
        }

        stage('Sprint Regression Test') {

            when {
                expression { env.SMOKE_STATUS == "PASSED" }
            }

            steps {

                echo "Running Regression Tests.."

                script {

                    try {

                        bat 'mvn test -PRegression -Dbrowser=chrome "-DreportName=RegressionReport"'
                        env.SPRINT_REGRESSION_STATUS = "PASSED"

                    } catch (err) {

                        env.SPRINT_REGRESSION_STATUS = "FAILED"
                        error "Regression tests failed, stopping pipeline"

                    }
                    
                    echo "Sending Email Report.."

        emailext(

            subject: "Jenkins Build Report - ${currentBuild.currentResult}",

            body: """
                <h2>Automation Test Execution Result</h2>

                <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                <p><b>Build Status:</b> ${currentBuild.currentResult}</p>
                <p><b>Job Name:</b> ${env.JOB_NAME}</p>

                <h3>Test Status</h3>

                <ul>
                    <li>Sprint Regression Test: ${env.SPRINT_REGRESSION_STATUS}</li>
                    
                </ul>

                <p>
                    Check Jenkins Console Output:
                    <a href="${env.BUILD_URL}">
                        Open Build
                    </a>
                </p>
            """,

            mimeType: 'text/html',

            to: "${EMAIL_RECIPIENTS}",

            attachLog: true,

            attachmentsPattern: 'reports/index.html'
        )
                    
                }
            }
        }

        stage('Full Regression Test') {

            when {
                expression { env.SMOKE_STATUS == "PASSED" && env.SPRINT_REGRESSION_STATUS == "PASSED"}
            }

            steps {

                echo "Running Full Regression Tests.."
                
                script {
					
					 try {

                        bat 'mvn test -PSanity -Dbrowser=edge "-DreportName=SanityReport"'
                        env.FULL_REGRESSION_STATUS = "PASSED"

                    } catch (err) {

                        env.FULL_REGRESSION_STATUS = "FAILED"
                        error "Regression tests failed, stopping pipeline"

                    } 
                    
                    echo "Sending Email Report.."

        emailext(

            subject: "Jenkins Build Report - ${currentBuild.currentResult}",

            body: """
                <h2>Automation Test Execution Result</h2>

                <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                <p><b>Build Status:</b> ${currentBuild.currentResult}</p>
                <p><b>Job Name:</b> ${env.JOB_NAME}</p>

                <h3>Test Status</h3>

                <ul>
                    <li>Full Regression Test: ${env.FULL_REGRESSION_STATUS}</li>
                    
                </ul>

                <p>
                    Check Jenkins Console Output:
                    <a href="${env.BUILD_URL}">
                        Open Build
                    </a>
                </p>
            """,

            mimeType: 'text/html',

            to: "${EMAIL_RECIPIENTS}",

            attachLog: true,

            attachmentsPattern: 'reports/index.html'
        )
                    
					
				}
             
            }
        }
        
    }

    post {

        success {

            echo "Pipeline completed successfully."

        }

        failure {

            echo "Pipeline failed."
 
        }
    }
}
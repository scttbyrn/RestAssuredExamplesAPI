/**This Pipeline is work only on this Branch

Note:
- To make this dynamically with auto detect other branches you must use "MultiBranch Pipeline".

There is a sample of MultiBranch Pipelin groovy setup on branch of "OAuth".

**/

pipeline {

    agent any

    environment {
        EMAIL_RECIPIENTS = 'scttsmrfng2@gmail.com'
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

        stage('Smoke Tests') {

            steps {

                echo "Running Smoke Tests.."

                script {

                    try {

                        bat 'mvn test -PSmoke -Dbrowser=edge DreportName=SmokeReport'
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
                expression { env.SMOKE_STATUS == "PASSED" }
            }

            steps {

                echo "Running Regression Tests.."

                script {

                    try {

                        bat 'mvn test -PRegression -Dbrowser=chrome DreportName=RegressionReport'
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
                expression { env.SMOKE_STATUS == "PASSED" && env.REGRESSION_STATUS == "PASSED"}
            }

            steps {

                echo "Running Sanity Tests.."
                
                script {
					
					 try {

                        bat 'mvn test -PSanity -Dbrowser=edge -DreportName=SanityReport'
                        env.SANITY_STATUS = "PASSED"

                    } catch (err) {

                        env.SANITY_STATUS = "FAILED"
                        error "Regression tests failed, stopping pipeline"

                    } 
					
				}
             
            }
        }
    }

    post {

        always {

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
                        <li>Regression Test: ${env.REGRESSION_STATUS}</li>
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

                attachmentsPattern: 'reports/*.html'
            )
        }

        success {

            echo "Pipeline completed successfully."

        }

        failure {

            echo "Pipeline failed."

        }
    }
}
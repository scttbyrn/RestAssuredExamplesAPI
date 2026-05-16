/**This Pipeline is work only on this Branch

Note:
- To make this dynamically with auto detect other branches you must use "MultiBranch Pipeline".

There is a sample of MultiBranch Pipelin groovy setup on branch of "OAuth".

**/



pipeline {

    agent any

    environment {
        EMAIL_RECIPIENTS = 'scttsmrfng2@gmail.com, scttsmrfng@gmail.com'
    }

    stages {

        stage('Checkout Smoke Repo') {

            steps {

                echo "Checkout Smoke Automation Test Cases.."

                git url: 'https://github.com/scttbyrn/ExtentReport_Parallel.git',
                    branch: 'master'

                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Smoke Test') {

            steps {

                script {

                    try {

                        echo "Running Smoke Tests.."

                        bat 'mvn test -PSmoke -Dbrowser=edge "-DreportName=SmokeReport"'

                        env.SMOKE_STATUS = "PASSED"

                    } catch (err) {

                        env.SMOKE_STATUS = "FAILED"

                    } finally {

                        echo "Sending Smoke Email Report.."

                        emailext(

                            subject: "Smoke Test Report - ${env.SMOKE_STATUS}",

                            body: """
                                <h2>Smoke Test Execution Result</h2>

                                <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                                <p><b>Job Name:</b> ${env.JOB_NAME}</p>
                                <p><b>Status:</b> ${env.SMOKE_STATUS}</p>

                                <p>
                                    <a href="${env.BUILD_URL}">
                                        Open Jenkins Build
                                    </a>
                                </p>
                            """,

                            mimeType: 'text/html',

                            to: "${EMAIL_RECIPIENTS}",

                            attachLog: true,

                            attachmentsPattern: 'SmokeRun/SmokeReport.html'
                        )

                        if (env.SMOKE_STATUS == "FAILED") {
                            error "Smoke Tests Failed"
                        }
                    }
                }
            }
        }

        stage('Checkout Regression Repo') {

            when {
                expression { env.SMOKE_STATUS == "PASSED" }
            }

            steps {

                echo "Checkout Regression Automation Test Cases.."

                git url: 'https://github.com/scttbyrn/ExtentReport_Parallel.git',
                    branch: 'JenkinsCIwithEmailReport'

                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Sprint Regression Test') {

            when {
                expression { env.SMOKE_STATUS == "PASSED" }
            }

            steps {

                script {

                    try {

                        echo "Running Regression Tests.."

                        bat 'mvn test -PRegression -Dbrowser=chrome "-DreportName=RegressionReport"'

                        env.SPRINT_REGRESSION_STATUS = "PASSED"

                    } catch (err) {

                        env.SPRINT_REGRESSION_STATUS = "FAILED"

                    } finally {

                        echo "Sending Regression Email Report.."

                        emailext(

                            subject: "Regression Test Report - ${env.SPRINT_REGRESSION_STATUS}",

                            body: """
                                <h2>Regression Test Execution Result</h2>

                                <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                                <p><b>Job Name:</b> ${env.JOB_NAME}</p>
                                <p><b>Status:</b> ${env.SPRINT_REGRESSION_STATUS}</p>

                                <p>
                                    <a href="${env.BUILD_URL}">
                                        Open Jenkins Build
                                    </a>
                                </p>
                            """,

                            mimeType: 'text/html',

                            to: "${EMAIL_RECIPIENTS}",

                            attachLog: true,

                            attachmentsPattern: 'RegressionRun/RegressionReport.html'
                        )

                        if (env.SPRINT_REGRESSION_STATUS == "FAILED") {
                            error "Regression Tests Failed"
                        }
                    }
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

        always {
            echo "Pipeline execution finished. "
        }
    }
}


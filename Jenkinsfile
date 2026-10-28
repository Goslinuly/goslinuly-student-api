pipeline {
    agent any

    tools {
        jdk 'jdk17'
    }

    environment {
        STUDENT_NAME = 'Goslinuly'
        STUDENT_ID   = 'gosling'
        IMAGE_NAME   = 'goslinuly-student-api'
        IMAGE_TAG    = 'v1.1-gosling'
    }

    triggers {
        pollSCM('H/2 * * * *')
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Student: ${STUDENT_NAME} (Goslinuly, ID ${STUDENT_ID})"
                checkout scm
                sh 'git log -1 --format="Commit %h by %an: %s"'
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean bootJar -x test --no-daemon'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
            }
        }

        stage('Verify') {
            steps {
                sh 'docker image inspect ${IMAGE_NAME}:${IMAGE_TAG} --format "Image {{.RepoTags}} created {{.Created}} size {{.Size}} bytes"'
                sh 'docker images ${IMAGE_NAME}'
                withCredentials([string(credentialsId: 'goslinuly-db-password', variable: 'DB_PASSWORD')]) {
                    sh 'echo "DB password loaded from Jenkins Credentials: $DB_PASSWORD (${#DB_PASSWORD} chars)"'
                }
                echo "Image ${IMAGE_NAME}:${IMAGE_TAG} for ${STUDENT_NAME} is ready"
            }
        }
    }

    post {
        success {
            echo "SUCCESS: pipeline of ${STUDENT_NAME} finished"
        }
        failure {
            echo "FAILURE: pipeline of ${STUDENT_NAME} stopped - see the failed stage above"
        }
    }
}

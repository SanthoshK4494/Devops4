pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Check out the code from the Git repository
                script {
                    git credentialsId: 'your-git-credentials-id', url: 'https://github.com/your-username/your-repo.git'
                }
            }
        }
        
        stage('Build') {
            steps {
                // Build the Maven project
                script {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Test') {
            steps {
                // Run tests (assuming Maven test phase)
                script {
                    sh 'mvn test'
                }
            }
        }

        stage('Deploy') {
            steps {
                // Deploy the application (example: deploy to Tomcat)
                script {
                    sh 'mvn tomcat7:redeploy'
                }
            }
        }
    }
    
    post {
        success {
            // Actions to be performed on successful build
            echo 'Deployment successful!'
        }
        failure {
            // Actions to be performed on build failure
            echo 'Build failed. Notify the team.'
        }
    }
}

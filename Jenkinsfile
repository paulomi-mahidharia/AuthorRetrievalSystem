pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
		sh "mvn -f AuthorRetrival/pom.xml clean install -DskipTests"
	
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
		sh "mvn -f AuthorRetrival/pom.xml test"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
		sh "mvn -f AuthorRetrival/pom.xml clean install"
            }
        }
    }
}

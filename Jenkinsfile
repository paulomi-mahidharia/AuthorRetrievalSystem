pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
		sh "mvn -f AuthorRetriever/pom.xml clean install -DskipTests"
	
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
		sh "mvn -f AuthorRetriever/pom.xml test"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
		sh "mvn -f AuthorRetriever/pom.xml clean install"
	
            }
        }
    }
}

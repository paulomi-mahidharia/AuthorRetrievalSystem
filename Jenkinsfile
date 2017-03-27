pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
		sh "mvn -f dblp/pom.xml clean install -DskipTests"
	
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
		sh "mvn -f dblp/pom.xml test"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
		sh "mvn -f dblp/pom.xml clean install"
            }
        }
    }
}
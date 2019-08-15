pipeline {
	agent  any

	parameters {
		choice(name: 'browser',
			   choices: 'All\nChrome\nFirefox',
			   description: 'In which browser the tests will run? All, Chrome or Firefox?')
	}

	options {
        ansiColor('xterm')
      }

    environment {
        MAVEN_OPTS = '-Djansi.force=true'
    }

	stages {
		stage('Prepare') {
			steps {
				checkout scm
				sh 'ls -la'
			}
		}

		stage('Run tests') {
			steps {
					sh "/Applications/apache-maven-3.6.1/bin/mvn test -Denv.BROWSER=${params.browser}"
			}
		}

		stage('Build Reports') {
            steps {
            script {
                    allure([
                            includeProperties: false,
                            jdk: '',
                            properties: [],
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'target/allure-results']]
                    ])
            }
            }
        }
        
        stage('Clear Reports Folder') {
        	steps {
        		sh 'rm -rf target/allure-results'
        	}
        }
	}
	post {
		always {
			echo 'Cleaning Workspace'
		}
		success {
			echo 'Successfully!'
		}
		failure {
			echo 'Failed!'
		}
		unstable {
			echo 'This will run only if the run was marked as unstable'
		}
		changed {
			echo 'This will run only if the state of the Pipeline has changed'
		}
	}
}

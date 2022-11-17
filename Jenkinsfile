pipeline {
    agent any
    tools {
    maven 'Maven-3.8.6'
    }
      stages {
        stage('Build') {
            steps {
                echo 'TODO: build install'
                sh 'mvn clean install'
            }
        }
        stage('Package') {
            steps {
                echo 'TODO: package'
                sh 'mvn clean package -e'           
            }
        }
       // stage('Sonar') {
       //     steps {
      //           script {      
       //         withSonarQubeEnv('Sonar') {
       //         sh 'mvn clean package sonar:sonar -Dsonar.projectKey=ejemplo-nexus -Dsonar.java.binaries=build'
       //            }
       //         }
       //     }
      //  }
	stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                   nexusPublisher nexusInstanceId: 'Nexus-Repository', nexusRepositoryId: 'devops-usach-nexus', 
			packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${workspace}/build/DevOpsUsach2020-0.0.1.jar"]],
		        mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.2']]]
                }
            }
        }      
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }
    }
}

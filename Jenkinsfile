pipeline {
    agent any
    tools {
        maven 'Maven-3.8.6'
        gradle 'Gradle'
    }
	parameters{
		choice choices: ['maven','gradle'], name: 'Build_Tool'
	    booleanParam 'PushNexus'
	}
    stages {
        stage('Maven') {
            when {
				expression{
					params.Build_Tool == 'maven'
				}
			}
			steps {
                script {
                    def script_mvn = load 'maven.groovy'
                    script_mvn.mvn_build()
                    script_mvn.mvn_sonar()
                    script_mvn.mvn_test()
                    script_mvn.mvn_package()
                    script_mvn.mvn_run()
                }
            }
        }
		stage('Gradle') {
            when {
				expression{
					params.Build_Tool == 'gradle'
				}
			}
			steps {
                script {
                    def script_gradle = load 'gradle.groovy' 
                    script_gradle.grdl_compile()
                    script_gradle.grdl_run()
                    script_gradle.grdl_test()
                }
            }
        }
        stage('Push to Nexus') {
           when {
				expression{
					params.PushNexus
				}
			}
		   steps {
                nexusPublisher nexusInstanceId: 'NexusServer', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar"]], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.2']]]
            }
        } 
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }
    }
}

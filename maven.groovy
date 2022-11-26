def mvn_build() {
    stage('Build'){
        sh 'mvn clean install -e'
    }
}
def mvn_sonar() {
    stage('Sonar'){
        withSonarQubeEnv('SonarTest') {
            sh 'mvn clean package sonar:sonar -Dsonar.projectKey=build-maven -Dsonar.java.binaries=build'
        }
    }
}
def mvn_test() {
    stage('Test'){
        sh 'mvn clean test -e'
    }
}
def mvn_package() {
    stage('Package'){
        sh 'mvn clean package -e'
    }
}
def mvn_run() {
    stage('Run'){
        sh 'nohup bash mvn spring-boot:run &'
    }
}

return this;

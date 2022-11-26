def grdl_compile() {
    stage('Compile'){
        sh 'gradle build'
    }
}

def grdl_run() {
    stage('Run'){
        sh 'gradle bootRun &'
    }
}

def grdl_test() {
    stage('Test'){
        sh 'curl -X GET \'http://nexus:8081/rest/mscovid/test?msg=testing\''
    }
}

return this;

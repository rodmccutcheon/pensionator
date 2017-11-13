node {
    def commit_id

    stage('configure') {
        env.PATH = "${tool 'Maven-3.5.2'}/bin:${env.PATH}"
    }

    stage('compile') {
        checkout scm
        commit_id = sh(script: 'git rev-parse --short HEAD', returnStdout: true)
        //sh "git rev-parse --short HEAD > .git/commit-id"
        //commit_id = readFile('./git/commit-id').trim()
        echo commit_id
    }

    stage('test') {

    }

    stage('docker build/push') {
        docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
            def app = docker.build("rodmccutcheon/pensionator:${commit_id}", '.').push()
        }
    }
}
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                sh './jenkins/scripts/deliver.sh'
            }
        }
    }
}

/*node {
    def commit_id

    stage('configure') {
        env.PATH = "${tool 'Maven-3.5.2'}/bin:${env.PATH}"
    }

    stage('compile') {
        //git 'https://github.com/rodmccutcheon/pensionator.git'
        // shortcut to checkout from where this Jenkinsfile is hosted
        checkout scm

        commit_id = sh(script: 'git rev-parse --short HEAD', returnStdout: true)
        echo commit_id
    }

    stage('test') {
        sh "docker-compose up -d"
    }

    stage('docker build/push') {
        docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
            def app = docker.build("rodmccutcheon/pensionator:${commit_id}", '.').push()
        }
    }
}*/
pipeline {
    agent any
    
    tools {
        maven '3.9.9' // Đặt đúng version Maven đã cài trong Jenkins
    }

    environment {
        GIT_REPO = 'https://github.com/namhoang27/API_proj.git' // Đường dẫn kho GitHub của bạn
        BRANCH = 'main' // Nhánh Git cần build
    }

    stages {
        stage('Checkout') {
            steps {
                // Clone repository từ GitHub
                git branch: "${BRANCH}", url: "${GIT_REPO}", credentialsId: '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'
            }
        }
        stage('Build') {
            steps {
                // Chạy Maven build và verify
                sh 'mvn clean verify'
            }
        }
        stage('Generate Cucumber HTML Report') {
            steps {
                script {
                    // Run Maven goal to generate the HTML report
                    sh 'mvn verify -DskipTests=true'
                }
            }
        }
        stage('Publish Reports') {
            steps {
                // Đảm bảo cấu hình để Maven sinh ra báo cáo
                publishHTML(target:[
                reportDir: 'target/cucumber-html-reports',
                reportFiles: 'overview-features.html',
                reportName: 'Cucumber HTML Report'])
            }
        }
    }
    post {
        success {
            echo 'Build và kiểm thử thành công!'
        }
        failure {
            echo 'Build thất bại.'
        }
    }
}

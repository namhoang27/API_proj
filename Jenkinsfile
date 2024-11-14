pipeline {
    agent any
    
    tools {
        maven 'Maven 3.9.9' // Đặt đúng version Maven đã cài trong Jenkins
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
        stage('Publish Reports') {
            steps {
                // Đảm bảo cấu hình để Maven sinh ra báo cáo
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: true,
                             keepAll: true,
                             reportDir: 'target/cucumber-reports/cucumber-html-reports/',
                             reportFiles: 'report-feature_1626635988.html',
                             reportName: 'HTML Report'])
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

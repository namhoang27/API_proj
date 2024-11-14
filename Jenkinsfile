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
    }
    post {
        always {
            // Archive the generated HTML report in Jenkins
            publishHTML (target: [
                            allowMissing: false,
            alwaysLinkToLastBuild: false,  // Thiết lập `false` để lưu báo cáo cho từng bản build
            keepAll: true,    
                reportDir: 'target/cucumber-reports/cucumber-html-reports',
                reportFiles: 'overview-features.html',
                reportName: 'Cucumber HTML Report'
            ])
        }
        success {
            echo 'Build và kiểm thử thành công!'
        }
        failure {
            echo 'Build thất bại.'
        }
    }
}

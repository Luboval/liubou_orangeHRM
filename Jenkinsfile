pipeline {
    agent any            // Агент: где выполнять pipeline (any – на любом доступном узле, в нашем случае на самом Jenkins)
    tools { 
        maven "apache-maven-3.9.9"   // Указываем установленный Maven (имя должно совпадать с настроенным в Global Tools)
    }
    parameters {
        gitParameter branch: 'BRANCH',
        type: 'BRANCH',
        defaultValue: 'main',
        description: 'Выберите ветку для сборки'
        }
    stages {
        stage('Build') {
            steps {
                echo 'Starting build...'
                // Сборка проекта (компиляция и упаковка без запуска тестов):
                powershell 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                // Запускаем тесты Maven (TestNG + RestAssured):
                powershell 'mvn clean test'
            }
            // Мы добавим блок post чуть позже для публикации отчёта Allure
            post {
                  // Генерируем Allure-отчет независимо от статуса тестов:
                  always {
                       allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
                  }
            }
        }
        // stage('Deploy') будет добавлен после настройки тестов и отчетов
                //stage('Deploy') {
                    //when {
                        // Выполнять деплой только если предыдущие стадии успешны:
                      //  expression { currentBuild.currentResult == 'SUCCESS' }
                   // }
                  //  steps {
                    //    echo 'Deploying application...'
                        // Пример: деплой артефакта на удалённый сервер или репозиторий
                     //   sh 'mvn clean deploy'
                        // В реальности тут могут быть команды SCP, SSH или вызов API деплоя
                  //  }
               // }
    }
}
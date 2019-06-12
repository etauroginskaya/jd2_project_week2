Для запуска программы необходимо внести изменения в документ jdbc.properties, расположенный по следующему пути: 
jd2_project_week2\repository-module\src\main\resources\...
Необходимо изменить на используемые database.username и database.password.
В работе программы используется БД MySQL. 
Для запуска программы необходима БД с именем 'test'. После запуска таблица 'document' будет пересоздана. 

Git:
1. Создать Git проект jd2_project_week2
2. Проинициализировать проект 
3. Создать ветку develop
4. Написать проект
5. Внести изменения в ветку develop c соответствующим комментарием
6. Сделать pull request в ветку master
7. Прислать ссылку на pull request

Проект:
1. Создать многомодульный проект, состоящий из трех jar подпроектов: web-module/service-module/repository-module
2. Создать зависимость service-module oт repository-module
3. Создать зависимость web-module oт service-module
4. Создать и реализовать интерфейс в модуле web-module  
public interface DocumentController {
    DocumentDTO add(DocumentDTO documentDTO);
    DocumentDTO getDocumentById(Long id);
    void delete(Long id);
}
5. Создать и реализовать необходимые сервисы и репозитории в модулях service-module и repository-module
6. Для подключения к базе данных использовать JDBC
7. Требование к модели в базе данных:
	- id
	- unique_number(использовать UUID класс для генерации значений)
	- descrition(не более 100 символов)
8. Валидация на уровне DocumentController
9. Конвертация в сущность в базе на уровне service-module
10. Работа с базой данных на уровне repository-module
11. Создать в web-module метод main с позитивным flow для работы с методами интерфейса
12. Написать на уровне service-module Advice, который логирует время старта и время окончания работы каждого метода и вычисляет продолжительность метода.
13. Покрыть тестами DocumentController

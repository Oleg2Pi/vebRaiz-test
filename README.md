# VebRaiz-test
### Тестовое задание
Приложение представляет собой микросервис, который имеет следующие эндпоинты по запросу http://localhost:8080/
* POST /users - создать пользователя
* GET /users/{id} - получить информацию о пользователе
* PUT /users/{id} - обновить пользователя
* DELETE /users/{id} - удалить пользователя
* POST /users/{id}/subscriptions - добавить подписку
* GET /users/{id}/subscriptions - получить подписки пользователя
* DELETE /users/{id}/subscriptions/{sub_id} - удалить подписку
* GET /subscriptions/top - получить ТОП-3 популярных подписок

Для запуска приложения необходимо запустить docker-compose с помощью команды: <code>docker-compose up --build -d</code>
после чего можно воспользоваться эндпоинтами.

Также, при переходе на http://localhost:8080/swagger-ui/index.html можно увидеть все эндпоинты и протестировать их.

Логирование происходит в отельные файлы (app.log)
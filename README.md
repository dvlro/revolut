# revolut

MoneyAPP

GET http://localhost:8080/rest/account/all
GET http://localhost:8080/rest/account/{email}
POST http://localhost:8080/rest/account/add/{email}/{amount}
PUT http://localhost:8080/rest/account/update/{email}/{amount}
PUT http://localhost:8080/rest/account/transfer/{email1}/{amount}/{email2}
DELETE http://localhost:8080/rest/account/delete/{email}

---------------

Run the application with ./gradlew appRun


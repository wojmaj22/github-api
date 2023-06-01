# Github-api

Project provides simple REST endpoint which generates data about user repositories and their branches using GitHub api.
## Installing / Getting started
Since this project is just a demo you can run with either with maven, by typing this into terminal while in project folder:
```shell
./mvn spring-boot:run
```
Or just open in your IDE and then run project manually.
## Initial configuration
Configuration is not needed to use this app.
## Features 
App provides one endpoint: "/api/{username}" which gives info about given user from GitHub api.  
* You need to specify header "Accept: application/json".  
* If user does not exist you will be given '404' error response.  
* If header "Accept: application/xml" you will be given '406' not acceptable response.
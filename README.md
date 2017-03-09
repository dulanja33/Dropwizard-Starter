# Introduction

The Dropwizard example application.

# Overview

Included with this application is an example of the optional DB API module. The examples provided illustrate a few of
the features available in [Hibernate](http://hibernate.org/), along with demonstrating how these are used from within
Dropwizard.

# Steps

Steps are setted in different branches.

ex - git checkout step1

* step1 - Initial work , Up and runnnig dropwizard application 
* step2 - Rest API implementation
* step3 - Add DAO layer with hibernate support with transaction and DB migration
* step4 - Authentication and Authorization
* step5 - filtering
* step6 - Health check, Logs
* step7 - Integrate swagger

# Running The Application

To test the example application run the following commands.

* To package the example run the following from the root dropwizard directory.

        ./gradlew shadowjar

* To setup the h2 database run.

        java -jar build/libs/dropwizard-KT-1.0.0-all.jar db migrate config.yml

* To run the server run.

        java -jar  build/libs/dropwizard-KT-1.0.0-all.jar server config.yml

* API will be expose in 

	http://localhost:8080/v1/order
	
* Swagger UI
   
  http://localhost:8080/swagger/ui

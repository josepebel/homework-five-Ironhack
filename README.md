# Poke-app

> Full stack app made with Angular & Boostrap and Spring Boot & MySQL

![demo](https://github.com/ES-IH-WDPT-JUN21/Homework-5-Devs-Dragons/blob/master/images/demo.gif)

> "For this project, we will make a Pokemon app! in this app, we will have 3 sections, **Pokedex**, **Teams** and **Trainers**. In Pokedex, we will be presented with a paginated list of all pokemon names, and when clicking a name a detailed view will be shown with an image, stats, and a Pokedex entry from the selected pokemon, it will be a good source of knowledge if you aren’t familiar with the pokemon names. In Trainers, we will be able to create and delete pokemon trainers with name, age, hobby and photo. In Teams, we will be able to select a trainer and see their pokemon team and add/remove pokemon to that team."

## Main features: 

- **Welcome page**
![welcome](https://github.com/ES-IH-WDPT-JUN21/Homework-5-Devs-Dragons/blob/master/images/welcomepage.png)

- **Pokedex page**: using the [PokéAPI](https://pokeapi.co/) it shows a paginated list of pokemon. When clicking on a pokemon it renders a detailed card
![pokedex](https://github.com/ES-IH-WDPT-JUN21/Homework-5-Devs-Dragons/blob/master/images/pokedex.png)

- **Trainers page**: form to add new trainers and trainers list
![trainers](https://github.com/ES-IH-WDPT-JUN21/Homework-5-Devs-Dragons/blob/master/images/trainers.png) 

- **Teams page**: it lets the user add up to 6 pokemon to a trainer's team
![teams](https://github.com/ES-IH-WDPT-JUN21/Homework-5-Devs-Dragons/blob/master/images/teams.png)
![teams2](https://github.com/ES-IH-WDPT-JUN21/Homework-5-Devs-Dragons/blob/master/images/teams2.png)

## Project setup: 

To run this project locally do the following after cloning it:

### Frontend:

- Run ```npm i``` to install node dependencies
- Run ```ng serve``` to start project on ```localhost:4200```


### Backend:

We decided to split the backend app into the following microservices:

- Eureka service: runs on port:8761
- Edge service: runs on port:8080
- Team service: runs on port:8081
- Trainer service: runs on port:8082
![services](https://github.com/ES-IH-WDPT-JUN21/Homework-5-Devs-Dragons/blob/master/images/services.png)


**Prerrequisites:**

Don't forget to setup your own SQL server and seed the database with this [SQL script](https://github.com/ES-IH-WDPT-JUN21/Homework-5-Devs-Dragons/blob/master/backend-app/resources/database.sql). All our microservices will use the same database, called *pokemon*. Make sure you have the same values you'll find in the application.properties file of each microservice or properly change them according to your configuration.

- Open each service folder separately

- Run ```mvn spring-boot:run```


❗️ It is important that you start first the eureka service project so the other microservices work


## API Endpoints 

| Service | Method | Endpoint              | Response / description                                           | 
| ------ | ------ | --------------------- | -------------------------------------------------------- | 
| Trainers    | GET         | ```/trainers  ```             | shows list of all trainers | 
| Trainers    | POST         | ```/trainers  ```             | shows data of the new created trainer | 
| Trainers    |  GET  | ```/trainers/{id}```  | retrieved data from a specific trainer (id is the name of the trainer)  | 
| Trainers    |  DELETE  | ```/trainers/{id}```  | removes a specific trainer from the db (id is the name of the trainer)  | 
| Teams    |  GET  | ```/pokemons/{name}```  | shows list of a trainer's team of pokemon  | 
| Teams    |  POST  | ```/pokemons/{name}```  | adds pokemon to a trainer's team  | 
| Teams    |  DELETE  | ```/pokemons/{id}```  | removes pokemon from a trainer's team | 


## Requirements: 

- Include a Java/Spring Boot backend and an Angular frontend.
- We will use this API to retrieve the info of every pokemon.
- Trainer and team info must be stored in your backend DB. 
- In the Teams section, when typing a pokemon name to add it to a team you must make the field case insensitive and make it submit the pokemon name when pressing enter as an alternative to pressing the add button.
- A pokemon Team can only hold up to 6 pokemon, if trying to add more you must display a warning message and refuse the operation.
- If you type a name that doesn’t belong to a pokemon you must display an error message.
- Hovering the pokemon cards (in Teams and Pokedex) and trainer cards (in trainers) must make them change color
- In Teams, the pokemon card must contain the image, name, and types of the pokemon and have a button to toggle between displaying its stats (attack, defense, speed, etc) or not.
- In Pokedex, you must display a simple list with buttons to cycle between pages from up to 20 pokemon and when clicking a name you must display a detailed view with any English Pokedex entry (everything is provided in the Pokemon API).
- Include adequate and complete documentation of your backend API in the README.md file.


## Made by

- [Bruno Álvarez](https://github.com/brunoalvarezlopez)
- [Pilar María Carranza](https://github.com/pilicarranza)
- [Sergio Mateos](https://github.com/SergioMateosSanz)
- [José Antonio Peño](https://github.com/josepebel)
- [Sandra del Pozo](https://github.com/Pitsbows)
- [Gema Segarra](https://github.com/gemasegarra)

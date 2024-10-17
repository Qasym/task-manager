# Description
This is my implementation of a CRUD (create, read, update, delete) application
The implementation utilized Spring MVC, thus it contains all the code that handles the front end 
part as well

Feel free to edit the looks and/or functionality of this application

# Requirements

- Java 17
- Mongo DB host
    - It can be done locally using mongod
    - Or you can create a db online using Mongo Atlas
---
- Opening this project with Intellij Idea would be a good option as I developed this project using that IDE
- If you decide to use Mongo Atlas and host db on a remote server, don't forget to change the `application.properties` file

# How to test
1. Clone the repo
2. Make sure you have MongoDB installed [guide I used](https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-os-x/)
    - I you did not go with the default settings, make sure to change the `application.properties` file accordingly
3. Open the cloned repo using Intellij Idea and run the `SpringApplication.kt`

You should be able to see things running app on http://localhost:8989/

# Future todos
- [x] ~~Add user authentication~~
- [ ] Beautify front end part of the app
- [ ] Categories for the tasks
- [ ] Priorities for the tasks

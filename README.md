# math-app-client

# What is the purpose of this application?
The main purpose of this application is to perform mathematical calculations and return the data back to the user.<br/><br/>
The program consists of 2 parts - the server and the client:<br/>
math-app-server 👉🏻 [click here to see the source code on GitHub](https://github.com/itsmechelly/math-app-server)
<br/>
math-app-client 👉🏻 [click here to see the source code on GitHub](https://github.com/itsmechelly/math-app-client)
<br/>
[CLICK HERE](http://math-app-ui.s3-website-eu-west-1.amazonaws.com/) to see the website!<br/>
<br/><br/>

To write this project I used Java Networking Programming tools (java.net) and it's designed to handle several math clients, meaning, it is a multi-threaded system.
<br/><br/>
For this purpose, I used Java's Thread Object and implemented the singleton pattern as only 1 server should exist per application.
In addition, throughout the program I used additional design patterns such as Factory pattern and Mediator pattern.
<br/><br/>
Plus, on the client server, I added a thread that listens to all input from the server, logs INFO messages and documents data to a local log file.
<br/><br/>
I wrote this program inspired by a good friend of mine who is very close to my heart.
He is a former lecturer at John Bryce College, he helped me a lot and was by my side in my first steps in the programming field, he is the ultimate mentor, and I will always be grateful to him.
<br/><br/>
Okay, so... shall we dive into more details?

# How it works?

## math-app-server
I will be covering here the main purpose of each class, extra details about each method can be found in the source code.<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230099021-ae514210-251e-4207-938a-707fd483eb8f.png)

## App class:
This class is the starting point of the math-app-server, this class contain a method with a reference to MathServer class that will be generated an instance of a ServerSocket

## MathServer class:
Designed to handle multiple math clients, implements the singleton pattern as only 1 server should exist per application.<br/>
This class instantiates different threads who handle different clients.

## MathOperationsThread class:
Designed to provide services for a single math-app-client.<br/>
This class is the one the communicates de facto with math-app-client.<br/>
-	has a method named eventLoop() – that will be call the MathOperationFactory in runtime.<br/><br/>

![image](https://user-images.githubusercontent.com/60425986/230099340-0d73498d-38d7-4eb5-9104-6df370ac60c9.png)

## MathOperationFactory class:
Implementing the factory pattern, responsible for identifying math operations that received from the math-app-client and creating the right object accordingly.

## MathOperation class:
MathOperation class is an abstract class.<br/>
The main method in this class is named perform(), this method parses commands received from the math-app-client.<br/>
This class will be extended by all the command types that exist in this system (see more details below).<br/>
Each one of those sub-classes will be performing and using the methods in this abstract class, and must implement the actual calculation parsenCalc(String n1, String n2) method.<br/>

## Addition class:
The Addition class extends MathOperation abstract class, the main method in use here is the one that performs the calculation de facto - parseAndCalc(String n1, String n2).

## Subtraction class:
The Subtraction class extends MathOperation abstract class, the main method in use here is the one that performs the calculation de facto - parseAndCalc(String n1, String n2).

## Multiplication class:
The Multiplication class extends MathOperation abstract class, the main method in use here is the one that performs the calculation de facto - parseAndCalc(String n1, String n2).

## Division class:
The Division class extends MathOperation abstract class, the main method in use here is the one that performs the calculation de facto - parseAndCalc(String n1, String n2).<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230099692-a6cf944d-386a-413b-a2e8-cee177990cde.png)

## More classes:
I also added class that contain constant values and 2 custom exception classes.
<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230099787-325d0e1a-f4fc-479d-a89c-4661e559e3c0.png)
![image](https://user-images.githubusercontent.com/60425986/230099816-d3b8f322-472c-413e-82c7-eafaac8b5680.png)

## math-app-client
I will be covering here the main purpose of each class, extra details about each method can be found in the source code.
<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230100855-f32790a2-b760-4f3e-978c-a1251132ea4a.png)

## App class:
This class is the starting point of the math -app-client, this class contains a method with a reference to MathOperationClient class that will generate a Socket.

## MathOperationClient class:
MathOperationClient class, designed to enable sending requests from the client to the server.<br/>
This class is the one the communicates de facto with math-app-server, an object of the class should be instantiated in order to connect to the server.

## ServerListeningThread class:
This class is designed to perform as a log file manager.<br/>
I added a thread that listens to all input from the server, logs INFO messages and documents data to a local log file.

## MathClientIOMediator class:
This class implements the Mediator pattern, synchronizes the IO operations between logging threads and MathOperationClient.
<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230101106-1e096238-2162-413d-af6e-612f1538b2af.png)
<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230101184-634bc055-588c-4537-85b0-0dca3c7dca31.png)

## ProtocolConstants classe:
I also added class that contain constant values:
<br/><br/>
![image](https://user-images.githubusercontent.com/60425986/230101281-9118a6b4-470b-43d3-b7ac-73827da7266d.png)


<br/>
Thanks for reading,
<br/>
Chelly 👩🏻‍💻




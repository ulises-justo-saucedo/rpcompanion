## RPCompanion
A Java desktop application made to help roleplayers store their OC's (Original Characters).

## Tools
RPCompanion is built with:
- Java
- Swing
- JDBC

## RPCompanion workflow  
Creation of the database -> Creation of database tables -> Stablish connection with database -> Show UI built in Swing -> Receive and process request made from the UI.  
Some of mentioned steps are optional. If RPCompanion detects that the database or the corresponding tables are already created, it doesn't try to delete and create them again. Instead, just ignores those steps and just stablish the connection.  
Since with JDBC you have to build everything from the ground, the connection, queries, repositories, services and mapping of Java objects is made from zero.  
Queries are stored in a separated file, which repositories read and execute them if needed. So in the repository layer RPCompanion just executes those queries and returns the corresponding ResultSet.  
Service layer takes the ResultSet and does the necessary operations, i.e mapping a row from the ResultSet in a Java object.  
In the UI built with Swing, the service layer is injected as listeners inside of some buttons. So when the user fills the UI with data, through a simple click it can store it in the database. Or even retrieve it if he wants to see what is stored in the database.

## Set up
In order to use RPCompanion, you need to adjust the database configuration located in resources/database/database.properties . There, you might want to replace 'username' and 'password'
to your MySQL credentials. This is needed so the application can stablish a connection with your MySQL and create the database and tables if needed.

## Features
RPCompanion lets you store your own made OC's, letting you the posibility to save this information of your OC: Aspect (A visual representation of your OC. JPEG, JPG or PNG file),
name, surname, date of birth and age.
It's an easy place to save your favorite OC's.

# RPCompanion
A Java desktop application made to help roleplayers store their OC's (Original Characters).

## Set-up
In order to use RPCompanion, you need to adjust the database configuration located in resources/database/database.properties . There, you might want to replace 'username' and 'password'
to your MySQL credentials. This is needed so the application can stablish a connection with your MySQL and create the database and tables if needed.

## Features
RPCompanion lets you store your own made OC's, letting you the posibility to save this information of your OC: Aspect (A visual representation of your OC. JPEG, JPG or PNG file),
name, surname, date of birth and age.
It's an easy place to save your favorite OC's.
RPCompanion makes use of JDBC in order to perform transactional operations. So, the database, tables, queries and mapping of DB registers to Java objects it's made from the ground,
without the use of any ORM such as Hibernate.

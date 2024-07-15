<h1>Basic Crud Application</h1>
<h3>Requirements</h3>
<ul>
  <li>JDK (Recommended: <a href="https://www.oracle.com/java/technologies/downloads/" target="_blank">OracleJDK</a>) - 17 or above</li>
  <li>Maven</li>
  <li>IDE of choice (VSCode, Intellij, Eclipse..)</li>
  <li> <a href="https://www.postgresql.org/download/">PostgreSQL</a> - pgAdmin</li>
  <li> <a href="https://www.postman.com/downloads/">Postman</a> - to generate requests (will not be needed when front-end is developed)</li>
</ul>
This is a simple project that uses controllers to create/change information about locations and departments.
The applicaton contains 2 entities: Location and Department. Each department has many locations; one location has only one department.
For early testing, its best to use Postman with a JSON body when generating requests, since the controller uses request parameters as method arguments and the front-end of the application is still in works.
Enjoy!
<br><br>
<h3>How to run</h3>
<p>
Make sure you have installed OracleJDK for java 17 and above<br>Open your favourite IDE<br>
  Click on open - and then navigate to the pom.xml file and open it as project<br>
  Add maven support to the project - almost all IDE's have a solution for maven<br>
  Connect the application with a database (we will be using PostgreSQL)<br>
  Navigate to the application properties and change the parameters according to your PostgreSQL configuration<br>
  Run the app - BasicCrudApplication (..\Basic Crud\src\main\java\com\example\locationapp\BasicCrudApplication.java)<br>
  Open Postman and generate http requests based on the methods developed in the Controllers<br> with the defined parameters given<br><br>
</p>
<h3>Example</h3>
<p>Lets create a new department</p>
<p>Open postman while the app is running,<br>
type localhost:8080/departments/add<br>
select post method from the left dropdown<br>
click on Body > raw > JSON and type the following line<br>
{
    "name":"Department1"
} <br>
  - This request will send a JSON file as request body <br>- the controller will pass it to the internal business logic <br>- a new department with the name Department1 is created in the database.
</p>
</p>
Following this blueprint, all of the forementioned functionalities of this app should be working properly.
#Not finished;
#Test almost done;

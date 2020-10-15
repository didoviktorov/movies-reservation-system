# movies-reservation-system

Simple system which allows the user to make seat reservations for diffrent movies, broadcasted in three distinct city locations. <br/>
There is no limitation for how many reservations to be, except if all seats are occupied. <br/>
Every movie is presented with short description, poster and if available, trailer video embeded into the application.

# Installation - local use only
Install MySQL.<br/>
Some MySql server management system like XAMPP.<br/>
Configure MySql server to listen to 3306 (default) port if not set already.

Java version 9 and above.<br/>
Java SDK tool.<br/>
Some java IDE for installing all dependencies, building and starting the application InteliJ Community, Eclipse etc.

When everything is installed and started, you can access the web app on http://localhost:8000/.<br/>
If the app is started for the first time, the database with name 'movies_system' and all needed data tables<br/>
are created automatically for you.<br/>
There is one user which is seeded into database:<br/>
username: admin<br/>
password: admin.<br/>
Only the administrator can add movies and create movies projections for different dates, hours and cinema locations and halls.<br/>
The admin user can also edit the other users information.

# Used technologies
spring framework<br/>
jQuery lybrary

# Dependencies
spring-boot-starter-data-jpa<br/>
spring-boot-starter-security<br/>
spring-boot-starter-thymeleaf<br/>
spring-boot-starter-validation<br/>
spring-boot-starter-web<br/>
mysql-connector-java<br/>
thymeleaf-extras-springsecurity4<br/>
modelmapper 0.7.5<br/>
spring-boot-starter-test<br/>
gson 2.8.2<br/>
hsqldb<br/>
jaxb-api 2.2.11




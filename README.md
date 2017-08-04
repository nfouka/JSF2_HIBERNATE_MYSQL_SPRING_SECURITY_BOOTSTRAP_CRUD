# JSF2_HIBERNATE_MYSQL_SPRING_SECURITY_BOOTSTRAP_CRUD
JSF2_HIBERNATE_MYSQL_SPRING_SECURITY_BOOTSTRAP_CRUD


# Require 

```js
maven 3.13.0-117-generic
java 8 >= 
```



# How to deploy this project ? 
----------------

```js
start tomcat 8 
./bin/start.sh 
compile project + insert data (random see init() ) in mysql db
mvn clean install ; 
deploy with tomcat
cp -r target/app.war ~/apache-tomcat-8.0.44/webapps ;

```
# Result 

```js
http://localhost:8080/app/secure/student.jsf
secure 
pass : nfouka
user : nfouka
```



# Create user table and Role 

```js

CREATE TABLE `users` (
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL DEFAULT '',
  `enabled` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('nfouka', 'nfouka', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


--
-- Table structure for table `Roles`
--

CREATE TABLE `Roles` (
  `username` varchar(20) NOT NULL DEFAULT '',
  `role` varchar(20) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Roles`
--

INSERT INTO `Roles` (`username`, `role`) VALUES
('nfouka', 'ROLE_ADMIN');

```



demo 
<img src="https://raw.githubusercontent.com/nfouka/JSF2_HIBERNATE_MYSQL_SPRING_SECURITY_BOOTSTRAP_CRUD/master/logo.png" />

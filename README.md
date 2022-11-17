

 ### Project: Member Management System
 ### Developed by: Yousef Emadi
 ### Date: APR 2021
 ### Dependencies: Maven, Spring JPA, Spring boot, Hibernate, Thymeleaf, MySQL, Lombok, Skeleton 
 
This is a mini full-stack project with Java, Spring, Thymeleaf, Hibernate and MySQL. This app will gradually merge to a website for helping people with mental health issues.
If you want to learn more or interested to join this project development, please contact me usef.emadi@gmail.com
<img width="877" alt="image" src="https://user-images.githubusercontent.com/63328419/202348867-afcb4bbe-62ab-41b0-8066-ab2c0200841a.png">

<img width="779" alt="image" src="https://user-images.githubusercontent.com/63328419/202348963-538bd507-a1c7-42f8-bad0-21904f5025c5.png">


### To setup and run the application take these steps:
web user interface: http://localhost:8080/


#### MySQL setup:
- 1. Create connection with name root and password root. otherwise reset connectin name and password in application.properties 

- 2. Run these SQL commands to create the databse:<br/>
`DROP DATABASE IF EXISTS db_members;` <br/>
`CREATE DATABASE db_members;` <br/>

- 3. After the first compiling of the application, turn off Uniqness of service id in joint table by these script:<br/>
`ALTER TABLE db_members.members_services ` <br/>
`ADD PRIMARY KEY (services_id, member_id),` <br/>
`DROP INDEX UK_h8ln2x35bcjoq1o6qykb69bwd ;` <br/>

### Note: Application members have two types of privilage: regualar members and admin members. Due to security, the ability of changing member attribute does not exist and disabled in front-end user interface. 
To change the privilage, you need to access directly to MySQL database and run this script for any memeber. (example member with id 1): <br/>
`UPDATE db_members.members SET admin =b'1' WHERE (id = 1);` <br/>

<br/>

### About the application 
- 1. Members are devided two types: Regular members and Admin members.
- 2. Each member has a different member panel including different modules to use.
- 3. password is case sensitive. Authetentication of username and password is based on MySQL database and each login process devied to three possible result: 
    - 1. username doesn't exist,
    - 2. password is not correct, 
    - 3. successful authenticatio
- 4. To create a new member record, all fields are required except phone number and email address
- 5. After a booking by user, service will be added to his list and also price of service will be deducted from member balance
- 6. Email module of the system completely done and design to work with SMTP protocol. But during the development I did not mange to solve the protocol socket problem with TSL port and to be developed in the future for the usage in sending membership activation link , reset password link or any other necessary admin modules.
- 7. Regular members has ability to create their profile, modify their profile and order a service
- 8. Admin memberes has the ability to have a report of booked services, also create, modify or delete both member profiles and service profiles as well as sending email.
- 9. All whitelable errors managed and designed proper pages for general errors and 403 and 404 numbers.
- 10. All event handlers return the web pages instead of string messages. (no dead-end page)
- 11. For more code readability, all the properties and fields of Entities has been set as "public". Clearly, in production phase we need to use setters and getters (private modifiers) to encapsulation of data to have more security.



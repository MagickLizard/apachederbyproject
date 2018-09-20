Please note: classpaths + local host may vary.

------------------- For Employee.java -----------------------------------------
/*
 - To start this java program make sure the database tables have been created.
 - SQL commands for creating the tables can be seen below.
q31. CREATE TABLES AND INSERTS USING DERBY IJ

java org.apache.derby.tools.ij
connect 'jdbc:derby:Q31_DB;create=true';

DROP TABLE ASSIGNMENT;
DROP TABLE PROJECT;
DROP TABLE EMPLOYEE;
DROP TABLE JOB;

CREATE TABLE JOB (
  job_code int NOT NULL,
  job_description varchar(40) NOT NULL,
  job_chg_hour int NOT NULL,
  PRIMARY KEY (job_code)
);

CREATE TABLE EMPLOYEE (
  emp_num int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  emp_lname varchar(40) NOT NULL,
  emp_fname varchar(40) NOT NULL,
  emp_initial varchar(2) NOT NULL,
  emp_hiredate date NOT NULL,
  job_code int NOT NULL,
  PRIMARY KEY (emp_num),
  FOREIGN KEY (job_code) REFERENCES JOB(job_code)
);

CREATE TABLE PROJECT(
  proj_num int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  proj_name varchar(40) NOT NULL,
  emp_num int UNIQUE NOT NULL,
  PRIMARY KEY (proj_num),
  FOREIGN KEY (emp_num) REFERENCES EMPLOYEE(emp_num)
);

CREATE TABLE ASSIGNMENT (
  assign_num int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  assign_date date NOT NULL,
  proj_num int NOT NULL,
  emp_num int NOT NULL,
  assign_hours int NOT NULL,
  assign_chg_hour int NOT NULL,
  assign_charge int NOT NULL,
  PRIMARY KEY (assign_num),
  FOREIGN KEY (proj_num) REFERENCES PROJECT(proj_num),
  FOREIGN KEY (emp_num) REFERENCES EMPLOYEE(emp_num)
);

INSERT INTO JOB VALUES(4523,'Cleaner',18);
INSERT INTO JOB VALUES(6587,'Programmer',25);

INSERT INTO EMPLOYEE VALUES(DEFAULT,'King','Lizzie','LK','22.05.2015',6587);
INSERT INTO EMPLOYEE VALUES(DEFAULT,'Queen','Bob','BQ','22.05.2015',4523);

INSERT INTO PROJECT VALUES(DEFAULT,'Project RocketShip',2);
INSERT INTO PROJECT VALUES(DEFAULT,'Project Submarine',1);

INSERT INTO ASSIGNMENT VALUES(DEFAULT,'25.05.2015',1,2,10,250,500);
INSERT INTO ASSIGNMENT VALUES(DEFAULT,'23.05.2015',2,1,20,400,100);

SELECT * FROM JOB;
SELECT * FROM EMPLOYEE;
SELECT * FROM PROJECT;
SELECT * FROM ASSIGNMENT;

Make sure derby server is running first
  java -jar /Users/../apache/dberby-10.11.1.1-bin/lib/derbyrun.jar server start

TO SHOW THE INSERT WORKED THROUGH IJ, ONCE WE SHUTDOWN THE SERVER

java org.apache.derby.tools.ij
connect 'jdbc:derby:Q31_DB;create=true';
SELECT * FROM EMPLOYEE;



------------------- For Customers.java -----------------------------------------

To start this java program make sure the database tables have been created.
SQL commands for creating the tables can be seen below.
/*
  CREATE TABLE Customers (CustomerId int, CustomerName varchar(30), CustomerAddress varchar(30), PRIMARY KEY (CustomerId));
  CREATE TABLE BankAccount (AccountNumber int, Balance int, PRIMARY KEY (AccountNumber));
  CREATE TABLE AccountHolding (AccountNumber int, CustomerId int, FOREIGN KEY (AccountNumber) References BankAccount (AccountNumber), FOREIGN KEY (CustomerId) References Customers (CustomerId));

  SELECT * FROM AccountHolding;
  SELECT * FROM Customers;
  SELECT * FROM BankAccount;

  DROP TABLE AccountHolding;
  DROP TABLE Customers;
  DROP TABLE BankAccount;
*/

To run this program, change directory to your derby's lib, move this java file to this location.
cd /Users/../apache/db-derby-10.11.1.1-bin/lib
 - Compile the java file
 javac Customers.java
 Open new bash terminal and start the derby server
 Return to previous terminal and start the java program.
  java Customers

 NOTE:
   Command to start derby server
    java -jar /Users/../apache/db-derby-10.11.1.1-bin/lib/derbyrun.jar server start
   Command to set the Classpath
   export CLASSPATH .:/Users/../apache/db-derby-10.11.1.1-bin/lib/derby.jar:/Users/../apache/db-derby-10.11.1.1-bin/lib/derbytools.jar:/Users/../apache/db-derby-10.11.1.1-bin/lib/derbyclient.jar

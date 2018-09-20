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

  CREATE TABLE Customers (CustomerId int, CustomerName varchar(30), CustomerAddress varchar(30), PRIMARY KEY (CustomerId));
  CREATE TABLE BankAccount (AccountNumber int, Balance int, PRIMARY KEY (AccountNumber));
  CREATE TABLE AccountHolding (AccountNumber int, CustomerId int, FOREIGN KEY (AccountNumber) References BankAccount (AccountNumber), FOREIGN KEY (CustomerId) References Customers (CustomerId));


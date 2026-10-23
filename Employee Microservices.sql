create database employee_microservices_db;

use employee_microservices_db;

show tables;

select * from employee;

describe employee;

create database if not exists department_service_db;

use department_service_db;

insert into department (id, name, location)
values
(101, 'HR', 'Mumbai'),
(104, 'Testing', 'Pune');

use employee_microservices_db;

select id, name, department_id
from employee
where department_id is not null;

select id, name, email, department_id
from employee
where department_id in (101, 104);
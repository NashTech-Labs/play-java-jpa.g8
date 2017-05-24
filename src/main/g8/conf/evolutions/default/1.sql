# --- !Ups

CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  CONSTRAINT pk_dept PRIMARY KEY (`id`)
);

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(6) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  CONSTRAINT pl_employee PRIMARY KEY (`id`)
);

ALTER TABLE `employee` ADD CONSTRAINT fk_employee_1 FOREIGN KEY (`dept_id`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

# --- !Downs

drop table if exists employee;

drop table if exists department;
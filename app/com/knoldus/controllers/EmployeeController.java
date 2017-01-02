package com.knoldus.controllers;

import com.knoldus.entities.Department;
import com.knoldus.entities.Employee;
import com.knoldus.service.EmployeeService;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by Harmeet Singh(Taara) on 27/12/16.
 */
@Singleton
public class EmployeeController extends Controller {

  private EmployeeService employeeService;

  @Inject
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @Transactional
  public Result addNewEmployee() {
    Department department = new Department();
    department.setName("Devops");

    Employee employee = new Employee();
    employee.setName("James");
    employee.setAge(27);
    employee.setSex("Male");
    employee.setDepartment(department);

    employeeService.saveNewEmployee(employee);

    System.out.println("******** Fetch All Employees ********");
    List<Employee> allEmployees = employeeService.findAllEmployees();
    allEmployees.forEach(System.out::println);

    System.out.println();
    System.out.println("******** Fetch Employee By ID ********");
    Employee employeeById = employeeService.findEmployeeById(100);
    System.out.println(employeeById);

    return ok("Add New Employee Successfully");
  }

  public Result updateEmployee(Integer id) {

    System.out.println("******** Update Employee By ID ********");
    int result = employeeService.updateEmployeeById(id);
    System.out.println(result);
    return ok("Update Employee Successfully");
  }
}

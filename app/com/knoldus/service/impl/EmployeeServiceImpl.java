package com.knoldus.service.impl;

import com.knoldus.dao.EmployeeDao;
import com.knoldus.entities.Employee;
import com.knoldus.service.EmployeeService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by Harmeet Singh(Taara) on 27/12/16.
 */
@Singleton
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeDao employeeDao;

  @Inject
  public EmployeeServiceImpl(EmployeeDao employeeDao) {
    this.employeeDao = employeeDao;
  }

  @Override public void saveNewEmployee(Employee employee) {
    employeeDao.save(employee);
  }

  @Override public Employee findEmployeeById(int id) {
    return employeeDao.findById(id);
  }

  @Override public List<Employee> findAllEmployees() {
    return employeeDao.findAll();
  }

  @Override
  public int updateEmployeeById(int id) {
    return employeeDao.updateById(id);
  }
}

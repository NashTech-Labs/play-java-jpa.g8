package com.knoldus.dao.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.knoldus.dao.EmployeeDao;
import com.knoldus.entities.Employee;
import play.api.Play;
import play.db.jpa.JPAApi;

/**
 * Created by Harmeet Singh(Taara) on 27/12/16.
 */
@Singleton
public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements EmployeeDao {

    @Inject
    public EmployeeDaoImpl(JPAApi api) {
        super(api);
    }
}

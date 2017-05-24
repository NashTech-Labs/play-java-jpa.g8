package dao;

import com.google.inject.Guice;
import com.knoldus.dao.EmployeeDao;
import com.knoldus.entities.Department;
import com.knoldus.entities.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.ApplicationLoader;
import play.Environment;
import play.db.jpa.JPAApi;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;
import play.test.Helpers;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by harmeet on 29/12/16.
 */
public class EmployeeDaoImplTest {

    @Inject
    private static Application fakeApplication;
    @Inject
    private EmployeeDao employeeDao;
    @Inject
    private JPAApi jpaApi;

    @Before
    public void setup() {
        fakeApplication = Helpers.fakeApplication();

        GuiceApplicationBuilder builder =
                new GuiceApplicationLoader().builder(new ApplicationLoader.Context(Environment.simple()));
        Guice.createInjector(builder.applicationModule()).injectMembers(this);

        Helpers.start(fakeApplication);
    }

    @After
    public void destroy() {
        Helpers.stop(fakeApplication);
    }

    @Test
    public void testSaveAndFindById() throws InterruptedException {
        Department department = new Department();
        department.setName("Tester");

        Employee employee = new Employee();
        employee.setName("Kick");
        employee.setAge(15);
        employee.setSex("Male");
        employee.setDepartment(department);

        Employee resultEmployee = jpaApi.withTransaction(() -> {
            employeeDao.save(employee);
            return employeeDao.findById(100);
        });

        Department expectedDept = new Department();
        expectedDept.setId(20);
        expectedDept.setName("Training");

        Employee expectedEmployee = new Employee();
        expectedEmployee.setId(100);
        expectedEmployee.setName("Jimmy");
        expectedEmployee.setSex("MALE");
        expectedEmployee.setAge(27);
        expectedEmployee.setDepartment(expectedDept);

        assertThat(resultEmployee, is(equalTo(expectedEmployee)));
        assertThat(resultEmployee, is(not(equalTo(employee))));
    }

    @Test
    public void testFindAll() {
        List<Employee> allEmployees = jpaApi.withTransaction(entityManager -> employeeDao.findAll());

        Department expectedDept = new Department();
        expectedDept.setId(20);
        expectedDept.setName("Training");

        Employee expectedEmployee = new Employee();
        expectedEmployee.setId(100);
        expectedEmployee.setName("Jimmy");
        expectedEmployee.setSex("MALE");
        expectedEmployee.setAge(27);
        expectedEmployee.setDepartment(expectedDept);

        assertThat(allEmployees.size(), is(equalTo(2)));
        assertThat(allEmployees, hasItems(expectedEmployee));
    }

    @Test
    public void testUpdate() {
        int result = employeeDao.updateById(101);

        Department expectedDept = new Department();
        expectedDept.setId(20);
        expectedDept.setName("Training");

        Employee expectedEmployee = new Employee();
        expectedEmployee.setId(101);
        expectedEmployee.setName("Kick");
        expectedEmployee.setSex("MALE");
        expectedEmployee.setAge(25);
        expectedEmployee.setDepartment(expectedDept);

        Employee resultEmployee = jpaApi.withTransaction(() ->
                employeeDao.findById(101)
        );

        assertThat(result, is(equalTo(1)));
        assertThat(resultEmployee, is(equalTo(expectedEmployee)));
    }
}

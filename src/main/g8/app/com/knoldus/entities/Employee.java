package com.knoldus.entities;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Harmeet Singh(Taara) on 27/12/16.
 */
@Entity
@Table(name = "employee")
public class Employee {
  @Id @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "name")
  private String name;
  @Column(name = "age")
  private Integer age;
  @Column(name = "sex")
  private String sex;
  @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "dept_id")
  private Department department;

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Employee employee = (Employee) o;

    return getId() != null ? getId().equals(employee.getId()) : employee.getId() == null;

  }

  @Override public int hashCode() {
    return getId() != null ? getId().hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Employee{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", sex='" + sex + '\'' +
            ", department=" + department +
            '}';
  }
}

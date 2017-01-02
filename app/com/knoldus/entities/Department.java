package com.knoldus.entities;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Harmeet Singh(Taara) on 27/12/16.
 */
@Entity
@Table(name = "department")
public class Department {
  @Id @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "name")
  private String name;
  @OneToMany(fetch = LAZY, mappedBy = "department")
  private Set<Employee> employees;

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

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Department that = (Department) o;

    return getId() != null ? getId().equals(that.getId()) : that.getId() == null;

  }

  @Override public int hashCode() {
    return getId() != null ? getId().hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Department{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}

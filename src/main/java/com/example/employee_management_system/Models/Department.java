package com.example.employee_management_system.Models;

import java.util.List;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Cacheable
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;
	@OneToMany(mappedBy = "department")
	private List<Employee> employees;

	public List<Employee> getEmployees() {
		return employees;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", employees=" + employees + "]";
	}
}

package com.example.employee_management_system;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.employee_management_system.Models.Department;
import com.example.employee_management_system.Models.Employee;

public class App {
	public static void main(String[] args) {
		Department department = new Department();
		department.setName("Development");

		Employee employee1 = new Employee();
		employee1.setName("John Smith");
		Employee employee2 = new Employee();
		employee2.setName("Jason Smith");

		department.setEmployees(Arrays.asList(employee1, employee2));
		employee1.setDepartment(department);
		employee2.setDepartment(department);

		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Department.class).buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction transaction1 = session.beginTransaction();
		session.persist(employee1);
		session.persist(employee2);
		session.persist(department);
		transaction1.commit();

		Transaction transaction2 = session.beginTransaction();
		Department dept = session.get(Department.class, 1);
		System.out.println(dept);
		Employee emp = session.get(Employee.class, 1);
		System.out.println(emp);
		transaction2.commit();

		Transaction transaction3 = session.beginTransaction();
		Employee emp2 = session.get(Employee.class, 1);
		emp2.setName("John B. Smith");
		Employee empToDelete = session.get(Employee.class, 2);
		if (empToDelete != null) {
			session.remove(empToDelete);
		}
		transaction3.commit();

		Transaction transaction4 = session.beginTransaction();
		Department dept2 = session.get(Department.class, 1);
		System.out.println(dept2.getEmployees());
		transaction4.commit();

		session.close();
		sessionFactory.close();
	}
}

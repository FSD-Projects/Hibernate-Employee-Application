package com.mycompany.hibernate_crud_demo.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mycompany.hibernate_crud_demo.model.Employee;

public class MyHibernateFactory {
	private static SessionFactory factory;
	private static Session session;
	
	public static Session getMySession() {
		factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		Session session = factory.openSession();
		return session;
				
	}
}

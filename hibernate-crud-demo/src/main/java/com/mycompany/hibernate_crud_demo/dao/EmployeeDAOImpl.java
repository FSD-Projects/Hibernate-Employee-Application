package com.mycompany.hibernate_crud_demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;

import com.devskiller.friendly_id.FriendlyId;
import com.mycompany.hibernate_crud_demo.factory.MyHibernateFactory;
import com.mycompany.hibernate_crud_demo.model.Employee;
import com.mycompany.hibernate_crud_demo.shared.SharedEmployee;

public class EmployeeDAOImpl implements EmployeeDAO {
	private Session session = null;
	private SessionFactory sessionFactory = null;
	
	public EmployeeDAOImpl() {
		super();
		session = MyHibernateFactory.getMySession();
	}
	public void createEmployee(SharedEmployee sEmployee) {
		ModelMapper mapper = new ModelMapper();
		Employee employee = mapper.map(sEmployee, Employee.class);
		
		String uUid = FriendlyId.toFriendlyId(UUID.randomUUID());
		employee.setGeneratedId(uUid);
		
		/*UUID uUid = UUID.randomUUID();
		employee.setGeneratedId(uUid.toString());*/
		
		session.getTransaction().begin();
		session.save(employee);
		session.getTransaction().commit();
	}
	public List<SharedEmployee> getAllEmployee() {
		// TODO Auto-generated method stub
		ModelMapper mapper = new ModelMapper();
		session.getTransaction().begin();
		Query query = session.createQuery("from Employee");
		session.getTransaction().commit();
		@SuppressWarnings("unchecked")
		List<Employee> list1 = query.getResultList();
		List<SharedEmployee> sList = new ArrayList<SharedEmployee>();
		for(Employee e: list1) {
			SharedEmployee se = mapper.map(e, SharedEmployee.class);
			sList.add(se);
		}
		return sList;
	}
	public SharedEmployee findById(int id) {
		// TODO Auto-generated method stub
		session.getTransaction().begin();
		Employee employee = session.get(Employee.class, new Integer(id));
		session.getTransaction().commit();
		SharedEmployee sEmployee;
		if(employee != null) {
			ModelMapper mapper = new ModelMapper();
			sEmployee = mapper.map(employee, SharedEmployee.class);
			return sEmployee;
		}
		else {
			return null;
		}
	}
	public void findByCustomId(String generated_id) {
		// TODO Auto-generated method stub
		session.getTransaction().begin();
		Query query = session.createQuery("from Employee e where e.generatedId=:gId").setParameter("gId", generated_id);
		@SuppressWarnings("unchecked")
		List<Employee> l = query.getResultList();
		session.getTransaction().commit();
		ModelMapper mapper = new ModelMapper();
		List<SharedEmployee> sList = new ArrayList<SharedEmployee>();
		for(Employee e: l) {
			SharedEmployee se = mapper.map(e, SharedEmployee.class);
			sList.add(se);
		}
		if(!sList.isEmpty()) {
			for(SharedEmployee e: sList) {
				System.out.println(e);
			}
		}
		else {
			System.out.println("No such Employee found!");
		}
	}
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		session.getTransaction().begin();
		Employee e = session.get(Employee.class, id);
		if(e != null) {
			session.remove(e);
			System.out.println("Record with id " + id + " deleted sucessfully!");
			session.getTransaction().commit();
		}
		else {
			System.out.println("No such employee record!");
		}
		
	}
	public void updateById(int id, String firstName, String lastName, String email) {
		// TODO Auto-generated method stub
		session.getTransaction().begin();
		Employee e = session.get(Employee.class, id);
		if(e != null) {
			e.setFirstName(firstName);
			e.setLastName(lastName);
			e.setEmail(email);
			session.update(e);
			System.out.println("Record with id " + id + " updated sucessfully!");
			session.getTransaction().commit();
		}
		else {
			System.out.println("No such employee record!");
		}
	}
}

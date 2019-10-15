package com.mycompany.hibernate_crud_demo.dao;

import java.util.List;

import com.mycompany.hibernate_crud_demo.shared.SharedEmployee;

public interface EmployeeDAO {
	public void createEmployee(SharedEmployee sEmployee);
	public List<SharedEmployee> getAllEmployee();
	public SharedEmployee findById(int id);
	public void findByCustomId(String generated_id);
	public void deleteById(int id);
	public void updateById(int id, String firstName, String lastName, String email);
}

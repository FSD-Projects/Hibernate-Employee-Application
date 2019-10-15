package com.mycompany.hibernate_crud_demo;

import java.util.List;
import java.util.Scanner;

import com.mycompany.hibernate_crud_demo.dao.EmployeeDAO;
import com.mycompany.hibernate_crud_demo.dao.EmployeeDAOImpl;
import com.mycompany.hibernate_crud_demo.shared.SharedEmployee;
import static java.lang.System.in;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAOImpl();
		Scanner sc = new Scanner(in);
		int choice, id;
		String fName, lName, email, generatedId;
		try {
			do {
				System.out.println("1. Create Employee Record");
				System.out.println("2. List all Employees");
				System.out.println("3. Find Employee by ID");
				System.out.println("4. Find Employee by Custom ID");
				System.out.println("5. Remove Employee by ID");
				System.out.println("6. Update Employee by ID");
				System.out.println("Enter your choice:");
				
				choice = sc.nextInt();
				switch (choice) {
					case 1:
						System.out.println("Enter First Name: ");
						fName = sc.next();
						System.out.println("Enter Last Name: ");
						lName = sc.next();
						System.out.println("Enter Email: ");
						email = sc.next();
						dao.createEmployee(new SharedEmployee(fName, lName, email));
						System.out.println("Inserted!");
						break;
					case 2:
						List<SharedEmployee> list = dao.getAllEmployee();
						for (SharedEmployee se : list) {
							//System.out.printf("%s %s %s\n", se.getFirstName(),se.getLastName(), se.getEmail());
							System.out.println(se);
						}
						break;
					case 3:
						System.out.println("Enter Employee Id:");
						id = sc.nextInt();
						SharedEmployee sEmployee = dao.findById(id);
						if(sEmployee == null) {
							System.out.println("No such record found!");
						}
						else {
							System.out.println(sEmployee);
						}
						break;
					case 4:
						System.out.println("Enter Employee Custom Id:");
						generatedId = sc.next();
						dao.findByCustomId(generatedId);
						break;
					case 5:
						System.out.println("Enter ID to remove:");
						id = sc.nextInt();
						dao.deleteById(id);
						break;
					case 6:
						System.out.println("Enter ID to update:");
						id = sc.nextInt();
						System.out.println("Enter First Name:");
						fName = sc.next();
						System.out.println("Enter Last Name:");
						lName = sc.next();
						System.out.println("Enter Email:");
						email = sc.next();
						dao.updateById(id, fName, lName, email);
						break;
					case 0: 
						System.out.println("Program exits");
						System.exit(0);
					default:
						break;
					}
				} while(choice != 0);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package org.example.employeePayrollapp;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        employeeDAO dao = new employeeDAO(factory);
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== EMPLOYEE PAYROLL MENU =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Get Employee By ID");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Display All Employees");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    employee emp = new employee();

                    System.out.print("Enter Employee Name: ");
                    emp.setEmployeeName(sc.nextLine());

                    System.out.print("Enter Department: ");
                    emp.setDepartment(sc.nextLine());

                    System.out.print("Enter Salary: ");
                    emp.setSalary(sc.nextDouble());
                    sc.nextLine();

                    System.out.print("Enter Joining Date (yyyy-mm-dd): ");
                    emp.setJoiningDate(LocalDate.parse(sc.nextLine()));

                    dao.insert(emp);
                    break;

                case 2:
                    System.out.print("Enter Employee ID: ");
                    Long id = sc.nextLong();

                    employee found = dao.getById(id);

                    if (found != null) {
                        System.out.println("\nEmployee Details:");
                        System.out.println("ID: " + found.getEmployeeId());
                        System.out.println("Name: " + found.getEmployeeName());
                        System.out.println("Department: " + found.getDepartment());
                        System.out.println("Salary: " + found.getSalary());
                        System.out.println("Joining Date: " + found.getJoiningDate());
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Employee ID to Update: ");
                    Long updateId = sc.nextLong();
                    sc.nextLine();

                    employee updateEmp = dao.getById(updateId);

                    if (updateEmp != null) {

                        System.out.print("Enter New Name: ");
                        updateEmp.setEmployeeName(sc.nextLine());

                        System.out.print("Enter New Department: ");
                        updateEmp.setDepartment(sc.nextLine());

                        System.out.print("Enter New Salary: ");
                        updateEmp.setSalary(sc.nextDouble());
                        sc.nextLine();

                        System.out.print("Enter New Joining Date (yyyy-mm-dd): ");
                        updateEmp.setJoiningDate(LocalDate.parse(sc.nextLine()));

                        dao.Update(updateEmp);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Employee ID to Delete: ");
                    Long deleteId = sc.nextLong();
                    dao.Delete(deleteId);
                    break;

                case 5:
                    dao.getAll();
                    break;

                case 0:
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 0);

        sc.close();
        factory.close();
    }
}
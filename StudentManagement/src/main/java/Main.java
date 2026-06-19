import org.example.studentapp.Student;
import org.example.studentapp.StudentDAO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.config.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        StudentDAO studentDAO = new StudentDAO(factory);
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student By ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    Student student = new Student(name, email, course, age);
                    studentDAO.saveStudent(student);

                    System.out.println("Student Added Successfully!");
                    break;

                case 2:
                    List<Student> students = studentDAO.getAllStudents();

                    System.out.println("\n--- Student List ---");
                    for (Student s : students) {
                        System.out.println(
                                s.getStudentId() + " | " +
                                        s.getStudentName() + " | " +
                                        s.getEmail() + " | " +
                                        s.getCourse() + " | " +
                                        s.getAge()
                        );
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    Long searchId = sc.nextLong();

                    Student found = studentDAO.getStudentByID(searchId);

                    if (found != null) {
                        System.out.println("ID: " + found.getStudentId());
                        System.out.println("Name: " + found.getStudentName());
                        System.out.println("Email: " + found.getEmail());
                        System.out.println("Course: " + found.getCourse());
                        System.out.println("Age: " + found.getAge());
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID to Update: ");
                    Long updateId = sc.nextLong();
                    sc.nextLine();

                    Student updateStudent = studentDAO.getStudentByID(updateId);

                    if (updateStudent != null) {

                        System.out.print("Enter New Course: ");
                        String newCourse = sc.nextLine();

                        updateStudent.setCourse(newCourse);

                        studentDAO.updateStudent(updateStudent);

                        System.out.println("Student Updated Successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter Student ID to Delete: ");
                    Long deleteId = sc.nextLong();

                    studentDAO.deleteStudent(deleteId);

                    System.out.println("Student Deleted Successfully!");
                    break;

                case 6:
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        sc.close();
        factory.close();
    }
}
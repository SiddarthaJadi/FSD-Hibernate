package org.example.studentapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class StudentDAO {
    private SessionFactory factory;
    public StudentDAO(SessionFactory factory) {
        this.factory = factory;
    }
    // CREATE
    public void saveStudent(Student student) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // READ - Single student (By ID)
    public Student getStudentByID(Long ID) {
        try(Session session = factory.openSession()) {
            return session.find(Student.class, ID);
        }
    }
    // READ - Get All students
    public List<Student> getAllStudents() {
        try(Session session = factory.openSession()) {
            return session.createQuery("FROM Student", Student.class).list();
        }
    }
    // UPDATE
    public void updateStudent(Student student) {
        try(Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // DELETE
    public void deleteStudent(Long ID) {
        try(Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = session.find(Student.class, ID);
            if(student != null) {
                session.remove(student);
                System.out.println("Student removed successfully");
            } else {
                System.out.println("Student not found");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

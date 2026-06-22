package org.example.employeePayrollapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class employeeDAO {
    private SessionFactory sessionFactory;
    public employeeDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    public void insert(employee employee){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(employee);
        transaction.commit();
        session.close();
        System.out.println("Product Inserted Successfully");
    }
    public employee getById(Long id){
        Session session = sessionFactory.openSession();
        employee p = session.find(employee.class,id);
        session.close();
        return p;
    }
    public void Update(employee p){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(p);
        tx.commit();
        session.close();
        System.out.println("Product is updated successfully");
    }
    public void Delete(Long id){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        employee last = session.find(employee.class,id);
        if(last!=null){
            session.remove(last);
            System.out.println("Product is successfully Deleted");
        }
        else{
            System.out.println("The id doest in the database");
        }
        tx.commit();
        session.close();
    }
    public List<employee> getAll(){
        Session session = sessionFactory.openSession();
        List<employee> employee = session.createQuery("from employee ",employee.class).list();
        for(employee p : employee){
            System.out.println(p);
        }
        return employee;
    }



}

package com.hibernate01.hibernateProject01;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcom to SMS");

        System.out.println("Enter student name, marks, email");
        Student s1 = new Student(sc.next(), sc.nextInt(), sc.next());
        
        // Hibernate 7 - steps
        
        // 1 - load the configuration
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        
        // 2 - create session factory
        SessionFactory factory = configuration.buildSessionFactory();
        
        // 3 - open session
        Session session = factory.openSession();
        
        // 4 - Begin Transaction 
        Transaction transaction = session.beginTransaction();
        
        // 5 - Perfoms operation
         
        // inserting data
        session.persist(s1);
        
        // gettting data 
        Student obj = session.get(Student.class, 2);
        System.out.println(obj);
        
        // delete data - check whether data is present on DB or not
        System.out.println("Enter user id to be deleted: ");
        int id = sc.nextInt();
        Student obj2 = session.get(Student.class, id);
        if(obj2 != null) {
        	session.remove(obj2);
        	System.out.println("Data is deleted");
        }else {
        	System.out.println("Data is not present");
        }
        
//        // update data - check whether data is present on DB or not
        System.out.println("Enter user id to be update name and email: ");
        int id2 = sc.nextInt();
        
        Student obj3 = session.get(Student.class, id2);
        if(obj3 != null) {
        	System.out.println("Enter new name and email: ");
        	String name = sc.next();
        	String email = sc.next();
        	obj3.setName(name);
        	obj3.setEmail(email);
        	session.merge(obj3); // or you should use session.persists also
        	System.out.println("Data is updated");
        }else {
        	System.out.println("Data is not present");
        }
        
        
        
        // 6 - commit Transaction
        transaction.commit();
        
        // 7 - close session and session Factory
        session.close();
        factory.close();
        
        
    }
}

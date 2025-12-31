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
//        Simple application using hibernate and mysql
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
        session.persist(s1);
        
        // 6 - commit Transaction
        transaction.commit();
        
        // 7 - close session and session Factory
        session.close();
        factory.close();
        
        
    }
}

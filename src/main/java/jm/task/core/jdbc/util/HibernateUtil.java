package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new Configuration();

            cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test_db");
            cfg.setProperty("hibernate.connection.username", "root");
            cfg.setProperty("hibernate.connection.password", "1234");
            cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

            cfg.setProperty("hibernate.show.sql", "true");
            cfg.setProperty("hibernate.hbm2ddl.auto", "update");

            cfg.addAnnotatedClass(User.class); //Класс - Entity.

            return cfg.buildSessionFactory();
        } catch (Exception a) {
            throw new RuntimeException("Failed to build Session Factory");
        }
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }
}

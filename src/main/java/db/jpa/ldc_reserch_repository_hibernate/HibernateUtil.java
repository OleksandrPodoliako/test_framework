package db.jpa.ldc_reserch_repository_hibernate;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.persistence.Entity;
import java.util.Properties;

import static utils.PropertiesReader.getProperty;

@Getter
@Setter
public class HibernateUtil {

    private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);
    private static HibernateUtil instance = new HibernateUtil();

    private SessionFactory sessionFactory;

    private HibernateUtil() {
        this.sessionFactory = buildSessionFactory();
    }

    public static void main(String[] args) {
        new HibernateUtil();
    }

    public static HibernateUtil getInstance() {
        if (instance == null) {
            return new HibernateUtil();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private synchronized SessionFactory buildSessionFactory() {
        Properties prop = new Properties();
        prop.setProperty("hibernate.connection.url", getProperty("hibernate.connection.url"));
        prop.setProperty("hibernate.connection.username", getProperty("hibernate.connection.username"));
        prop.setProperty("hibernate.connection.password", getProperty("hibernate.connection.password"));
        prop.setProperty("hibernate.connection.driver_class", getProperty("hibernate.connection.driver.class"));
        prop.setProperty("hibernate.dialect", getProperty("hibernate.dialect"));
        prop.setProperty("javax.persistence.lock.timeout", getProperty("javax.persistence.lock.timeout"));
        prop.setProperty("hibernate.show_sql", getProperty("hibernate.show.sql"));
        prop.setProperty("hibernate.format_sql", getProperty("hibernate.format.sql"));
        LOGGER.info("HIBERNATE init :" + prop);

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

        Configuration configuration = new Configuration().addProperties(prop);

        for (BeanDefinition bd : scanner.findCandidateComponents("db.jpa.entity")) {
            try {
                configuration.addAnnotatedClass(Class.forName(bd.getBeanClassName()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return configuration.buildSessionFactory();
    }
}

package saffchen.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import saffchen.entity.ProductEntity;
import saffchen.entity.SatelliteEntity;

public class DBConnection implements Connection {
    public Session pgConnect(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(SatelliteEntity.class);
        configuration.addAnnotatedClass(ProductEntity.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        return session;
    }
}

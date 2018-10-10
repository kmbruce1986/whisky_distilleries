package com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class DistilleryRepositoryImpl implements DistilleryRepositoryCustom {

    @Autowired
    EntityManager entityManager;



    @Override
    @Transactional
    public List<Distillery> findDistilleriesByRegion(String region){
        List<Distillery> results = null;
        Session session = entityManager.unwrap(Session.class);

        try {
            Criteria cr = session.createCriteria(Distillery.class);
            cr.add(Restrictions.eq("region", region));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return results;
    }

    @Override
    @Transactional
    public List<Distillery> findDistilleriesWithWhiskyAged(int age){
        List<Distillery> results = null;
        Session session = entityManager.unwrap(Session.class);

        try {
            Criteria cr = session.createCriteria(Distillery.class);
//            for each whisky
            cr.createAlias("whiskies", "whisky");
//            find whiskies that match the age
            cr.add(Restrictions.eq("whisky.age", age));
//            return a list with their distillery
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return results;
    }

}

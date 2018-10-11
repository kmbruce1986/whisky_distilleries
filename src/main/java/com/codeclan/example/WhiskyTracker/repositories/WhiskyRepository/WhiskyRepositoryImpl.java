package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;


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

public class WhiskyRepositoryImpl implements WhiskyRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Whisky> getWhiskiesForYear(int year){

        List<Whisky> whiskies = null;

        Session session = entityManager.unwrap(Session.class);

        try {
//            think of this as SELECT * FROM whiskies
            Criteria cr = session.createCriteria(Whisky.class);
//            think of this as WHERE year(property) = year(argument)
            cr.add(Restrictions.eq("year", year));
//            make the list!
            whiskies = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return whiskies;
    }

    @Override
    @Transactional
    public List<Whisky> getWhiskiesByRegion(String region){
        List<Whisky> results = null;
        Session session = entityManager.unwrap(Session.class);

        try {
//            think of this as SELECT * FROM whiskies
            Criteria cr = session.createCriteria(Whisky.class);
//            think of this as looping through the distillery(property) and naming each one a batman.  Alias is to be used when we will be looking through another class.
            cr.createAlias("distillery", "thedistillery");
//            think of this as WHERE the ditillery's region(property) matches the region(argument).  we are able to access this easily.
            cr.add(Restrictions.eq("thedistillery.region", region).ignoreCase());
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
//    we could pass in Long distilleryId instead of the String distillery, then amend the restriction for distillery name and amend the controller
    public List<Whisky> getWhiskiesFromDistilleryAged(String distillery, int age){
        List<Whisky> results = null;
        Session session = entityManager.unwrap(Session.class);

        try {
            Criteria cr = session.createCriteria(Whisky.class);
            cr.createAlias("distillery", "thedistillery");
            cr.add(Restrictions.eq("thedistillery.name", distillery).ignoreCase());
            cr.add(Restrictions.eq("age", age));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }


        return results;
    }




}

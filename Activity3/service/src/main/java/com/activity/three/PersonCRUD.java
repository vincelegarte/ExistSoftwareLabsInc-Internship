package com.activity.three;
import com.activity.three.HibernateUtil;
import java.util.List;
import java.util.Iterator;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PersonCRUD {

    public void addPerson(Person person){ //CREATE
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public Person getPersonById(long id){ //GET ID
        Transaction transaction = null;
        Person person = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            person = session.get(Person.class, id);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
        return person;
    }

    public List<Person> getAllPersons(){ //READ
        Transaction transaction = null;
        List<Person> persons = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            persons = session.createQuery("FROM person").list();
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
        return persons;
    }

    public void updatePerson(Person person){ //UPDATE
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(person);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public void removePerson(long id){ //DELETE
        Transaction transaction = null;
        Person person = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            person = session.get(Person.class, id);
            session.delete(person);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

}

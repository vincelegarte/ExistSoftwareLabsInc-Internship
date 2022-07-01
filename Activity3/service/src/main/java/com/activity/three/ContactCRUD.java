package com.activity.three;

import com.activity.three.HibernateUtil;

import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class ContactCRUD {

    public void addContact(Contact contact){ //CREATE
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(contact);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public Contact getContactById(long id){ //GET ID
        Transaction transaction = null;
        Contact contact = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            contact = session.get(Contact.class, id);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
        return contact;
    }

    public List<Contact> getAllContact(){ //READ
        Transaction transaction = null;
        List<Contact> contacts = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            contacts = session.createQuery("FROM contact").list();
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
        return contacts;
    }

    public void updateContact(Contact contact){ //UPDATE
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(contact);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public void removeContact(long id){ //DELETE
        Transaction transaction = null;
        Contact contact = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            contact = session.get(Contact.class, id);
            session.delete(contact);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

}

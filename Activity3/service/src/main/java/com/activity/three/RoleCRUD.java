package com.activity.three;

import com.activity.three.HibernateUtil;

import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class RoleCRUD {

    public void addRole(Role role){ //CREATE
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            session.close();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public Role getRoleById(long id){ //GET ID
        Transaction transaction = null;
        Role role = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            role = session.get(Role.class, id);
            transaction.commit();
            session.close();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
        return role;
    }

    public List<Role> getAllRoles(){ //READ
        Transaction transaction = null;
        List<Role> roles = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            roles = session.createQuery("FROM role").list();
            transaction.commit();
            session.close();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
        return roles;
    }

    public void updateRole(Role role){ //UPDATE
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(role);
            transaction.commit();
            session.close();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public void removeRole(long id){ //DELETE
        Transaction transaction = null;
        Role role = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            role = session.get(Role.class, id);
            session.delete(role);
            transaction.commit();
            session.close();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

}

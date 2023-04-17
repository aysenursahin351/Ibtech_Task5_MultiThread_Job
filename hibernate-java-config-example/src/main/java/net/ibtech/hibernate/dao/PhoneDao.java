package net.ibtech.hibernate.dao;

import java.util.List;

import javax.persistence.Query;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import net.ibtech.hibernate.model.Phone;
import net.ibtech.hibernate.util.HibernateUtil;

public class PhoneDao {

    private Session session;

	public PhoneDao() {
        // TODO Auto-generated constructor stub
    }

    public PhoneDao(Session session) {
		// TODO Auto-generated constructor stub
    	 this.session = session;
	}

	public void savePhone(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(phone);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
        }
    }
	public int savePhoneAndGetId(Phone phone) {
	    Transaction transaction = null;
	    int phoneId = 0;
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        transaction = session.beginTransaction();
	        phoneId = (int) session.save(phone);
	        transaction.commit();
	    } catch (HibernateException e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return phoneId;
	}


    public void insertPhone() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "INSERT INTO Phone (number, type) "
                    + "SELECT number, type FROM Phone";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
        }
    }

    public void updatePhone(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "UPDATE Phone SET number = :number, type = :type WHERE id = :phoneId";
            Query query = session.createQuery(hql);
            query.setParameter("number", phone.getNumber());
            query.setParameter("type", phone.getType());
            query.setParameter("phoneId", phone.getId());
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
        }
    }

    public void deletePhone(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Phone phone = session.get(Phone.class, id);
            if (phone != null) {
                String hql = "DELETE FROM Phone WHERE id = :phoneId";
                Query query = session.createQuery(hql);
                query.setParameter("phoneId", id);
                int result = query.executeUpdate();
                System.out.println("Rows affected: " + result);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
        }
    }

    public Phone getPhone(int id) {
        Transaction transaction = null;
        Phone phone = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM Phone P WHERE P.id = :phoneId";
            Query query = session.createQuery(hql);
            query.setParameter("phoneId", id);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                phone = (Phone) results.get(0);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
        }
        return phone;
    }

    public List<Phone> getPhones() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Phone", Phone.class).list();
        }
    }
}
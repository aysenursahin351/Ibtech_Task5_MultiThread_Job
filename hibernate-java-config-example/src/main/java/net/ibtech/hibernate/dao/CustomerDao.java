package net.ibtech.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import net.ibtech.hibernate.model.Customer;
import net.ibtech.hibernate.model.Phone;
import net.ibtech.hibernate.util.HibernateUtil;

public class CustomerDao {

	private Session session;

	public CustomerDao() {
		// TODO Auto-generated constructor stub
	}

	public CustomerDao(Session session) {
		// TODO Auto-generated constructor stub
		 this.session = session;
	}

	public void saveCustomer(Customer customer) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.save(customer);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        // Daha ayrıntılı hata işleme yapılabilir
	    }
	}
	/*public void saveCustomer(Customer customer) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
	    	

	        entityManager.getTransaction().begin();
	        entityManager.persist(customer);
	        for (Phone phone : customer.getPhones()) {
	            if (phone.getId() == 0) {
	                entityManager.persist(phone);
	            } else {
	                entityManager.merge(phone);
	            }
	        }
	        entityManager.getTransaction().commit();
	    } catch (Exception e) {

	        entityManager.getTransaction().rollback();
	        throw e;
	    }
	}
*/

	public void insertCustomer() {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        String hql = "INSERT INTO Customer (firstName, lastName, email) "
	                + "SELECT firstName, lastName, email FROM Customer";
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

	public void updateCustomer(Customer customer) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        // Initialize collections
	        Hibernate.initialize(customer.getAddresses());
	        Hibernate.initialize(customer.getPhones());

	        String hql = "UPDATE Customer set firstName = :firstName, lastName = :lastName, email = :email WHERE id = :customerId";
	        Query query = session.createQuery(hql);
	        query.setParameter("firstName", customer.getFirstName());
	        query.setParameter("lastName", customer.getLastName());
	        query.setParameter("email", customer.getEmail());
	        query.setParameter("customerId", customer.getId());
	        int result = query.executeUpdate();
	        System.out.println("Rows affected: " + result);

	        session.flush(); // Verilerin veritabanına kaydedilmesini sağlar
	        session.clear(); // Session önbelleğini temizler
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        // Daha ayrıntılı hata işleme yapılabilir
	    }
	}
	public void deleteCustomer(int id) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        Customer customer = session.get(Customer.class, id);
	        if (customer != null) {
	            String hql = "DELETE FROM Customer WHERE id = :customerId";
	            Query query = session.createQuery(hql);
	            query.setParameter("customerId", id);
	            int result = query.executeUpdate();
	            System.out.println("Rows affected: " + result);
	        }

	        session.flush(); // Verilerin veritabanına kaydedilmesini sağlar
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        // Daha ayrıntılı hata işleme yapılabilir
	    }
	}

	public Customer getCustomer(int id) {
	    Transaction transaction = null;
	    Customer customer = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        customer = session.get(Customer.class, id);
	        Hibernate.initialize(customer.getAddresses());

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        // Daha ayrıntılı hata işleme yapılabilir
	    }
	    return customer;
	}

	public List<Customer> getCustomers() {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        return session.createQuery("FROM Customer", Customer.class).list();
	    }
	}

	public int saveCustomerAndGetId(Customer customer) {
	    Transaction transaction = null;
	    int customerId = 0;
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        transaction = session.beginTransaction();
	        customerId = (int) session.save(customer);
	        transaction.commit();
	    } catch (HibernateException e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return customerId;
	}

}
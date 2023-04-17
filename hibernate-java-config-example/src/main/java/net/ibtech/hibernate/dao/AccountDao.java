package net.ibtech.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.ibtech.hibernate.model.Account;
import net.ibtech.hibernate.model.Customer;
import net.ibtech.hibernate.util.HibernateUtil;

public class AccountDao {
	 private Session session;
	    
	    public AccountDao(Session session) {
	        this.session = session;
	    }
	 public AccountDao() {
			// TODO Auto-generated constructor stub
		}
	/*public void addAccount(Account account) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
	            // get the customer id of the account from the account's customer object
	            int customerId = account.getCustomer().getId();
	            // get the customer object from the database using the customer id
	            Customer customer = session.get(Customer.class, customerId);
	            // set the customer object to the account object
	            account.setCustomer(customer);
	            session.save(account);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }*/
	 public void addAccount(Account account) {
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    Transaction transaction = null;
		    try {
		        transaction = session.beginTransaction();
		        int customerId = account.getCustomer().getId();
		        Customer customer = session.get(Customer.class, customerId);
		        account.setCustomer(customer);
		        session.save(account);
		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		    } finally {
		        if (session != null) {
		            session.close();
		        }
		    }
		}


    public void updateAccount(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(account);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
        }
    }

    public void deleteAccount(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Account account = session.get(Account.class, id);
            if (account != null) {
                session.delete(account);
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

    public Account getAccountById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Account account = session.get(Account.class, id);

            return account;
        } catch (Exception e) {
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
            return null;
        }
    }

    public List<Account> getAllAccounts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<Account> accounts = session.createQuery("from Account", Account.class).list();

            return accounts;
        } catch (Exception e) {
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
            return null;
        }
    }
    

}
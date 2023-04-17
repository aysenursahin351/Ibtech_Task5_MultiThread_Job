package net.ibtech.hibernate.operation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.ibtech.hibernate.dao.CustomerDao;
import net.ibtech.hibernate.dao.PhoneDao;
import net.ibtech.hibernate.model.Customer;
import net.ibtech.hibernate.model.Phone;
import net.ibtech.hibernate.util.HibernateUtil;
import net.ibtech.hibernate.xbag.XBag;
import net.ibtech.hibernate.xbag.XBagKey;

public class PhoneOperation {
     PhoneDao phoneDao = new PhoneDao();
    
     public void add2(XBag xbag) {
    	    int customerId = (int) xbag.getValue(XBagKey.CUSTOMER_ID);
    	    CustomerDao customerDao = new CustomerDao();
    	    Customer customer = customerDao.getCustomer(customerId);

    	    if (customer != null) {
    	        Session session = HibernateUtil.getSessionFactory().openSession();
    	        Transaction transaction = null;

    	        try {
    	            transaction = session.beginTransaction();
    	            Phone phone = new Phone();
    	            phone.setCustomer(customer);
    	            phone.setNumber((String) xbag.getValue(XBagKey.NUMBER));
    	            session.save(phone);
    	            transaction.commit();
    	        } catch (HibernateException e) {
    	            if (transaction != null) {
    	                transaction.rollback();
    	            }
    	            e.printStackTrace();
    	        } finally {
    	            session.close();
    	        }
    	    }
    	}


     public void add(XBag xbag) {
 		Phone phone = new Phone();
 		Object numberValue = xbag.getValue(XBagKey.NUMBER);
 		Object TYPEValue = xbag.getValue(XBagKey.TYPE);

 		if (numberValue != null) {
 		    phone.setNumber(numberValue.toString());
 		    System.out.println("AAAAAAAAAAAAAAAAA");
 		}
 		if (TYPEValue != null) {
 	 		phone.setType( (xbag.getValue(XBagKey.TYPE)).toString());
 		    System.out.println("BBBBBBBBBBBBBBB");
 		}
 		//phone.setNumber( (String) (xbag.getValue(XBagKey.NUMBER)));
 		
 		phoneDao.savePhoneAndGetId(phone);
 	}
   
	public void update(XBag bag) {
		Phone phone = new Phone(0, null,null, null);
		phoneDao.updatePhone(phone);
	}

	public void delete(XBag bag) {
		Phone phone = new Phone(0, null,null, null);
		phoneDao.deletePhone(phone.getId());
	}
}

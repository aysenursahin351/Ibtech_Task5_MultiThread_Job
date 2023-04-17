package net.ibtech.hibernate.operation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import net.ibtech.hibernate.dao.AccountDao;
import net.ibtech.hibernate.dao.AddressDao;
import net.ibtech.hibernate.dao.CustomerDao;
import net.ibtech.hibernate.dao.PhoneDao;
import net.ibtech.hibernate.model.Account;
import net.ibtech.hibernate.model.Address;
import net.ibtech.hibernate.model.Customer;
import net.ibtech.hibernate.model.Phone;
import net.ibtech.hibernate.util.HibernateUtil;
import net.ibtech.hibernate.xbag.XBag;
import net.ibtech.hibernate.xbag.XBagKey;

public class CustomerOperation {
	private static final LocalDateTime LocalDateTime = null;
	CustomerDao customerDao=new CustomerDao();
//// buranın içine phone ve address nesnesi varsa ekleme işlemi gerçekleştirmen gerekiyor.
	/*public void add(XBag xbag) {
		System.out.println("bag ile add gerçekleşti");
	 Customer customer = new Customer((int) xbag.getValue(XBagKey.CUSTOMER_ID),(String) xbag.getValue(XBagKey.FIRSTNAME),(String) xbag.getValue(XBagKey.LASTNAME),(String) xbag.getValue(XBagKey.EMAIL));
	        
	 customer.setId((int) xbag.getValue(XBagKey.CUSTOMER_ID));
     customer.setFirstName((String) xbag.getValue(XBagKey.FIRSTNAME));
     customer.setFirstName((String) xbag.getValue(XBagKey.LASTNAME));
     customer.setFirstName((String) xbag.getValue(XBagKey.EMAIL)); 
   // customer.addPhone((Phone) xbag.getValue(XBagKey.CUSTOMER_ID));
      if (XBagKey.PHONE_ID!=null) {
		Phone phone=new Phone(((int)xbag.getValue(XBagKey.PHONE_ID)),(String)(xbag.getValue(XBagKey.NUMBER)),(String)(xbag.getValue(XBagKey.TYPE)),customer);
	    customer.addPhone(phone); 
     }
	       //customer.setFirstName((String) xbag.get(XBagKey.FIRSTNAME));
	        //customer.setFirstName((String) xbag.get(XBagKey.LASTNAME));
	       // customer.setFirstName((String) xbag.get(XBagKey.EMAIL));

	        customerDao.saveCustomerAndGetId(customer);
	    
	}*/
	public void add(XBag xbag) {
	    System.out.println("bag ile add gerçekleşti");
	    Customer customer = new Customer(
	        (int) xbag.getValue(XBagKey.CUSTOMER_ID),
	        (String) xbag.getValue(XBagKey.FIRSTNAME),
	        (String) xbag.getValue(XBagKey.LASTNAME),
	        (String) xbag.getValue(XBagKey.EMAIL)
	    );
	        
	    customer.setId((int) xbag.getValue(XBagKey.CUSTOMER_ID));
	    customer.setFirstName((String) xbag.getValue(XBagKey.FIRSTNAME));
	    customer.setLastName((String) xbag.getValue(XBagKey.LASTNAME));
	    customer.setEmail((String) xbag.getValue(XBagKey.EMAIL)); 
	    customerDao.saveCustomer(customer);


		if (XBagKey.TYPE!=null) {
	        Phone phone = new Phone();
	        phone.setNumber((String) xbag.getValue(XBagKey.NUMBER));
	        phone.setType((String) xbag.getValue(XBagKey.TYPE));
	        phone.setCustomer(customer);
	        PhoneDao phone_dao=new PhoneDao();
	        phone_dao.savePhone(phone);        
	    }
		if (XBagKey.ADDRESS_ID!=null) {
			Address address=new Address();
			address.setCity((String) xbag.getValue(XBagKey.CITY));
			address.setCustomer(customer);
			address.setState((String) xbag.getValue(XBagKey.STATE));
			address.setStreet((String) xbag.getValue(XBagKey.STREET));
			address.setZip((String) xbag.getValue(XBagKey.ZIP));
			AddressDao address_dao=new AddressDao();
			address_dao.saveAddressAndGetId(address);
		}
		if (XBagKey.ACCOUNT_ID!=null) {
			Account account = new Account();
			account.setAccountNumber((String) xbag.getValue(XBagKey.ACCOUNT_NUMBER));
			account.setAccountType((String) xbag.getValue(XBagKey.TYPE));
			account.setCustomer(customer);

			account.setCurrencyCode((String) xbag.getValue(XBagKey.CURRENCY_CODE));
			account.setCreatedAt((java.time.LocalDateTime) xbag.getValue(XBagKey.CREATE_DATE));
			account.setUpdatedAt((java.time.LocalDateTime) xbag.getValue(XBagKey.UPDATE_DATE));

			AccountDao accountdao = new AccountDao();
			accountdao.addAccount(account);

			
		}
	    customerDao.updateCustomer(customer);
	}

	
	public void update(XBag xbag) {
		        Customer customer = new Customer();
		        customer.setId((int) xbag.getValue(XBagKey.CUSTOMER_ID));
		        customer.setFirstName((String) xbag.getValue(XBagKey.FIRSTNAME));
		        customer.setFirstName((String) xbag.getValue(XBagKey.LASTNAME));
		        customer.setFirstName((String) xbag.getValue(XBagKey.EMAIL));
		         customerDao.updateCustomer(customer);
		 
	}
	
	public void delete(XBag xbag) {
		        Customer customer = new Customer();
		customerDao.deleteCustomer(customer.getId());
	}
}
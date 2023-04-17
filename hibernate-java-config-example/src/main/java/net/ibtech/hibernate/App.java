package net.ibtech.hibernate;


import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import net.ibtech.hibernate.command.CommandExecutor;
import net.ibtech.hibernate.dao.AccountDao;
import net.ibtech.hibernate.dao.AddressDao;
import net.ibtech.hibernate.dao.CommandDao;
import net.ibtech.hibernate.dao.CustomerDao;
import net.ibtech.hibernate.dao.PhoneDao;
import net.ibtech.hibernate.model.Account;
import net.ibtech.hibernate.model.Address;
import net.ibtech.hibernate.model.Command;
import net.ibtech.hibernate.model.Customer;
import net.ibtech.hibernate.model.Phone;
import net.ibtech.hibernate.operation.CustomerOperation;
import net.ibtech.hibernate.util.HibernateUtil;
import net.ibtech.hibernate.xbag.XBag;
import net.ibtech.hibernate.xbag.XBagKey;
import net.ibtech.hibernate.xml.XML_Parser;

public class App {
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            String xmlType = "customer";
            XML_Parser parser = new XML_Parser();
            String commandName = parser.parseCommandName(xmlType);
            System.out.println(commandName.trim());
            String commandNameTrim=commandName.trim();
            CommandExecutor execute=new CommandExecutor();
            XBag xbag=new XBag();
            xbag = parser.parseCustomer(xmlType);
         
            execute.executeBag(commandNameTrim, xbag);

            /*CommandDao cmd =new CommandDao();
			Command command = cmd .getCommandByName("add_customer_info");
             //// Bu execute metodu Bag parametresi ile                                                                             
             CommandExecutor executorBag=new CommandExecutor();
              XBag customerBag = new XBag() ;
              customerBag.add(XBagKey.CUSTOMER_ID, 230);
              customerBag.add(XBagKey.FIRSTNAME,"task3" );  
              customerBag.add(XBagKey.LASTNAME,"task3" );  
              customerBag.add(XBagKey.EMAIL,"task3" );
              customerBag.add(XBagKey.PHONE_ID, 3);
              customerBag.add(XBagKey.TYPE, "home");
              customerBag.add(XBagKey.NUMBER, "ll55");
              customerBag.add(XBagKey.STREET, "cadde 3");
              customerBag.add(XBagKey.CITY, "Ankara");
              customerBag.add(XBagKey.STATE, "cadde 3");
              customerBag.add(XBagKey.ZIP, "cadde 3");
              customerBag.add(XBagKey.ADDRESS_ID,3);
              customerBag.add(XBagKey.ACCOUNT_NUMBER, "2586932548");
              customerBag.add(XBagKey.ACCOUNT_TYPE, "vadeli");
              customerBag.add(XBagKey.BALANCE, 50.0);
              customerBag.add(XBagKey.CURRENCY_CODE, "$");
              customerBag.add(XBagKey.ACCOUNT_ID, 55656);
              customerBag.add(XBagKey.CREATE_DATE, LocalDateTime.now());
              customerBag.add(XBagKey.UPDATE_DATE, LocalDateTime.now());

            executorBag.executeBag(command.getCommandName(),customerBag);

            */
        
            /*   
            ///Task2 Test
            List<Customer> customerList = new ArrayList<Customer>();
            List<Address> addressList = new ArrayList<Address>();
            Customer customer = new Customer("helloooo", "", "j", new ArrayList<>(), new ArrayList<>());
            customerList.add(customer);
            Address address =new Address("reff", "deneme", "Ankara", "5748");
            addressList.add(address);
            Phone phone =new Phone("506-135", "home");
            phone.setCustomer(customer);
            executor.executeCommand(command.getCommandName(), customer);
            executor.executeCommand(command2.getCommandName(),  address);
            executor.executeCommand(command3.getCommandName(),  phone);
            ///Task1 Test		
            Customer customer1 = new Customer("John", "Doe", "johndoe@example.com", new ArrayList<>(), new ArrayList<>());
            Customer customer2 = new Customer("Maria", "Phoe", "maria@example.com", new ArrayList<>(), new ArrayList<>());
            // create new address objects and add them to the customer object
            Address address1 = new Address("123 Main St", "Anytown", "NY", "12345");
            Address address3 = new Address("405.Cadde", "Yenimahalle", "Ankara", "5748");

            address1.setCustomer(customer1);
            address3.setCustomer(customer2);

            customer1.getAddresses().add(address1);
            customer2.getAddresses().add(address3);

            Address address2 = new Address("456 Oak St", "Othertown", "CA", "67890");
            address2.setCustomer(customer1);
            customer1.getAddresses().add(address2);

            // create new phone objects and add them to the customer object
            Phone phone1 = new Phone("555-1234", "home");
            phone1.setCustomer(customer1);
            customer1.getPhones().add(phone1);

            Phone phone2 = new Phone("555-5678", "work");
            phone2.setCustomer(customer1);
            customer1.getPhones().add(phone2);

            Phone phone3 = new Phone("505-5678", "work");
            phone3.setCustomer(customer2);
            customer2.getPhones().add(phone3); 
            
            // save the customer object and get its generated id
            Session saveSession = sessionFactory.openSession();
            CustomerDao customerDao = new CustomerDao(saveSession);
            int customerId = customerDao.saveCustomerAndGetId(customer1);
            int customerId2 = customerDao.saveCustomerAndGetId(customer2);

            saveSession.close();

            // create new account object with the generated customer id
            Account account = new Account();
            account.setCustomer(new Customer(customerId)); // set only the id of the customer
            account.setAccountNumber("123456789");
            account.setAccountType("savings");
            account.setBalance(1000);
            account.setCurrencyCode("USD");
            account.setCreatedAt(LocalDateTime.now());
            account.setUpdatedAt(LocalDateTime.now());

            
         // create new account object with the generated customer id
            Account account1 = new Account();
            account1.setCustomer(new Customer(customerId2)); // set only the id of the customer
            account1.setAccountNumber("987654321");
            account1.setAccountType("savings");
            account1.setBalance(1000);
            account1.setCurrencyCode("TL");
            account1.setCreatedAt(LocalDateTime.now());
            account1.setUpdatedAt(LocalDateTime.now());

            
            // save the account object
            Session saveSession2 = sessionFactory.openSession();
            AccountDao accountDao = new AccountDao(saveSession2);
            accountDao.saveAccount(account);
            saveSession2.close();
            
            Session saveSession21 = sessionFactory.openSession();
            AccountDao accountDao1 = new AccountDao(saveSession21);
            accountDao1.saveAccount(account1);
            saveSession21.close();
            
            
            
         // update an existing account object
            Account accountToUpdate = accountDao.getAccountById(7); // assuming account with id 1 exists in the database
            accountToUpdate.setBalance(1500); // update the balance
            Session updateSession = sessionFactory.openSession();
            AccountDao updateAccountDao = new AccountDao(updateSession);
            updateAccountDao.updateAccount(accountToUpdate);
            updateSession.close();
          
            // delete an existing account object
            Account accountToDelete = accountDao.getAccountById(9); // assuming account with id 2 exists in the database
            Session deleteSession = sessionFactory.openSession();
            AccountDao deleteAccountDao = new AccountDao(deleteSession);

            deleteAccountDao.deleteAccount(accountToDelete.getId());
            deleteSession.close();
       */
            
         tx.commit();}
         catch (Exception e) {
        	    // Exception handling code
        	} finally {
        	    // Code that should always be executed
        	}}}
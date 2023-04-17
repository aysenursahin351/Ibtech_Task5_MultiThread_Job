package net.ibtech.hibernate.model;

import javax.persistence.*;

import net.ibtech.hibernate.xbag.XBagKey;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

	private String email;
    
	 public Customer() {
			super();
		}
	 public Customer(int id, String firstName, String lastName, String email) {
	        this.id = id;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	    }

 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)

    private List<Address> addresses = new ArrayList<>();

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();
	public void addAddress(Address addressInfo) {
		if (this.addresses == null)
			this.addresses = new ArrayList<Address>();
		this.addresses.add(addressInfo);
		addressInfo.setCustomer(this);
	}
	public void addPhone(Phone phoneNumber) {
		if (this.phones == null){
			this.phones = new ArrayList<Phone>();
		}
		this.phones.add(phoneNumber);
		phoneNumber.setCustomer(this);
	}
    public void addAccount(Account account) {
    	if (this.accounts == null){
			this.accounts = new ArrayList<Account>();
		}
        accounts.add(account);
        account.setCustomer(this);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
	
   
    // getters and setters
}

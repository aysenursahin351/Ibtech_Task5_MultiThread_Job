package net.ibtech.hibernate.model;
import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String street;

    private String city;

    private String state;

    private String zip;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
       
    public Address(String street, String city, String state, String zip, Customer customer) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
		this.customer = customer;

    }

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

    
    // getters and setters
}

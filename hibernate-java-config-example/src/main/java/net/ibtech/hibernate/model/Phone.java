package net.ibtech.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    public Phone(int id, String number, String type, Customer customer) {
		super();
		this.id = id;
		this.number = number;
		this.type = type;
		this.customer = customer;
	}

	private String number;
    private String type;
 
/*
    public Phone(String number, String type) {
        this.number = number;
        this.type = type;
    }

  */ 

	public Phone() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

	

    // getters and setters
}
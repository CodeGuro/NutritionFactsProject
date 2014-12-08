package resources;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@NamedQuery( name = "Address.findAll", query = "SELECT a FROM Address a" )
public class Address implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int addressid;

	private String city;

	private String country;

	@Column( name = "`State/Province`" )
	private String state_Province;

	private String street;

	private String suburb;

	private String zip;

	// bi-directional many-to-one association to User
	@OneToMany( mappedBy = "address" )
	private List< User > users;

	public Address()
	{
	}

	public int getAddressid()
	{
		return this.addressid;
	}

	public void setAddressid( int addressid )
	{
		this.addressid = addressid;
	}

	public String getCity()
	{
		return this.city;
	}

	public void setCity( String city )
	{
		this.city = city;
	}

	public String getCountry()
	{
		return this.country;
	}

	public void setCountry( String country )
	{
		this.country = country;
	}

	public String getState_Province()
	{
		return this.state_Province;
	}

	public void setState_Province( String state_Province )
	{
		this.state_Province = state_Province;
	}

	public String getStreet()
	{
		return this.street;
	}

	public void setStreet( String street )
	{
		this.street = street;
	}

	public String getSuburb()
	{
		return this.suburb;
	}

	public void setSuburb( String suburb )
	{
		this.suburb = suburb;
	}

	public String getZip()
	{
		return this.zip;
	}

	public void setZip( String zip )
	{
		this.zip = zip;
	}

	public List< User > getUsers()
	{
		return this.users;
	}

	public void setUsers( List< User > users )
	{
		this.users = users;
	}

	public User addUser( User user )
	{
		getUsers().add( user );
		user.setAddress( this );

		return user;
	}

	public User removeUser( User user )
	{
		getUsers().remove( user );
		user.setAddress( null );

		return user;
	}

}
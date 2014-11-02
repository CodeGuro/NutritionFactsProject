package resources;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class RegistrationBean
{
	private String username;
	private String password;
	private String confirmpassword;
	private String firstname;
	private String lastname;
	private Date dob;
	private String email;
	private String confirmemail;
	private String street;
	private String suburb;
	private String city;
	private String zip;
	private String province;
	private String country;

	@EJB
	RegistrationEAO service;
	private String[] selectedGroups;

	public String getUsername()
	{
		return username;
	}

	public void setUsername( String username )
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public String getConfirmpassword()
	{
		return confirmpassword;
	}

	public void setConfirmpassword( String confirmpassword )
	{
		this.confirmpassword = confirmpassword;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname( String firstname )
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname( String lastname )
	{
		this.lastname = lastname;
	}

	public Date getDob()
	{
		return dob;
	}

	public void setDob( Date dob )
	{
		this.dob = dob;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public String getConfirmemail()
	{
		return confirmemail;
	}

	public void setConfirmemail( String confirmemail )
	{
		this.confirmemail = confirmemail;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet( String street )
	{
		this.street = street;
	}

	public String getSuburb()
	{
		return suburb;
	}

	public void setSuburb( String suburb )
	{
		this.suburb = suburb;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity( String city )
	{
		this.city = city;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip( String zip )
	{
		this.zip = zip;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince( String province )
	{
		this.province = province;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry( String country )
	{
		this.country = country;
	}

	public String[] getSelectedGroups()
	{
		return selectedGroups;
	}

	public void setSelectedGroups( String[] selectedGroups )
	{
		this.selectedGroups = selectedGroups;
	}

	private void addMessage( FacesMessage message )
	{
		FacesContext.getCurrentInstance().addMessage( null, message );
	}

	public String addUser()
	{
		if( service.persistUser( this ) )
		{
			addMessage( new FacesMessage( FacesMessage.SEVERITY_INFO,
				"User Registration successful!", null ) );
			return "success";
		}
		addMessage( new FacesMessage( FacesMessage.SEVERITY_ERROR,
			"User Registration failed.", null ) );
		return "failure";
	}
}

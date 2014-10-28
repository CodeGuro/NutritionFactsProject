package resources;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean( name = "regBean" )
@SessionScoped()
public class RegistrationBean
{
	private String username;
	private String password;
	private String confirmpassword;
	private String firstname;
	private String lastname;
	private String dob;
	private String email;
	private String confirmemail;
	private String street;
	private String suburb;
	private String city;
	private String zip;
	private String province;
	private String country;

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

	public String getDob()
	{
		return dob;
	}

	public void setDob( String dob )
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
		String msg = "";
		try
		{
			if( !this.confirmpassword.equals( this.password ) )
				msg += "Passwords does not match. ";
			if( !this.confirmemail.equals( this.email ) )
				msg += "Emails does not match. ";
			if( msg.length() > 0 )
				throw new Exception( msg );
		}
		catch( Exception e )
		{
			addMessage( new FacesMessage( FacesMessage.SEVERITY_ERROR,
					"Registration failure. " + msg, null ) );
			return "failure";
		}
		addMessage( new FacesMessage( FacesMessage.SEVERITY_INFO,
				"User Registration Successful!!!", null ) );
		return "success";
	}
}

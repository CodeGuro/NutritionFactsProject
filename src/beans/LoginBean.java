package beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class LoginBean
{
	private String userName;
	private String userPass;

	@EJB
	RegistrationEAO registrationEAO;

	public String getUserName()
	{
		return userName;
	}

	public void setUserName( String userName )
	{
		this.userName = userName;
	}

	public String getUserPass()
	{
		return userPass;
	}

	public void setUserPass( String userPass )
	{
		this.userPass = userPass;
	}

	public String login()
	{
		if( registrationEAO.CheckRegisteredUser( userName, userPass ) )
			return "success";
		FacesContext.getCurrentInstance().addMessage(
			null,
			new FacesMessage( FacesMessage.SEVERITY_ERROR,
				"Invalid username or password", null ) );
		return "failure";
	}

	public RegistrationEAO getRegistrationEAO()
	{
		return registrationEAO;
	}

	public void setRegistrationEAO( RegistrationEAO registrationEAO )
	{
		this.registrationEAO = registrationEAO;
	}
}

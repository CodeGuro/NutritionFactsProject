package resources;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator
{
	@Override
	public void validate( FacesContext context, UIComponent uiComp, Object val )
		throws ValidatorException
	{
		UIInput Input = (UIInput)uiComp.getAttributes().get( "email" );
		String email = Input.getValue().toString();

		if( email.indexOf( "@" ) < 0
			|| email.indexOf( "@" ) == email.length() - 1 )
			throw new ValidatorException( new FacesMessage(
				FacesMessage.SEVERITY_ERROR,
				"Invalid E-mail. Please use the form \"user@domain\".", null ) );
		else if( !val.toString().equals( email ) )
			throw new ValidatorException( new FacesMessage(
				FacesMessage.SEVERITY_ERROR, "E-mail fields do not match.",
				null ) );
	}
}

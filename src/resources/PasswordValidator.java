package resources;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class PasswordValidator implements Validator
{
	@Override
	public void validate( FacesContext context, UIComponent uiComp, Object val )
		throws ValidatorException
	{
		UIInput input = (UIInput)uiComp.getAttributes().get( "password" );
		String pass = input.getValue().toString();
		
		if( !pass.equals( val.toString() ) )
			throw new ValidatorException( new FacesMessage( FacesMessage.SEVERITY_ERROR, "Password fields do not match.", null ) );
	}
}

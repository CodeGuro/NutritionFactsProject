package beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import resources.Food;
import resources.Menu;

public class AdminSession
{
	@EJB
	private AdminResourceEAO resources;
	private Menu workingMenu;
	private Food workingFood;

	public AdminResourceEAO getResources()
	{
		return resources;
	}

	public void setResources( AdminResourceEAO resources )
	{
		this.resources = resources;
	}

	public void setWorkingMenu( Menu menu )
	{
		this.workingMenu = menu;
	}
	
	public Menu getWorkingMenu()
	{
		return workingMenu;
	}

	public static void raiseErrorMessage( String str )
	{
		FacesContext.getCurrentInstance().addMessage( null,
			new FacesMessage( FacesMessage.SEVERITY_ERROR, str, null ) );
	}

	
	public Food getWorkingFood()
	{
		return workingFood;
	}

	
	public void setWorkingFood( Food workingFood )
	{
		this.workingFood = workingFood;
	}
}

package beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import resources.Food;
import resources.Ingredient;
import resources.Menu;
import resources.Nutrition;

public class AdminSession
{
	@EJB
	private AdminResourceEAO resources;
	private Menu workingMenu;
	private Food workingFood;
	private Ingredient workingIngredient;

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

	public Ingredient getWorkingIngredient()
	{
		return workingIngredient;
	}

	public void setWorkingIngredient( Ingredient workingIngredient )
	{
		this.workingIngredient = workingIngredient;
	}
	
	public Nutrition getWorkingNutrition()
	{
		return this.workingIngredient.getNutrition();
	}

	public static void raiseInfoMessage( String string )
	{
		FacesContext.getCurrentInstance().addMessage( null,
			new FacesMessage( FacesMessage.SEVERITY_INFO, string, null ) );
	}
}

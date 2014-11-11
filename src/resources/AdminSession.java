package resources;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class AdminSession
{
	private int menuId;
	private String menuName;
	int num = 1;

	@EJB
	private AdminResourceEAO resources;

	public AdminResourceEAO getResources()
	{
		return resources;
	}

	public void setResources( AdminResourceEAO resources )
	{
		this.resources = resources;
	}

	public List< Ingredient > getIngredients()
	{
		return resources.getIngredients();
	}

	public List< Menu > getMenus()
	{
		return resources.getMenus();
	}

	public List< Nutrition > getNutritions()
	{
		return resources.getNutritions();
	}

	public List< Food > getFoods()
	{
		return resources.getFoods();
	}

	public String deleteMenu()
	{
		if( resources.removeMenu( this.menuId ) )
			return "delSuccess";

		FacesContext.getCurrentInstance().addMessage(
			null,
			new FacesMessage( FacesMessage.SEVERITY_ERROR,
				"Error Deleting Entry", null ) );
		return "delFail";
	}

	public int getMenuId()
	{
		return menuId;
	}

	public void setMenuId( int menuId )
	{
		this.menuId = menuId;
	}

	public String getMenuName()
	{
		return menuName;
	}

	public void setMenuName( String menuName )
	{
		this.menuName = menuName;
	}

	public String createNewMenu()
	{
		Menu menu = new Menu();
		menu.setName( menuName );
		resources.persistMenu( menu );
		return "success";
	}
}

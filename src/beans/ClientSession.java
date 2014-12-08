package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import resources.Food;
import resources.Ingredient;
import resources.Menu;

/**
 * Session Bean implementation class ClientSession
 */
@Stateful
@LocalBean
public class ClientSession
{
	@EJB
	private ResourceEAO resources;
	
	private Menu selectedMenu;

	public ResourceEAO getResources()
	{
		return resources;
	}

	public void setResources( ResourceEAO resources )
	{
		this.resources = resources;
	}
	
	public List< Menu > getMenus()
	{
		return resources.getMenus();
	}

	public Menu getSelectedMenu()
	{
		return selectedMenu;
	}

	public String setSelectedMenu( Menu menuItem )
	{
		if( menuItem == null )
			return "failure";
		this.selectedMenu = menuItem;
		return "goToFoodCustomizationPage";
	}
	
	public List< Food > getFoodsFromSelectedMenu()
	{
		return this.getSelectedMenu().getFoods();
	}
	
	public String getAllergensFor( Food food )
	{
		String result = "";
		String delim = "";
		
		for( Ingredient ingred : food.getIngredients() )
		{
			result += delim + ingred.getAllergen();
			delim = ", ";
		}
		
		if( result.length() == 0 )
			result = "none";
		return result;
	}
	
}

package resources;

import java.util.List;

import javax.ejb.EJB;

public class AdminSession
{
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
}

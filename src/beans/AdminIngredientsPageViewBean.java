package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import resources.Ingredient;
import resources.Nutrition;

@Stateful
@LocalBean
public class AdminIngredientsPageViewBean
{
	@EJB
	private AdminResourceEAO resources;
	private AdminSession sessionBean;
	private int ingredId;
	private String ingredName;

	public AdminResourceEAO getResources()
	{
		return resources;
	}

	public void setResources( AdminResourceEAO resources )
	{
		this.resources = resources;
	}

	public AdminSession getSessionBean()
	{
		return sessionBean;
	}

	public void setSessionBean( AdminSession sessionBean )
	{
		this.sessionBean = sessionBean;
	}
	
	public List< Ingredient > getIngredients()
	{
		return resources.getIngredients();
	}

	
	public int getIngredId()
	{
		return ingredId;
	}

	
	public void setIngredId( int ingredId )
	{
		this.ingredId = ingredId;
	}

	public String getIngredName()
	{
		return ingredName;
	}

	public void setIngredName( String ingredName )
	{
		this.ingredName = ingredName;
	}
	
	public String createIngred()
	{
		Ingredient ingredient = new Ingredient();
		Nutrition nutrition = new Nutrition();
		nutrition.setIngredient( ingredient );
		ingredient.setNutrition( nutrition );
		ingredient.setIngredName( ingredName );
		ingredient.setRefCount( 0 );
		ingredient.setPrice( 0.0 );
		resources.persistIngredient( ingredient );
		return "success";
	}
	
	public String deleteIngred()
	{
		resources.removeIngredient( ingredId );
		return "success";
	}
}

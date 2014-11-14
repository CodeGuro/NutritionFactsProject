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
	private String editText;
	
	public void setEditText( String editText )
	{
		this.editText = editText;
	}
	
	public String getEditText()
	{
		return this.editText;
	}

	public String editIngredientName()
	{
		Ingredient ingred = resources.getIngredient( ingredId );
		ingred.setIngredName( editText );
		resources.updateIngredient( ingred );
		editText = null;
		return "success";
	}
	
	public String editIngredientAllergen()
	{
		Ingredient ingred = resources.getIngredient( ingredId );
		ingred.setAllergen( editText );
		resources.updateIngredient( ingred );
		editText = null;
		return "success";
	}
	
	public String editIngredientPrice()
	{
		try
		{
			Ingredient ingred = resources.getIngredient( ingredId );
			ingred.setPrice( Double.valueOf( editText ) );
			resources.updateIngredient( ingred );
			editText = null;
			return "success";
		}
		catch( Exception e )
		{
			AdminSession.raiseErrorMessage( "The price must be a real number!" );
			return "failure";
		}
	}
	
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
		return this.editText;
	}
	
	public String editNutritions()
	{
		sessionBean.setWorkingIngredient( resources.getIngredient( ingredId ) );
		return "goToEditPage";
	}
	
	public String createIngred()
	{
		Ingredient ingredient = new Ingredient();
		Nutrition nutrition = new Nutrition();
		nutrition.setIngredient( ingredient );
		ingredient.setNutrition( nutrition );
		ingredient.setIngredName( this.getIngredName() );
		ingredient.setRefCount( 0 );
		ingredient.setPrice( 0.0 );
		resources.persistIngredient( ingredient );
		this.editText = null;
		return "success";
	}
	
	public String deleteIngred()
	{
		resources.removeIngredient( ingredId );
		return "success";
	}
}

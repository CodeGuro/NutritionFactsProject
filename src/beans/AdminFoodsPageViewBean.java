package beans;

import java.util.List;

import javax.ejb.EJB;

import resources.Food;
import resources.Ingredient;
import resources.Menu;

public class AdminFoodsPageViewBean
{
	@EJB
	private AdminResourceEAO resources;
	private AdminSession sessionBean;
	private String foodName;
	private int foodId;
	private int ingredId;
	
	public String addToMenu()
	{
		Menu menu = sessionBean.getWorkingMenu();
		Food food = resources.getFood( foodId );
		menu.getFoods().add( food );
		food.getMenus().add( menu );
		resources.updateMenu( menu );
		return "success";
	}
	
	public String removeFromMenu()
	{
		Menu menu = sessionBean.getWorkingMenu();
		for( int i = 0; i < menu.getFoods().size(); ++i )
		{
			if( menu.getFoods().get( i ).getFoodid() == foodId )
			{
				for( int j = 0; j < menu.getFoods().size(); ++j )
					if( menu.getFoods().get( j ).getFoodid() == foodId )
						menu.getFoods().remove( j );
				
				resources.updateMenu( menu );
				return "success";
			}
		}
		
		return "failure";
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

	public List< Food > getFoods()
	{
		return resources.getFoods();
	}
	
	public List< Ingredient > getCurrentFoodIngredientsList()
	{
		return this.sessionBean.getWorkingFood().getIngredients();
	}
	
	public List< Ingredient > getCurrentFoodIngredientsInvList()
	{
		return this.sessionBean.getWorkingFood()
			.getUnincludedIngredients( this.getResources().getIngredients() );
	}
	
	public String getFoodName()
	{
		return foodName;
	}
	
	public void setFoodName( String foodName )
	{
		this.foodName = foodName;
	}
	
	public int getFoodId()
	{
		return foodId;
	}
	
	public void setFoodId( int foodId )
	{
		this.foodId = foodId;
	}
	
	public int getIngredId()
	{
		return ingredId;
	}

	public void setIngredId( int ingredId )
	{
		this.ingredId = ingredId;
	}

	public String createFood()
	{
		Food food = new Food();
		food.setFoodName( getFoodName() );
		food.setRefCount( 0 );
		resources.persistFood( food );
		return "createSuccess";
	}
	
	public String editFood()
	{
		return "goToEditPage";
	}
	
	public String deleteFood()
	{
		resources.removeFood( foodId );
		return "delSuccess";
	}
	
	public String prepareNewIngredient()
	{
		sessionBean.setWorkingFood( resources.getFood( foodId ) );
		return "goToEditPage";
	}

	public String addNewIngredient()
	{
		Food food = sessionBean.getWorkingFood();
		for( Ingredient ingred : resources.getIngredients() )
		{
			if( ingred.getIngredientid() == ingredId )
			{
				ingred.getFoods().add( food );
				food.getIngredients().add( ingred );
				resources.updateFood( food );
				resources.updateIngredient( ingred );
				break;
			}
		}
		sessionBean.setWorkingFood( food );
		
		return "addIngSuccess";
	}
	
	public String removeOldIngredient()
	{
		
		Food food = sessionBean.getWorkingFood();
		for( Ingredient ingred : food.getIngredients() )
		{
			if( ingred.getIngredientid() == ingredId )
			{
				ingred.getFoods().remove( food );
				food.getIngredients().remove( ingred );
				resources.updateFood( food );
				resources.updateIngredient( ingred );
				sessionBean.setWorkingFood( food );
				break;
			}
		}
		
		return "remIngSuccess";
	}
}

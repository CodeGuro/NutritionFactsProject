package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import resources.Food;
import resources.Ingredient;
import resources.Menu;
import resources.Nutrition;

/**
 * Session Bean implementation class ClientSession
 */
@Stateful
@LocalBean
public class ClientSession
{
	private class IngredientContainer
	{
		private Ingredient ingredient;
		public boolean included = true;
		public IngredientContainer( Ingredient ing )
		{
			this.ingredient = ing;
		}
		public Ingredient getIngredient()
		{
			return this.ingredient;
		}
	}
	private class FoodContainer
	{
		private Food food;
		private List< IngredientContainer > ingreds = new ArrayList< IngredientContainer >();
		public boolean included = true;
		public FoodContainer( Food food )
		{
			this.food = food;
			for( Ingredient ingred : food.getIngredients() )
				ingreds.add( new IngredientContainer( ingred ) );
		}
		private Food getFood()
		{
			return this.food;
		}
		private List< IngredientContainer > getIngredients()
		{
			return this.ingreds;
		}
	}
	private class MenuContainer
	{
		private Menu menu;
		private List< FoodContainer > foods = new ArrayList< FoodContainer >();
		private Menu getMenu()
		{
			return menu;
		}
		public List< FoodContainer > getFoods()
		{
			return this.foods;
		}
		public MenuContainer( Menu menu )
		{
			this.menu = menu;
			for( Food food : menu.getFoods() )
			{
				this.foods.add( new FoodContainer( food ) );
			}
		}
	}
	@EJB
	private ResourceEAO resources;
	
	private MenuContainer selectedMenu;
	private FoodContainer selectedFood;
	private double price;

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
		return selectedMenu.getMenu();
	}

	public String setSelectedMenu( Menu menuItem )
	{
		if( menuItem == null )
			return "failure";
		this.selectedMenu = new MenuContainer( menuItem );
		
		return "goToFoodCustomizationPage";
	}
	
	public List< Food > getFoodsFromSelectedMenu()
	{
		List< Food > foods = new ArrayList< Food >();
		for( FoodContainer fc : selectedMenu.getFoods())
			if( fc.included )
				foods.add( fc.getFood() );
		return foods;
	}
	
	public List< Food > getOmittedFoodsFromSelectedMenu()
	{
		List< Food > foods = new ArrayList< Food >();
		for( FoodContainer fc : selectedMenu.getFoods())
			if( !fc.included )
				foods.add( fc.getFood() );
		return foods;
	}
	
	public String removeFood( Food food )
	{
		for( FoodContainer fc : selectedMenu.foods )
		{
			if( fc.getFood().getFoodid() == food.getFoodid() )
			{
				fc.included = false;
				return "success";
			}
		}
		return "failure";
	}
	
	public String addFood( Food food )
	{
		for( FoodContainer fc : selectedMenu.foods )
		{
			if( fc.getFood().getFoodid() == food.getFoodid() )
			{
				fc.included = true;
				return "success";
			}
		}
		return "failure";
	}
	
	public String getAllergensFor( Food food )
	{
		String result = "";
		String delim = "";
		
		for( Ingredient ingred : food.getIngredients() )
		{
			if( ingred.getAllergen() != null )
			{
				result += delim + ingred.getAllergen();
				delim = ", ";
			}
		}
		
		if( result.length() == 0 )
			result = "no allergens";
		return result;
	}

	public Food getSelectedFood()
	{
		return selectedFood.getFood();
	}

	public String setSelectedFood( Food selectedFood )
	{
		for( FoodContainer ct : selectedMenu.getFoods() )
		{
			if( ct.getFood().getFoodid() == selectedFood.getFoodid() )
			{
				this.selectedFood = ct;
				return "goToIngredientCustomizationPage";
			}
		}
		
		return "failure";
	}
	
	public List< Ingredient > getIngredientsFromSelectedFood()
	{
		List< Ingredient > result = new ArrayList< Ingredient >();
		
		for( IngredientContainer ingred : selectedFood.getIngredients() )
		{
			if( ingred.included )
				result.add( ingred.getIngredient() );
		}
		
		return result;
	}
	
	public List< Ingredient > getIngredientsFromFood( Food food )
	{
		for( FoodContainer ct : selectedMenu.getFoods() )
		{
			if( ct.getFood().getFoodid() == food.getFoodid() )
			{
				this.selectedFood = ct;
				break;
			}
		}
		
		
		List< Ingredient > result = new ArrayList< Ingredient >();
		
		for( IngredientContainer ingred : selectedFood.getIngredients() )
		{
			if( ingred.included )
				result.add( ingred.getIngredient() );
		}
		
		return result;
	}
	
	public List< Ingredient > getOmittedIngredientsFromSelectedFood()
	{
		List< Ingredient > result = new ArrayList< Ingredient >();
		
		for( IngredientContainer ingred : selectedFood.getIngredients() )
		{
			if( !ingred.included )
				result.add( ingred.getIngredient() );
		}
		
		return result;
	}
	
	public String addIngredient( Ingredient ing )
	{
		for( IngredientContainer ct : selectedFood.getIngredients() )
		{
			if( ing.getIngredientid() == ct.getIngredient().getIngredientid() )
			{
				ct.included = true;
				return "success";
			}
		}
		return "failure";
	}
	
	public String removeIngredient( Ingredient ing )
	{
		for( IngredientContainer ct : selectedFood.getIngredients() )
		{
			if( ing.getIngredientid() == ct.getIngredient().getIngredientid() )
			{
				ct.included = false;
				return "success";
			}
		}
		return "failure";
	}
	
	public Nutrition getNutritionFacts()
	{
		Nutrition nutrition = new Nutrition();
		this.price = 0.0;
		
		for( FoodContainer foodct : selectedMenu.getFoods() )
		{
			if( foodct.included )
			{
				for( IngredientContainer ingredct : foodct.getIngredients() )
				{
					if( ingredct.included )
					{
						Nutrition current = ingredct.getIngredient().getNutrition();
						nutrition.setCalories( nutrition.getCalories() + current.getCalories() );
						nutrition.setFat( nutrition.getFat() + current.getFat() );
						nutrition.setCholesterol( nutrition.getCholesterol() + current.getCholesterol() );
						nutrition.setCarbohydrates( nutrition.getCarbohydrates() + current.getCarbohydrates() );
						nutrition.setSodium( nutrition.getSodium() + current.getSodium() );
						nutrition.setSugar( nutrition.getSugar() + current.getSugar() );
						nutrition.setProtein( nutrition.getProtein() + current.getProtein() );
						price += ingredct.getIngredient().getPrice();
					}
				}
			}
		}
		
		return nutrition;
	}
	
	public double getPrice()
	{
		return price;
	}
	
}

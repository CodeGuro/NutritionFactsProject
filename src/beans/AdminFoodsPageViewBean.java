package beans;

import java.util.List;

import javax.ejb.EJB;

import resources.Food;

public class AdminFoodsPageViewBean
{
	@EJB
	private AdminResourceEAO resources;
	private AdminSession sessionBean;
	private String foodName;
	private int foodId;

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

	List< Food > getFoods()
	{
		return resources.getFoods();
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
}

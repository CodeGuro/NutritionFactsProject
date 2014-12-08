package beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import resources.Nutrition;

public class AdminNutritionalFactsPageViewBean
{
	private int calories;

	private int fat;

	private int protein;

	private int sodium;

	private int sugar;
	
	private int carbohydrates;
	
	private int cholesterol;
	
	@EJB
	ResourceEAO resources;
	
	private AdminSession sessionBean;
	
	@PostConstruct
	private void init()
	{
		Nutrition nut = sessionBean.getWorkingNutrition();
		setCalories( nut.getCalories() );
		setFat( nut.getFat() );
		setProtein( nut.getProtein() );
		setSodium( nut.getSodium() );
		setCarbohydrates( nut.getCarbohydrates() );
		setCholesterol( nut.getCholesterol() );
		this.setSugar( nut.getSugar() );
	}

	public AdminSession getSessionBean()
	{
		return sessionBean;
	}

	public void setSessionBean( AdminSession sessionBean )
	{
		this.sessionBean = sessionBean;
	}

	public Integer getCalories()
	{
		return calories;
	}

	public void setCalories( Integer calories )
	{
		this.calories = calories;
	}

	public Integer getFat()
	{
		return fat;
	}

	public void setFat( Integer fat )
	{
		this.fat = fat;
	}

	public Integer getProtein()
	{
		return protein;
	}

	public void setProtein( Integer protein )
	{
		this.protein = protein;
	}

	public Integer getSodium()
	{
		return sodium;
	}

	public void setSodium( Integer sodium )
	{
		this.sodium = sodium;
	}

	public Integer getSugar()
	{
		return sugar;
	}

	public void setSugar( Integer sugar )
	{
		this.sugar = sugar;
	}

	public Integer getCarbohydrates()
	{
		return carbohydrates;
	}

	public void setCarbohydrates( Integer carbohydrates )
	{
		this.carbohydrates = carbohydrates;
	}

	public Integer getCholesterol()
	{
		return cholesterol;
	}

	public void setCholesterol( Integer cholesterol )
	{
		this.cholesterol = cholesterol;
	}
	
	public String submit()
	{
		Nutrition nut = sessionBean.getWorkingNutrition();
		nut.setCalories( calories );
		nut.setCarbohydrates( carbohydrates );
		nut.setCholesterol( cholesterol );
		nut.setFat( fat );
		nut.setProtein( protein );
		nut.setSodium( sodium );
		nut.setSugar( sugar );
		
		resources.updateNutrition( nut );
		
		AdminSession.raiseInfoMessage( "Updated!" );
		
		return "success";
	}
}

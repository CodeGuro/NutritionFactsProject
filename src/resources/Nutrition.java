package resources;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the nutrition database table.
 * 
 */
@Entity
@NamedQuery( name = "Nutrition.findAll", query = "SELECT n FROM Nutrition n" )
public class Nutrition implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int nutritionid;

	private Integer calories;

	private Integer fat;

	private Integer protein;

	private Integer sodium;

	private Integer sugar;
	
	private Integer carbohydrates;
	
	private Integer cholesterol;

	// bi-directional one-to-one association to Ingredient
	@OneToOne( cascade = { CascadeType.ALL } )
	@PrimaryKeyJoinColumn( name = "nutritionid" )
	private Ingredient ingredient;

	public Nutrition()
	{
	}

	public int getNutritionid()
	{
		return this.nutritionid;
	}

	public void setNutritionid( int nutritionid )
	{
		this.nutritionid = nutritionid;
	}

	public Integer getCalories()
	{
		return this.calories;
	}

	public void setCalories( Integer calories )
	{
		this.calories = calories;
	}

	public Integer getFat()
	{
		return this.fat;
	}

	public void setFat( Integer fat )
	{
		this.fat = fat;
	}
	
	public Integer getProtein()
	{
		return this.protein;
	}

	public void setProtein( Integer protein )
	{
		this.protein = protein;
	}

	public Integer getSodium()
	{
		return this.sodium;
	}

	public void setSodium( Integer sodium )
	{
		this.sodium = sodium;
	}

	public int getSugar()
	{
		return this.sugar;
	}

	public void setSugar( int sugar )
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

	public Ingredient getIngredient()
	{
		return this.ingredient;
	}

	public void setIngredient( Ingredient ingredient )
	{
		this.ingredient = ingredient;
	}

}
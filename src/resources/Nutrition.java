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

	private int calories;

	private int carbohydrates;

	private int cholesterol;

	private int fat;

	private int protein;

	private int sodium;

	private int sugar;

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

	public int getCalories()
	{
		return this.calories;
	}

	public void setCalories( int calories )
	{
		this.calories = calories;
	}

	public int getCarbohydrates()
	{
		return this.carbohydrates;
	}

	public void setCarbohydrates( int carbohydrates )
	{
		this.carbohydrates = carbohydrates;
	}

	public int getCholesterol()
	{
		return this.cholesterol;
	}

	public void setCholesterol( int cholesterol )
	{
		this.cholesterol = cholesterol;
	}

	public int getFat()
	{
		return this.fat;
	}

	public void setFat( int fat )
	{
		this.fat = fat;
	}

	public int getProtein()
	{
		return this.protein;
	}

	public void setProtein( int protein )
	{
		this.protein = protein;
	}

	public int getSodium()
	{
		return this.sodium;
	}

	public void setSodium( int sodium )
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

	public Ingredient getIngredient()
	{
		return this.ingredient;
	}

	public void setIngredient( Ingredient ingredient )
	{
		this.ingredient = ingredient;
	}

}
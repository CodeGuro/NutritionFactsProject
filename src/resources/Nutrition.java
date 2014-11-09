package resources;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the nutrition database table.
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

	private Integer cholesterol;

	@Column( name = "cholesterol_pct" )
	private Double cholesterolPct;

	private Integer fat;

	@Column( name = "fat_pct" )
	private Double fatPct;

	private Integer protein;

	private Integer sodium;

	@Column( name = "sodium_pct" )
	private Double sodiumPct;

	private Integer sugars;

	// bi-directional one-to-one association to Ingredient
	@OneToOne
	@JoinColumn( name = "nutritionid", insertable =  false, updatable = false )
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

	public Integer getCholesterol()
	{
		return this.cholesterol;
	}

	public void setCholesterol( Integer cholesterol )
	{
		this.cholesterol = cholesterol;
	}

	public Double getCholesterolPct()
	{
		return this.cholesterolPct;
	}

	public void setCholesterolPct( Double cholesterolPct )
	{
		this.cholesterolPct = cholesterolPct;
	}

	public Integer getFat()
	{
		return this.fat;
	}

	public void setFat( Integer fat )
	{
		this.fat = fat;
	}

	public Double getFatPct()
	{
		return this.fatPct;
	}

	public void setFatPct( Double fatPct )
	{
		this.fatPct = fatPct;
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

	public Double getSodiumPct()
	{
		return this.sodiumPct;
	}

	public void setSodiumPct( Double sodiumPct )
	{
		this.sodiumPct = sodiumPct;
	}

	public Integer getSugars()
	{
		return this.sugars;
	}

	public void setSugars( Integer sugars )
	{
		this.sugars = sugars;
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
package resources;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ingredient database table.
 * 
 */
@Entity
@NamedQuery( name = "Ingredient.findAll", query = "SELECT i FROM Ingredient i" )
public class Ingredient implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int ingredientid;

	private String allergen;

	private String ingredName;

	private Double price;

	private int refCount;

	// bi-directional many-to-one association to Food
	@ManyToOne
	@JoinColumn( name = "keyToFood" )
	private Food food;

	// bi-directional one-to-one association to Nutrition
	@OneToOne( mappedBy = "ingredient", cascade = { CascadeType.ALL } )
	private Nutrition nutrition;

	public Ingredient()
	{
	}

	public int getIngredientid()
	{
		return this.ingredientid;
	}

	public void setIngredientid( int ingredientid )
	{
		this.ingredientid = ingredientid;
	}

	public String getAllergen()
	{
		return this.allergen;
	}

	public void setAllergen( String allergen )
	{
		this.allergen = allergen;
	}

	public String getIngredName()
	{
		return this.ingredName;
	}

	public void setIngredName( String ingredName )
	{
		this.ingredName = ingredName;
	}

	public Double getPrice()
	{
		return this.price;
	}

	public void setPrice( Double price )
	{
		this.price = price;
	}

	public int getRefCount()
	{
		return this.refCount;
	}

	public void setRefCount( int refCount )
	{
		this.refCount = refCount;
	}

	public Food getFood()
	{
		return this.food;
	}

	public void setFood( Food food )
	{
		this.food = food;
	}

	public Nutrition getNutrition()
	{
		return this.nutrition;
	}

	public void setNutrition( Nutrition nutrition )
	{
		this.nutrition = nutrition;
	}

}
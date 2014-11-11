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

	private String name;

	private Double price;

	private Integer references;

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

	public String getName()
	{
		return this.name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public Double getPrice()
	{
		return this.price;
	}

	public void setPrice( Double price )
	{
		this.price = price;
	}

	public Integer getReferences()
	{
		return this.references;
	}

	public void setReferences( Integer references )
	{
		this.references = references;
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
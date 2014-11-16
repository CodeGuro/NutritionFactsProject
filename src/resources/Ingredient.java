package resources;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

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

	private double price;

	private int refCount;

	// bi-directional many-to-many association to Food
	@ManyToMany( cascade = { CascadeType.ALL } )
	@JoinTable( name = "food_has_ingredient", joinColumns = { @JoinColumn( name = "ingredient_ingredientid" ) }, inverseJoinColumns = { @JoinColumn( name = "food_foodid" ) } )
	private List< Food > foods;

	// bi-directional one-to-one association to Nutrition
	@OneToOne( mappedBy = "ingredient" )
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

	public double getPrice()
	{
		return this.price;
	}

	public void setPrice( double d )
	{
		this.price = d;
	}

	public int getRefCount()
	{
		return this.refCount;
	}

	public void setRefCount( int refCount )
	{
		this.refCount = refCount;
	}

	public List< Food > getFoods()
	{
		return this.foods;
	}

	public void setFoods( List< Food > foods )
	{
		this.foods = foods;
	}

	public Nutrition getNutrition()
	{
		return this.nutrition;
	}

	public void setNutrition( Nutrition nutrition )
	{
		this.nutrition = nutrition;
	}
	
	public String getListOfFoodsStr()
	{
		String res = "";
		String delim = "";
		for( Food food : getFoods() )
		{
			if( food != null )
				if( food.getFoodName() != null )
				{
					res += delim + food.getFoodName();
					delim = ", ";
				}
		}
		return res;
	}

}
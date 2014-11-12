package resources;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the food database table.
 * 
 */
@Entity
@NamedQuery( name = "Food.findAll", query = "SELECT f FROM Food f" )
public class Food implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int foodid;

	private String foodName;

	private int refCount;

	// bi-directional many-to-one association to Menu
	@ManyToOne( cascade = { CascadeType.ALL } )
	@JoinColumn( name = "keyToMenu" )
	private Menu menu;

	// bi-directional many-to-one association to Ingredient
	@OneToMany( mappedBy = "food" )
	private List< Ingredient > ingredients;

	public Food()
	{
	}

	public int getFoodid()
	{
		return this.foodid;
	}

	public void setFoodid( int foodid )
	{
		this.foodid = foodid;
	}

	public String getFoodName()
	{
		return this.foodName;
	}

	public void setFoodName( String foodName )
	{
		this.foodName = foodName;
	}

	public int getRefCount()
	{
		return this.refCount;
	}

	public void setRefCount( int refCount )
	{
		this.refCount = refCount;
	}

	public Menu getMenu()
	{
		return this.menu;
	}

	public void setMenu( Menu menu )
	{
		this.menu = menu;
	}

	public List< Ingredient > getIngredients()
	{
		return this.ingredients;
	}

	public void setIngredients( List< Ingredient > ingredients )
	{
		this.ingredients = ingredients;
	}

	public Ingredient addIngredient( Ingredient ingredient )
	{
		getIngredients().add( ingredient );
		ingredient.setFood( this );

		return ingredient;
	}

	public Ingredient removeIngredient( Ingredient ingredient )
	{
		getIngredients().remove( ingredient );
		ingredient.setFood( null );

		return ingredient;
	}

}
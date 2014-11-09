package resources;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the food database table.
 */
@Entity
@NamedQuery( name = "Food.findAll", query = "SELECT f FROM Food f" )
public class Food implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int foodid;

	@Column( name = "`food name`" )
	private String food_name;

	private Integer references;

	// bi-directional many-to-one association to Menu
	@ManyToOne( cascade = { CascadeType.ALL } )
	@JoinColumn( name = "food_menu_key" )
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

	public String getFood_name()
	{
		return this.food_name;
	}

	public void setFood_name( String food_name )
	{
		this.food_name = food_name;
	}

	public Integer getReferences()
	{
		return this.references;
	}

	public void setReferences( Integer references )
	{
		this.references = references;
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
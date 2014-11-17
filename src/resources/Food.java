package resources;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
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

	// bi-directional many-to-many association to Ingredient
	@ManyToMany( mappedBy = "foods" )
	private List< Ingredient > ingredients;

	// bi-directional many-to-many association to Menu
	@ManyToMany( mappedBy = "foods" )
	private List< Menu > menus;

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

	public List< Ingredient > getIngredients()
	{
		return this.ingredients;
	}

	public void setIngredients( List< Ingredient > ingredients )
	{
		this.ingredients = ingredients;
	}

	public List< Menu > getMenus()
	{
		return this.menus;
	}

	public void setMenus( List< Menu > menus )
	{
		this.menus = menus;
	}

	public List< Ingredient > getUnincludedIngredients( List< Ingredient > list )
	{
		List< Ingredient > result = new ArrayList< Ingredient >();
		List< Ingredient > myingreds = this.getIngredients();

		for( Ingredient ingred : list )
		{
			boolean addToRes = true;
			for( Ingredient myIng : myingreds )
			{
				if( myIng.getIngredientid() == ingred.getIngredientid() )
				{
					addToRes = false;
					break;
				}
			}
			if( addToRes )
				result.add( ingred );
		}

		return result;
	}

	public String getListOfIngredsStr()
	{
		String delim = "";
		String res = "";
		for( Ingredient ingred : this.getIngredients() )
		{
			res += delim + ingred.getIngredName();
			delim = ", ";
		}
		return res;
	}

}
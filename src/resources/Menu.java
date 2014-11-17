package resources;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@NamedQuery( name = "Menu.findAll", query = "SELECT m FROM Menu m" )
public class Menu implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int menuid;

	private String imgPath;

	private String menuName;

	// bi-directional many-to-many association to Food
	@ManyToMany
	@JoinTable( name = "menu_has_food", joinColumns = { @JoinColumn( name = "menu_menuid" ) }, inverseJoinColumns = { @JoinColumn( name = "food_foodid" ) } )
	private List< Food > foods;

	public Menu()
	{
	}

	public int getMenuid()
	{
		return this.menuid;
	}

	public void setMenuid( int menuid )
	{
		this.menuid = menuid;
	}

	public String getImgPath()
	{
		return this.imgPath;
	}

	public void setImgPath( String imgPath )
	{
		this.imgPath = imgPath;
	}

	public String getMenuName()
	{
		return this.menuName;
	}

	public void setMenuName( String menuName )
	{
		this.menuName = menuName;
	}

	public List< Food > getFoods()
	{
		return this.foods;
	}

	public void setFoods( List< Food > foods )
	{
		this.foods = foods;
	}

	public String getFoodsStr()
	{
		String delim = "";
		String result = "";

		for( Food food : this.getFoods() )
		{
			result += delim + food.getFoodName();
			delim = ", ";
		}

		return result;
	}

}
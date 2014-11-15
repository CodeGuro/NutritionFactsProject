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

	private String menuName;
	
	@Lob
	private byte[] img;

	// bi-directional many-to-one association to Food
	@OneToMany( mappedBy = "menu", cascade = { CascadeType.ALL } )
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

	public Food addFood( Food food )
	{
		getFoods().add( food );
		food.setMenu( this );

		return food;
	}

	public Food removeFood( Food food )
	{
		getFoods().remove( food );
		food.setMenu( null );

		return food;
	}

	public byte[] getImg()
	{
		return img;
	}

	public void setImg( byte[] img )
	{
		this.img = img;
	}

}
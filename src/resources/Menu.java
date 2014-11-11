package resources;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
//@NamedQuery( name = "Menu.findAll", query = "SELECT m FROM Menu m" )
@NamedQueries
({
	@NamedQuery( name = "Menu.findAll", query = "SELECT m FROM Menu m" ),
	@NamedQuery( name = "Menu.findById", query = "SELECT m FROM Menu m WHERE m.menuid = ?1" ),
	@NamedQuery( name = "Menu.deleteById", query = "DELETE FROM Menu m WHERE m.menuid = ?1" )
})
public class Menu implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int menuid;

	private String name;

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

	public String getName()
	{
		return this.name;
	}

	public void setName( String name )
	{
		this.name = name;
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

}
package resources;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class AdminResourceEAO
 */
@Stateless
@LocalBean
public class AdminResourceEAO
{
	@PersistenceContext
	private EntityManager em;
	
	public List< Ingredient > getIngredients()
	{
		return null;
	}
	
	public List< Menu > getMenus()
	{
		return null;
	}
	
	public List< Nutrition > getNutritions()
	{
		return null;
	}
	
	public List< Food > getFoods()
	{
		return null;
	}
	
	public void persistMenu( Menu menu )
	{
	}
	
	public void persistFood( Food food )
	{
	}
	
	public void persistIngredient( Ingredient ingredient )
	{
	}
	
	public void persistNutrition( Nutrition nutrition )
	{
	}
}

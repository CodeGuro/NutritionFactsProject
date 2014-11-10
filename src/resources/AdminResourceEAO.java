package resources;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class AdminResourceEAO
 */
@Stateless
@LocalBean
@SuppressWarnings( "unchecked" )
public class AdminResourceEAO
{
	@PersistenceContext
	private EntityManager em;
	
	public List< Ingredient > getIngredients()
	{
		TypedQuery< Ingredient > query =
			(TypedQuery< Ingredient >)em.createNativeQuery( "SELECT * FROM mydb.ingredient", Ingredient.class );
		return query.getResultList();
	}
	
	public List< Menu > getMenus()
	{
		TypedQuery< Menu > query =
			(TypedQuery< Menu >)em.createNativeQuery( "SELECT * FROM mydb.menu", Menu.class );
		return query.getResultList();
	}
	
	public List< Nutrition > getNutritions()
	{
		TypedQuery< Nutrition > query =
			(TypedQuery< Nutrition >)em.createNativeQuery( "SELECT * FROM mydb.nutrition", Nutrition.class );
		return query.getResultList();
	}
	
	public List< Food > getFoods()
	{
		TypedQuery< Food > query =
			(TypedQuery< Food >)em.createNativeQuery( "SELECT * FROM mydb.menu", Food.class );
		return query.getResultList();
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

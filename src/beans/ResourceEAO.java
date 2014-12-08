package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import resources.Food;
import resources.Ingredient;
import resources.Menu;
import resources.Nutrition;

/**
 * Session Bean implementation class AdminResourceEAO
 */
@Stateless
@LocalBean
public class ResourceEAO
{
	@PersistenceContext( type = PersistenceContextType.TRANSACTION )
	private EntityManager em;
	
	public void refresh()
	{
		em.getEntityManagerFactory().getCache().evictAll();
	}

	public List< Ingredient > getIngredients()
	{
		TypedQuery< Ingredient > query = (TypedQuery< Ingredient >)em
			.createNamedQuery( "Ingredient.findAll",
				Ingredient.class );
		return query.getResultList();
	}

	public Ingredient getIngredient( int id )
	{
		return em.find( Ingredient.class, id );
	}

	public void updateIngredient( Ingredient ingredient )
	{
		em.merge( ingredient );
	}
	
	public List< Menu > getMenus()
	{
		TypedQuery< Menu > query = (TypedQuery< Menu >)em.createNamedQuery(
			"Menu.findAll", Menu.class );
		return query.getResultList();
	}
	
	public Menu getMenu( int id )
	{
		return em.find( Menu.class, id );
	}

	public List< Nutrition > getNutritions()
	{
		TypedQuery< Nutrition > query = (TypedQuery< Nutrition >)em
			.createNamedQuery( "Nutrition.findAll", Nutrition.class );
		return query.getResultList();
	}

	public List< Food > getFoods()
	{
		TypedQuery< Food > query = (TypedQuery< Food >)em.createNamedQuery(
			"Food.findAll", Food.class );
		return query.getResultList();
	}

	public Food getFood( int id )
	{
		return em.find( Food.class, id );
	}
	
	public boolean persistMenu( Menu menu )
	{
		try
		{
			em.persist( menu );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public void persistFood( Food food )
	{
		em.persist( food );
	}
	
	public void updateFood( Food food )
	{
		em.merge( food );
		refresh();
	}

	public void persistIngredient( Ingredient ingredient )
	{
		em.persist( ingredient );
		refresh();
	}

	public void persistNutrition( Nutrition nutrition )
	{
		em.persist( nutrition );
	}

	@Transactional
	public boolean removeMenu( Integer menuId )
	{
		try
		{
			Menu menu = em.find( Menu.class, new Integer( menuId ) );
			em.remove( menu );
			refresh();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeFood( Integer foodId )
	{
		try
		{
			Food food = em.find( Food.class, foodId );
			em.remove( food );
			refresh();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeIngredient( Integer ingredientId )
	{
		try
		{
			Ingredient ingred = em.find( Ingredient.class, ingredientId );
			em.remove( ingred );
			refresh();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void updateNutrition( Nutrition nutrition )
	{
		em.merge( nutrition );
		refresh();		
	}

	public void updateMenu( Menu menu )
	{
		em.merge( menu );
		refresh();
	}
}

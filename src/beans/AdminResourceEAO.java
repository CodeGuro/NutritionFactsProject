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
@SuppressWarnings( "unchecked" )
public class AdminResourceEAO
{
	@PersistenceContext( type = PersistenceContextType.TRANSACTION )
	private EntityManager em;

	public List< Ingredient > getIngredients()
	{
		TypedQuery< Ingredient > query = (TypedQuery< Ingredient >)em
			.createNativeQuery( "Ingredient.findAll",
				Ingredient.class );
		return query.getResultList();
	}

	public List< Menu > getMenus()
	{
		TypedQuery< Menu > query = (TypedQuery< Menu >)em.createNamedQuery(
			"Menu.findAll", Menu.class );
		return query.getResultList();
	}

	public List< Nutrition > getNutritions()
	{
		TypedQuery< Nutrition > query = (TypedQuery< Nutrition >)em
			.createNativeQuery( "Nutrition.findAll", Nutrition.class );
		return query.getResultList();
	}

	public List< Food > getFoods()
	{
		TypedQuery< Food > query = (TypedQuery< Food >)em.createNativeQuery(
			"Food.findAll", Food.class );
		return query.getResultList();
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

	public void persistIngredient( Ingredient ingredient )
	{
		em.persist( ingredient );
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
			//TypedQuery< Menu > query = em.createNamedQuery( "Menu.deleteById", Menu.class );
			//query.setParameter( 1, menuId );
			//query.executeUpdate();
			Menu menu = em.find( Menu.class, new Integer( menuId ) );
			em.remove( menu );
			
		/*	TypedQuery< Menu > query = em.createNamedQuery( "Menu.findById", Menu.class );
			query.setParameter( 1, menuId );
			List< Menu > menus = query.getResultList();

			/*for( Menu menu : menus )
			{
				if( menu.getMenuid() == menuId )
				{
					em.getTransaction().begin();
					em.remove( menu );
					em.getTransaction().commit();
				}
			}*/
			
			//em.remove( menus.get( 0 ) );
			

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
			em.getTransaction().begin();
			em.remove( food );
			em.getTransaction().commit();
		}
		catch ( Exception e )
		{
			return false;
		}
		return true;
	}

	public boolean removeIngredient( Integer ingredientId )
	{
		try
		{
			Ingredient ingred = em.find( Ingredient.class, ingredientId );
			em.getTransaction().begin();
			em.remove( ingred );
			em.getTransaction().commit();
		}
		catch ( Exception e )
		{
			return false;
		}
		return true;
	}
}
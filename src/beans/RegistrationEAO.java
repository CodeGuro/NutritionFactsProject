package beans;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.faces.validator.ValidatorException;

import resources.Address;
import resources.Group;
import resources.User;
import resources.Util;

/**
 * Session Bean implementation class RegistrationEAO
 */
@Stateless( mappedName = "registrationEAO" )
@LocalBean
public class RegistrationEAO
{
	@PersistenceContext( )
	private EntityManager entityManager;

	public RegistrationEAO()
	{
	}

	public boolean persistUser( RegistrationBean regBean )
	{
		try
		{
			// Map a User instance to persist in the db
			User user = new User();
			user.setUsername( regBean.getUsername() );
			user.setPassword( Util.hash( regBean.getPassword() ) );
			user.setFirstname( regBean.getFirstname() );
			user.setLastname( regBean.getLastname() );
			user.setBirth( regBean.getDob().toString() );
			user.setEmail( regBean.getEmail() );

			// Map an Address instance to persist in the db
			Address address = new Address();
			address.setStreet( regBean.getStreet() );
			address.setSuburb( regBean.getSuburb() );
			address.setCity( regBean.getCity() );
			address.setZip( regBean.getZip() );
			address.setState_Province( regBean.getProvince() );
			address.setCountry( regBean.getCountry() );

			user.setAddress( address );
			List< User > users = new ArrayList< User >();
			users.add( user );
			address.setUsers( users );

			// Use JPQL to append the groups
			StringBuffer buffer = new StringBuffer();
			String[] targets = regBean.getSelectedGroups();
			buffer.append( "SELECT c FROM Group c WHERE c.groupname=?1" );
			int count = 2;
			for( int x = 1; x < targets.length; x++ )
			{
				buffer.append( " OR c.groupname=?" + count );
				count++;
			}

			// Add the parameter values to the query
			count = 1;
			Query query = this.entityManager.createQuery( buffer.toString() );
			for( String target : targets )
			{
				query.setParameter( count, target );
				count++;
			}

			// Fix for expression of type list needs express conversion to
			// conform
			// to conversion List
			// List groups = query.getResultList();

			List< Group > groups = Util.castList( Group.class,
				query.getResultList() );

			// Add user to each group
			for( Group group : groups )
			{
				group.getUsers().add( user );
			}

			// Add Groups to user
			user.setGroups( groups );

			// Commit data to the database
			entityManager.persist( user );

			return true;
		}
		catch ( RollbackException e )
		{
			Throwable en = e.getCause();
			en.printStackTrace();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		return false;
	}

	public boolean CheckRegisteredUser( String usname, String passwd )
		throws ValidatorException
	{
		EntityManager em = this.entityManager;

		@SuppressWarnings( "unchecked" )
		TypedQuery< User > query = (TypedQuery< User >)em.createNativeQuery(
			"SELECT * FROM mydb.user WHERE username = \"" + usname + "\"",
			User.class );

		List< User > users = query.getResultList();

		for( User user : users )
		{
			if( user.getPassword().equals( Util.hash( passwd ) ) )
				return true;
		}

		return false;
	}
}

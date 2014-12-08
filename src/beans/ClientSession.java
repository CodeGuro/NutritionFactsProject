package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import resources.Menu;

/**
 * Session Bean implementation class ClientSession
 */
@Stateful
@LocalBean
public class ClientSession
{
	@EJB
	private ResourceEAO resources;
	
	private Menu selectedMenu;

	public ResourceEAO getResources()
	{
		return resources;
	}

	public void setResources( ResourceEAO resources )
	{
		this.resources = resources;
	}
	
	public List< Menu > getMenus()
	{
		return resources.getMenus();
	}

	public Menu getSelectedMenu()
	{
		return selectedMenu;
	}

	public String setSelectedMenu( Menu menuItem )
	{
		System.out.println("success!");
		if( selectedMenu == null )
			return "failure";
		return "success";
	}
}

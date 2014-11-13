package beans;

import javax.ejb.EJB;

import resources.Menu;

public class AdminSession
{
	@EJB
	private AdminResourceEAO resources;
	private Menu workingMenu;

	public AdminResourceEAO getResources()
	{
		return resources;
	}

	public void setResources( AdminResourceEAO resources )
	{
		this.resources = resources;
	}

	public void setWorkingMenu( Menu menu )
	{
		this.workingMenu = menu;
	}
	
	public Menu getWorkingMenu()
	{
		return workingMenu;
	}
}

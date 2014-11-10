package resources;

import javax.ejb.EJB;

public class AdminSession
{
	@EJB
	private AdminResourceEAO resources;

	public AdminResourceEAO getResources()
	{
		return resources;
	}

	public void setResources( AdminResourceEAO resources )
	{
		this.resources = resources;
	}
}

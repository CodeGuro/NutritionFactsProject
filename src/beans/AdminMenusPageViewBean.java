package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import resources.Menu;

public class AdminMenusPageViewBean
{
	@EJB
	private AdminResourceEAO resources;
	private AdminSession adminSession;
	private int menuId;
	private String menuName;

	public AdminResourceEAO getResources()
	{
		return resources;
	}

	public void setResources( AdminResourceEAO resources )
	{
		this.resources = resources;
	}

	public List< Menu > getMenus()
	{
		return resources.getMenus();
	}
	
	public String deleteMenu()
	{
		if( resources.removeMenu( this.menuId ) )
			return "delSuccess";

		FacesContext.getCurrentInstance().addMessage(
			null,
			new FacesMessage( FacesMessage.SEVERITY_ERROR,
				"Error Deleting Entry", null ) );
		return "delFail";
	}

	public int getMenuId()
	{
		return menuId;
	}

	public void setMenuId( int menuId )
	{
		this.menuId = menuId;
	}

	public String getMenuName()
	{
		return menuName;
	}

	public void setMenuName( String menuName )
	{
		this.menuName = menuName;
	}

	public String createNewMenu()
	{
		Menu menu = new Menu();
		menu.setMenuName( menuName );
		if( !resources.persistMenu( menu ) )
		{
			FacesContext.getCurrentInstance().addMessage( null,
				new FacesMessage( FacesMessage.SEVERITY_ERROR, "An error occurred!", null ) );
			return "createFail";
		}
		FacesContext.getCurrentInstance().addMessage( null,
			new FacesMessage( FacesMessage.SEVERITY_INFO, "New Menu Item \""
				+ menu.getMenuName() + "\" created!", null ) );
		return "createSuccess";
	}
	
	public String editMenu()
	{
		adminSession.setWorkingMenu( resources.getMenu( this.getMenuId() ) );
		return "goToEditPage";
	}

	public AdminSession getAdminSession()
	{
		return adminSession;
	}

	public void setAdminSession( AdminSession adminSession )
	{
		this.adminSession = adminSession;
	}
	
}

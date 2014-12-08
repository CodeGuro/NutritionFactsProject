package beans;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import com.google.common.io.ByteStreams;

import resources.Food;
import resources.Menu;
import resources.Util;

public class AdminMenusPageViewBean
{
	@EJB
	private ResourceEAO resources;
	private AdminSession adminSession;
	private int menuId;
	private String menuName;
	private String imgPath;
	private Part file;
	private String editText;

	public List< Food > getFoodsOnCurrentMenu()
	{
		return adminSession.getWorkingMenu().getFoods();
	}
	
	public List< Food > getFoodsNotOnCurrentMenu()
	{
		List< Food > list = resources.getFoods();
		List< Food > result = new ArrayList< Food >();
		List< Food > myFoods = this.getAdminSession().getWorkingMenu().getFoods();
		
		for( Food food : list )
		{
			boolean addToRes = true;
			for( Food myFood : myFoods )
			{
				if( myFood.getFoodid() == food.getFoodid() )
				{
					addToRes = false;
					break;
				}
			}
			if( addToRes )
				result.add( food );
		}
		
		return result;
	}

	
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
			FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage( FacesMessage.SEVERITY_ERROR,
					"An error occurred!", null ) );
			return "createFail";
		}
		FacesContext.getCurrentInstance().addMessage(
			null,
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

	public String getImgPath()
	{
		return imgPath;
	}

	public void setImgPath( String imgPath )
	{
		this.imgPath = imgPath;
	}

	public Part getFile()
	{
		return file;
	}

	public void setFile( Part file )
	{
		this.file = file;
	}

	public String uploadFile()
	{
		Menu menu = resources.getMenu( menuId );
		if( file == null )
			return "failure";
		
		String basePath = Util.getBasePath();
		String filePath = Paths.get( basePath, Util.getFileName( file.getSubmittedFileName() ) ).toString();
		
		File saveFile = new File( filePath );
		if( !saveFile.exists() )
		{
			try
			{
				saveFile.createNewFile();
				FileOutputStream os = new FileOutputStream( saveFile );
				ByteStreams.copy( file.getInputStream(), os );
				os.close();
			}
			catch( Exception e )
			{
				e.printStackTrace();
				return "failure";
			}
		}
		menu.setImgPath( Paths.get( "img", Util.getFileName( file.getSubmittedFileName() ) ).toString() );
		resources.updateMenu( menu );
		return "success";
	}
	
	public String getFoodsStr( Menu menu )
	{
		String delim = "";
		String result = "";

		for( Food food : menu.getFoods() )
		{
			result += delim + food.getFoodName();
			delim = ", ";
		}

		return result;
	}
	
	public String setDescFor( Menu menu )
	{
		menu.setDescription( getEditText() );
		setEditText( "" );
		resources.updateMenu( menu );
		return "success";
	}

	public String getEditText()
	{
		return editText;
	}

	public void setEditText( String editText )
	{
		this.editText = editText;
	}
}

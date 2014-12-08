package beans;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.servlet.http.Part;

import com.google.common.io.ByteStreams;

import resources.Ingredient;
import resources.Nutrition;
import resources.Util;

@Stateful
@LocalBean
public class AdminIngredientsPageViewBean
{
	@EJB
	private ResourceEAO resources;
	private AdminSession sessionBean;
	private int ingredId;
	private String editText;
	private Part file;
	
	public void setEditText( String editText )
	{
		this.editText = editText;
	}
	
	public String getEditText()
	{
		return this.editText;
	}

	public String editIngredientName()
	{
		Ingredient ingred = resources.getIngredient( ingredId );
		ingred.setIngredName( editText );
		resources.updateIngredient( ingred );
		editText = null;
		return "success";
	}
	
	public String editIngredientAllergen()
	{
		Ingredient ingred = resources.getIngredient( ingredId );
		ingred.setAllergen( editText );
		resources.updateIngredient( ingred );
		editText = null;
		return "success";
	}
	
	public String editIngredientPrice()
	{
		try
		{
			Ingredient ingred = resources.getIngredient( ingredId );
			ingred.setPrice( Double.valueOf( editText ) );
			resources.updateIngredient( ingred );
			editText = null;
			return "success";
		}
		catch( Exception e )
		{
			AdminSession.raiseErrorMessage( "The price must be a real number!" );
			return "failure";
		}
	}
	
	public ResourceEAO getResources()
	{
		return resources;
	}

	public void setResources( ResourceEAO resources )
	{
		this.resources = resources;
	}

	public AdminSession getSessionBean()
	{
		return sessionBean;
	}

	public void setSessionBean( AdminSession sessionBean )
	{
		this.sessionBean = sessionBean;
	}
	
	public List< Ingredient > getIngredients()
	{
		return resources.getIngredients();
	}
	
	public int getIngredId()
	{
		return ingredId;
	}

	public void setIngredId( int ingredId )
	{
		this.ingredId = ingredId;
	}

	public String getIngredName()
	{
		return this.editText;
	}
	
	public String editNutritions()
	{
		sessionBean.setWorkingIngredient( resources.getIngredient( ingredId ) );
		return "goToEditPage";
	}
	
	public String createIngred()
	{
		Ingredient ingredient = new Ingredient();
		Nutrition nutrition = new Nutrition();
		nutrition.setIngredient( ingredient );
		ingredient.setNutrition( nutrition );
		ingredient.setIngredName( this.getIngredName() );
		ingredient.setRefCount( 0 );
		ingredient.setPrice( 0.0 );
		resources.persistIngredient( ingredient );
		this.editText = null;
		return "success";
	}
	
	public String deleteIngred()
	{
		resources.removeIngredient( ingredId );
		return "success";
	}

	public Part getFile()
	{
		return file;
	}

	public void setFile( Part file )
	{
		this.file = file;
	}
	
	public String submitImageFor( Ingredient ingred )
	{
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
		ingred.setImgPath( Paths.get( "img", Util.getFileName( file.getSubmittedFileName() ) ).toString() );
		resources.updateIngredient( ingred );
		return "success";
	}
	
	public String editDescFor( Ingredient ingred )
	{
		ingred.setDescription( editText );
		resources.updateIngredient( ingred );
		editText = "";
		return "success";
	}
	
}

package resources;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the groups database table.
 * 
 */
@Entity
@Table( name = "groups" )
@NamedQuery( name = "Group.findAll", query = "SELECT g FROM Group g" )
public class Group implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int groupid;

	private String groupdesc;

	private String groupname;

	// bi-directional many-to-many association to User
	@ManyToMany( mappedBy = "groups" )
	private List< User > users;

	public Group()
	{
	}

	public int getGroupid()
	{
		return this.groupid;
	}

	public void setGroupid( int groupid )
	{
		this.groupid = groupid;
	}

	public String getGroupdesc()
	{
		return this.groupdesc;
	}

	public void setGroupdesc( String groupdesc )
	{
		this.groupdesc = groupdesc;
	}

	public String getGroupname()
	{
		return this.groupname;
	}

	public void setGroupname( String groupname )
	{
		this.groupname = groupname;
	}

	public List< User > getUsers()
	{
		return this.users;
	}

	public void setUsers( List< User > users )
	{
		this.users = users;
	}

}
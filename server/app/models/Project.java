package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import util.*;

@Entity
public class Project extends Model implements JsonSerializable{
	
	@Id
	private Long id;
	
	/* Utilisation d'une string pour la date parce que sqlite ne supporte pas les dates */
	@Column(length = 255, nullable = false)
    private String timeStamp;
	
	@Column(length = 255, nullable = false)
    private String name;
	
	@Column(nullable = false)
	private int projectType;
	
	/* foreign key vers organisation */
	/* Commenter en attendant que organisation soit implementer */
	/*@Column(nullable = false)
	int organisation;*/

	/* CONSTANTS */
	public final static int PROJECT_TYPE_NEW=0;
	public final static int PROJECT_TYPE_ENHANCEMENT=1;
	
    public Project() {
        id=(long)1;
		timeStamp="10 01 01";
		name="test project";
		projectType = 0;
    }
	
	@Override
	public String toJson()
	{
		JsonBuilder json = new JsonBuilder();
		json.add("id", id);
		json.add("name", name);
		json.add("type", getProjectTypeName());
		json.add("timeStamp", timeStamp);
		json.add("systems", getSystems());
		
		return json.toString();
	}

	public String getProjectTypeName()
	{
		return projectType == PROJECT_TYPE_NEW ? "new" :
		       projectType == PROJECT_TYPE_ENHANCEMENT ? "enhancement" :
			   "invalid";
	}
	
	public long getId(){return id;}
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	public int getProjectType(){ return projectType; }
	public void setProjectType(int projectType)
	{ 
		if(projectType != PROJECT_TYPE_NEW && projectType != PROJECT_TYPE_ENHANCEMENT)
		{
			throw new RuntimeException("Invalid value for project type");
		}
		
		this.projectType = projectType;
	}
	
	public List<System> getSystems()
	{
		return System.find.where().eq("project", id).findList();
	}
	
	public static Finder<Long,Project> find = new Finder<Long,Project>(Long.class, Project.class); 
}

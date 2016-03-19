package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import util.*;

@Entity
public class System extends Model implements JsonSerializable{
	
	@Id
	private Long id;
	
	@Column(length = 255, nullable = false)
    private String name;
	
	/* foreign key vers projet */
	@Column(nullable = false)
	private long project;

	/* booleans */
	@Column(nullable = false)
	private int fAdd;
	
	@Column(nullable = false)
	private int fModify;
	
	@Column(nullable = false)
	private int fDelete;
	
	@Column(nullable = false)
	private int fUnknown;
	
    public System(long parentId) {
        id=null;
		name="";
		project=parentId;
		fAdd=0;
		fModify=0;
		fDelete=0;
		fUnknown=0;
    }
	
	@Override
	public void save()
	{
		super.save();
		
		if( getLayers().size() == 0 )
		{
			models.Layer l = new Layer(id);
			l.save();
		}
	}
	
	@Override
	public void delete()
	{
		List<Layer> layers = getLayers();
		for(Layer layer : layers) layer.delete();
		
		super.delete();
	}
	
	@Override
	public String toJson()
	{
		JsonBuilder json = new JsonBuilder();
		json.add("id", id);
		json.add("name", name);
		json.add("project_id", project);
		json.add("add", fAdd);
		json.add("modify", fModify);
		json.add("delete", fDelete);
		json.add("unknown", fUnknown);
		json.add("layers", getLayers());
		
		return json.toString();
	}
	
	public long getId(){return id;}
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	
	public boolean getAdd(){ return fAdd == 1; }
	public boolean getDelete(){ return fDelete == 1; }
	public boolean getModify(){ return fModify == 1; }
	public boolean getUnknown(){ return fUnknown == 1; }
	
	public void setAdd(boolean val){ fAdd = val ? 1 : 0; }
	public void setDelete(boolean val){ fDelete = val ? 1 : 0; }
	public void setModify(boolean val){ fModify = val ? 1 : 0; }
	public void setUnknown(boolean val){ fUnknown = val ? 1 : 0; }
	
	public List<Layer> getLayers()
	{
		return Layer.find.where().eq("system", id).findList();
	}
	
	public static System createForParentId(long parentId)
	{
		models.Project p = models.Project.find.byId(parentId);
		
		if(p == null)
			throw new RuntimeException("No project corresponds to id " + Long.toString(parentId));
			
		models.System sys = new System(parentId);
		sys.save();
		
		return sys;
	}
	
	public static Finder<Long,System> find = new Finder<Long,System>(Long.class, System.class); 
}

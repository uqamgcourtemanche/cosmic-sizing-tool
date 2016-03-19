package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import util.*;

@Entity
public class Process extends Model implements JsonSerializable{
	
	@Id
	private Long id;
	
	@Column(length = 255, nullable = false)
    private String name;
	
	/* foreign key vers layer */
	@Column(nullable = false)
	private long layer;
	
	@Column(nullable = false)
	private int fAdd;
	
	@Column(nullable = false)
	private int fModify;
	
	@Column(nullable = false)
	private int fDelete;
	
	@Column(nullable = false)
	private int fUnknown;;

	
    public Process(long parentId) {
        id=null;
		name="";
		layer=parentId;
		fAdd=0;
		fModify=0;
		fDelete=0;
		fUnknown=0;
    }
	
	@Override
	public String toJson()
	{
		JsonBuilder json = new JsonBuilder();
		json.add("id", id);
		json.add("name", name);
		json.add("layer_id", layer);
		json.add("add", fAdd);
		json.add("modify", fModify);
		json.add("delete", fDelete);
		json.add("unknown", fUnknown);
		json.add("data_groups", getDataGroup());
		
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
	
	public List<DataGroup> getDataGroup()
	{
		return DataGroup.find.where().eq("process", id).findList();
	}
	
	public static Finder<Long,Process> find = new Finder<Long,Process>(Long.class, Process.class); 
}

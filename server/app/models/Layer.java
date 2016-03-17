package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import util.*;

@Entity
public class Layer extends Model implements JsonSerializable{
	
	@Id
	private Long id;
	
	@Column(length = 255, nullable = false)
    private String name;
	
	/* foreign key vers system */
	@Column(nullable = false)
	private long system;

	
    public Layer() {
        id=(long)1;
		name="";
		system=1;
    }
	
	@Override
	public String toJson()
	{
		Json json = new Json();
		json.add("id", id);
		json.add("name", name);
		json.add("system_id", system);
		
		return json.toString();
	}
	
	public long getId(){return id;}
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	
	public static Finder<Long,Layer> find = new Finder<Long,Layer>(Long.class, Layer.class); 
}
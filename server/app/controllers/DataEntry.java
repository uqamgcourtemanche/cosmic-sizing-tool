package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;
import play.data.Form;
import static play.data.Form.*;
import util.*;

public class DataEntry extends Controller {

	private final static int MODE_HTML=0;
	private final static int MODE_JSON=1;

	public Result test_laurier()
	{
		return ok(mesure.render());
	}
	
	public Result test_guillaume()
	{
		/*debug*/
		try
		{
			models.Project p = new models.Project();
			p.save();
			createSystem("1");
		}
		catch(Exception e)
		{
			
		}
		
		/*debug*/
	
		return ok(views.html.project.test_guillaume.render());
	}
	
	public Result createSystem(String project_id)
	{
		try{
			Long lId = Long.parseLong(project_id);
			
			return ok(models.System.createForParentId(lId).toJson());
		} catch(Exception e)
		{
			return internalServerError(e.getMessage());
		}
	}
	
	public Result updateSystem(String id)
	{
		/* The values */
		long lId;
		String name = "";
		String add = "";
		String modify = "";
		String delete = "";
		String unknown = "";
			
		/* if Json present ... */
		
		
		/* else we take the form elements */
		
		{
			DynamicForm form = form().bindFromRequest();
			name = form.get("name");
			add = form.get("add");
			modify = form.get("modify");
			delete = form.get("delete");
			unknown = form.get("unknown");
		}
		
		boolean fAdd = add != null && add.equals("1");
		boolean fModify = modify != null && modify.equals("1");
		boolean fDelete = delete != null && delete.equals("1");
		boolean fUnknown = unknown != null && unknown.equals("1");
		
		try
		{
			lId = Long.parseLong(id);
		}
		catch(Exception e)
		{
			return badRequest("Id is of an invalid value.");
		}
		
		models.System sys = models.System.find.byId(lId);
		if( sys == null )
			return badRequest("Id does not correspond to any system");
			
		sys.setName(name);
		sys.setAdd(fAdd);
		sys.setModify(fModify);
		sys.setDelete(fDelete);
		sys.setUnknown(fUnknown);
		sys.save();
		
		return ok(sys.toJson());
	}
	
	public Result getSystem(String id)
	{
		/* The values */
		long lId;
		try
		{
			lId = Long.parseLong(id);
		}
		catch(Exception e)
		{
			return badRequest("Id is of an invalid value.");
		}
		
		models.System sys = models.System.find.byId(lId);
		return ok(sys.toJson());
	}
	
	public Result getAllSystems()
	{
		List<models.System> sys = models.System.find.all();
		
		JsonBuilder json = new JsonBuilder();
		json.add("systems", sys);
		
		return ok(json.toString());
	}
	
	public Result createProcess(String parentId)
	{
		Long lId;
		
		try
		{
			lId = Long.parseLong(parentId);
		}
		catch(Exception e)
		{
			return badRequest("Id is of an invalid value.");
		}
		
		models.System sys = models.System.find.byId(lId);
		if( sys == null )
			return badRequest("Id is of an invalid value.");
			
		if( sys.getLayers().size() == 0 )
			return internalServerError("System with id " + parentId + " is in an invalid state");
		
		models.Process proc = new models.Process(sys.getLayers().get(0).getId());
		proc.save();
		
		return ok(proc.toJson());
	}
	
	public Result updateProcess(String id)
	{
		/* The values */
		long lId;
		String name = "";
		String add = "";
		String modify = "";
		String delete = "";
		String unknown = "";
			
		/* if Json present ... */
		
		
		/* else we take the form elements */
		
		{
			DynamicForm form = form().bindFromRequest();
			name = form.get("name");
			add = form.get("add");
			modify = form.get("modify");
			delete = form.get("delete");
			unknown = form.get("unknown");
		}
		
		boolean fAdd = add != null && add.equals("1");
		boolean fModify = modify != null &&  modify.equals("1");
		boolean fDelete = delete != null &&  delete.equals("1");
		boolean fUnknown = unknown != null &&  unknown.equals("1");
		
		try
		{
			lId = Long.parseLong(id);
		}
		catch(Exception e)
		{
			return badRequest("Id is of an invalid value.");
		}
		
		models.Process proc = models.Process.find.byId(lId);
		if( proc == null )
			return badRequest("Id does not correspond to any Process");
			
		proc.setName(name);
		proc.setAdd(fAdd);
		proc.setModify(fModify);
		proc.setDelete(fDelete);
		proc.setUnknown(fUnknown);
		proc.save();
		
		return ok(proc.toJson());
	}
	
	public Result getProcess(String id)
	{
		/* The values */
		long lId;
		try
		{
			lId = Long.parseLong(id);
		}
		catch(Exception e)
		{
			return badRequest("Id is of an invalid value.");
		}
		
		models.Process proc = models.Process.find.byId(lId);
		return ok(proc.toJson());
	}
	
	public Result getAllProcess()
	{
		List<models.Process> sys = models.Process.find.all();
		
		JsonBuilder json = new JsonBuilder();
		json.add("process", sys);
		
		return ok(json.toString());
	}
	
    public Result edit(String id, int mode) {
		try{
			Long lId = Long.parseLong(id);
			models.Project p = models.Project.find.byId(lId);
			
			if(p == null)
				throw new RuntimeException("No project corresponds to id " + id);
			
			if( mode == MODE_HTML )
			{
				return ok(views.html.project.edit.render(p));
			}
			else if( mode == MODE_JSON )
			{
				return ok(p.toJson());
			}
			
			return badRequest("Mode invalide");
		} catch(Exception e)
		{
			return internalServerError(e.getMessage());
		}
    }
    
}



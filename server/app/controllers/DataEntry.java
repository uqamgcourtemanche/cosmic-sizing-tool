package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;
import play.data.Form;
import static play.data.Form.*;

public class DataEntry extends Controller {

	private final static int MODE_HTML=0;
	private final static int MODE_JSON=1;

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
		
	public Result system(String id) {
		try{
			Long lId = Long.parseLong(id);
			models.System s = models.System.find.byId(lId);
			
			if(s == null)
				throw new RuntimeException("No system corresponds to id " + id);
			
			return ok(views.html.project.test_form_system.render(s));
			
		} catch(Exception e)
		{
			return internalServerError(e.getMessage());
		}
	}
	
	public Result update(String id) {
		Long lId = Long.parseLong(id);
		models.System s = models.System.find.byId(lId);
		
		DynamicForm form = form().bindFromRequest();
		String nameValue = form.get("name");
		
		java.lang.System.out.println(nameValue);
		
		s.setName(nameValue);
		s.save();
		
		return ok(views.html.project.test_form_system.render(s));
	}
    
}


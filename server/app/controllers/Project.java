package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;
import play.data.Form;
import static play.data.Form.*;

public class Project extends Controller {

	private final static int MODE_HTML=0;
	private final static int MODE_JSON=1;

    public Result index(String id, int mode) {
		try{
			Long lId = Long.parseLong(id);
			models.Project p = models.Project.find.byId(lId);
			
			if(p == null)
				throw new RuntimeException("No project correspond to id " + id);
			
			System.out.println("mode = " + Integer.toString(mode));
			
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



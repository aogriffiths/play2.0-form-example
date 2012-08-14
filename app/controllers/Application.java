package controllers;

import java.util.List;

import models.Thing;
import play.*;
import play.api.templates.Html;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

  public static Result index() {
    return ok(views.html.index.render());
  }

  static Form<Thing> thingForm = form(Thing.class);

  /* There are three write operations. These correspond to the POST requests.
   * thingCreate()   - Create a new thing
   * thingUpdate(id) - Update an existing thing
   * thingDelete(id) - Delete an  existing thing
   */  
  public static Result thingCreate() {
    Form<Thing> filledForm = thingForm.bindFromRequest();
    if (filledForm.hasErrors()) {
      return badRequest("Could not create a new Thing");
    } else {
      Thing thing = filledForm.get();
      thing.save();
      return redirect(routes.Application.thingList());
    }
  }
  
  /* There are four read operations. These correspond to the GET requests.
   * thingList()   - GET a list of all things for viewing
   * thingView(id) - GET a single thing for viewing 
   * thingEdit(id) - GET a single thing for editing - POSTS to thingUpdate(id)
   * thingNew(id)  - GET a new thing for creating   - POSTS to thingCreate()
   * the list, single and edit views can all POST to thingDelete(id)
   */
  public static Result thingList() {
    List<Thing> things = Thing.find.findList();
    return ok(views.html.thingList.render(things));
  }
  
  public static Result thingNew() {
    Thing thing = new Thing();
    Form<Thing> createForm = thingForm.fill(thing);
    return ok(views.html.thingNew.render(thing, createForm));
  }

  public static Result thingView(Long id) {
    Thing thing = Thing.find.where().idEq(id).findUnique();
    return ok(views.html.thingView.render(thing, thingForm.fill(thing)));
  }

  public static Result thingEdit(Long id) {
    Thing thing = Thing.find.where().idEq(id).findUnique();
    Form<Thing> editForm = thingForm.fill(thing);
    return ok(views.html.thingEdit.render(thing, editForm));
  }
  
  
  public static Result thingUpdate(Long id) {
    Form<Thing> filledForm = thingForm.bindFromRequest();
    if (filledForm.hasErrors()) {
      Thing thing = Thing.find.where().idEq(id).findUnique();
      return badRequest(views.html.thingEdit.render(thing, filledForm));
    } else {
      Thing thing = filledForm.get();
      thing.id = id;
      thing.update();
      return redirect(routes.Application.thingView(id));
    }
  }

  public static Result thingDelete(Long id) {
    Thing thing = Thing.find.where().idEq(id).findUnique();
    thing.delete();
    return redirect(routes.Application.thingList());
  }


}
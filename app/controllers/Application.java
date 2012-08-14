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
  /*
   * This is a static immutable instance of of a Form<Thing>
   * It can be used directly to create a blank form  as in thingNew()
   * or used to render a form, filled with the data of an existing 
   * Thing as in thingEdit(), or used to to update an existing Thing
   * based on a form POST, as in thingUpdate().
   */
  static Form<Thing> thingForm = form(Thing.class);

  /* There are three write operations. These correspond to the POST requests.
   * thingCreate()   - Create a new thing
   * thingUpdate(id) - Update an existing thing
   * thingDelete(id) - Delete an  existing thing
   */  

  /* There are four read operations. These correspond to the GET requests.
   * thingList()   - GET a list of all things for viewing
   * thingView(id) - GET a single thing for viewing 
   * thingEdit(id) - GET a single thing for editing - POSTS to thingUpdate(id)
   * thingNew(id)  - GET a new thing for creating   - POSTS to thingCreate()
   * the list, single and edit views can all POST to thingDelete(id)
   */

  /* thingCreate
   * 
   * Creates a Thing record with the values from a POST request
   * If there is an error with validating the POST request the
   * thingNew view is sent back with the appropriate error messages 
   * (which play will have set as part of the bindFromRequest() call.
   * (You can test this by attempting to give the Thing a name of zero length.)
   */
  public static Result thingCreate() {
    Form<Thing> filledForm = thingForm.bindFromRequest();
    if (filledForm.hasErrors()) {
      return badRequest(views.html.thingNew.render(filledForm));
    } else {
      Thing thing = filledForm.get();
      thing.save();
      return redirect(routes.Application.thingList());
    }
  }

  /* thingUpdate
   * 
   * Updates a Thing record with the values from a POST request
   * If there is an error with validating the POST request the
   * thingEdit view is sent back with the appropriate error messages 
   * (which play will have set as part of the bindFromRequest() call.
   * (You can test this by attempting to give the Thing a name of zero length.)
   */
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

  /* thingDelete
   * 
   * Deletes a Thing record 
   */
  public static Result thingDelete(Long id) {
    Thing thing = Thing.find.where().idEq(id).findUnique();
    thing.delete();
    return redirect(routes.Application.thingList());
  }
  
  /* thingList
   * 
   * No form involved just standard play 2.0 MVC. thingList 
   * is the controller method, the model is a List<Thing> 
   * and the view is thingList.scala.html.
   * 
   * There are links to all the CRUD operations from 
   * thingList.scala.html.
   */
  public static Result thingList() {
    List<Thing> things = Thing.find.findList();
    return ok(views.html.thingList.render(things));
  }
  
  /* thingNew
   * 
   * Sends an empty form to thingNew.scala.html
   * to be rendered.
   */
  public static Result thingNew() {
    return ok(views.html.thingNew.render(thingForm));
  }

  /* thingView
   * 
   * Finds a single Thing in the database and sends it to
   * thingView.scala.html to be rendered.
   */
  public static Result thingView(Long id) {
    Thing thing = Thing.find.where().idEq(id).findUnique();
    return ok(views.html.thingView.render(thing));
  }

  /* thingView
   * 
   * Finds a single Thing in the database and creates a Form<Thing>
   * filled with the data of that Thing. Sends the Thing and it's edit form 
   * to thingEdit.scala.html to be rendered.
   */
  public static Result thingEdit(Long id) {
    Thing thing = Thing.find.where().idEq(id).findUnique();
    Form<Thing> editForm = thingForm.fill(thing);
    return ok(views.html.thingEdit.render(thing, editForm));
  }
}
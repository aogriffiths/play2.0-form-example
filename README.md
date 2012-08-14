Forms Using Play 2.0
====================

Prerequisites
------------

You will need to know a little java and how play 2.0 works. 
There are comments in the code that should help. You will need play 2.0 and git 
installed to run this example from your own machine.

To run it, simply do a "git clone", cd to the project directory, then "play run".

The Code
--------

Start by looking at [Application.java](play2.0-form-example/blob/master/app/controllers/Application.java).

There are three write operations. These correspond to the POST requests.
   * thingCreate()   - Create a new thing
   * thingUpdate(id) - Update an existing thing
   * thingDelete(id) - Delete an existing thing
   
There are four read operations. These correspond to the GET requests.
   * thingList()   - GET a list of all things for viewing
   * thingView(id) - GET a single thing for viewing 
   * thingEdit(id) - GET a single thing for editing - POSTS to thingUpdate(id)
   * thingNew(id)  - GET a new thing for creating   - POSTS to thingCreate()

The list, single and edit views can all POST to thingDelete(id)


All of these java methods have http methods routed to them as per [routes](play2.0-form-example/blob/master/conf/routes) (four are GET methods and one is a POST method).

The views are all in the standard [app/views](play2.0-form-example/blob/master/app/views) package and correspond to the List, Edit, New and View methods.


List
----

The most simple case. [Application.thingList()](play2.0-form-example/blob/master/app/controllers/Application.java) 
finds all the things in the dabase and sends them to the 
[thingList.scala.html](play2.0-form-example/blob/master/app/views/thingList.scala.html) template
to be renderd.

Create
------

[Application.thingNew()](play2.0-form-example/blob/master/app/controllers/Application.java) creates a new, 
blank, Thing and an empty Form<Thing> for editing it. Technically the blank Thing object is 
not needed, but would be useful if it contained useful default values, worth binding to the
form. [thingNew.scala.html](play2.0-form-example/blob/master/app/views/thingNew.scala.html) renders the form.

View
----

Another simple one. [Application.thingView(id)](play2.0-form-example/blob/master/app/controllers/Application.java)
simply finds the Thing by id and sends it to 
[thingView.scala.html](play2.0-form-example/blob/master/app/views/thingView.scala.html) to be rendered.

Edit
----

Similarities to both View and Create.
[Application.thingEdit(id)](play2.0-form-example/blob/master/app/controllers/Application.java) finds the Thing by id
and creates an edit form from it. The Thing and the Form are sent to 
[thingEdit.scala.html](play2.0-form-example/blob/master/app/views/thingView.scala.html) 
to be rendered.

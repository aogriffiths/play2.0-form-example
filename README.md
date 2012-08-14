Forms Using Play 2.0
====================

Prerequisites
------------

You will need to know a little java and how play 2.0 works. You will need play 2.0 and git 
installed to run this example from your own machine.

To run it, simply do a "git clone", cd to the project directory, then "play run".

The Code
--------

The first thing to say is this app is not made to look good! The UI is  simple and 
dull. The real purpose is to demostrate the code, which is well commented. Below is a quick
overview of how it hangs together and you can click the links to read the comments inline
with the code to find out more.

The function of the app is to provided CRUD opperations for a model object called "Thing"

Start by looking at [Application.java](play2.0-form-example/blob/master/app/controllers/Application.java).

There are three write operations. These correspond to the POST requests.
   * thingCreate()   - Create a new Thing
   * thingUpdate(id) - Update an existing Thing
   * thingDelete(id) - Delete an existing Thing
   
There are four read operations. These correspond to the GET requests.
   * thingList()   - GET a list of all Things for viewing
   * thingView(id) - GET a single Thing for viewing 
   * thingEdit(id) - GET a single Thing for editing - POSTS to thingUpdate(id)
   * thingNew(id)  - GET a new Thing for creating   - POSTS to thingCreate()

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

[Application.thingNew()](play2.0-form-example/blob/master/app/controllers/Application.java) creates an empty 
Form<Thing> for creating a new Thing. [thingNew.scala.html](play2.0-form-example/blob/master/app/views/thingNew.scala.html) renders the form.

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

List of Examples
--------------
[play2.0-form-example](../play2.0-form-example)
[play2.0-ajax-examples](../play2.0-ajax-examples)

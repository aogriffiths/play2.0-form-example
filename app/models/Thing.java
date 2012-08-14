package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Thing extends Model {

  @Id
  public Long id;

  @Required
  public String name;

  public static Finder<Long, Thing> find = new Finder<Long, Thing>(Long.class,
      Thing.class);

}

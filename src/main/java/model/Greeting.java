package model;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Greeting {
	@Parent
	Key<GuestBook> theBook;
	@Id
	public Long id;

	public String author_email;
	public String author_id;
	public String content;
	@Index
	public Date date;
	
	
	public Greeting() {
	    date = new Date();
	  }

	  /**
	   * A connivence constructor
	   **/
	  public Greeting(String book, String content) {
	    this();
	    if( book != null ) {
	      theBook = Key.create(GuestBook.class, book);  // Creating the Ancestor key
	    } else {
	      theBook = Key.create(GuestBook.class, "default");
	    }
	    this.content = content;
	  }

	  /**
	   * Takes all important fields
	   **/
	  public Greeting(String book, String content, String id, String email) {
	    this(book, content);
	    author_email = email;
	    author_id = id;
	  }

}

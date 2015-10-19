package model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class GuestBookStatistics {
	@Parent
	Key<GuestBook> theBook;
	@Id
	public Long id;
	
	public long messageCount;
	
	public GuestBookStatistics () {
	}
	
	public  GuestBookStatistics (Key<GuestBook> book, long count) {
		theBook = book;
		messageCount = count;
	}

}

package com.howard.test;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Greeting;
import model.GuestBook;
import model.GuestBookStatistics;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

public class SignGuestbookServlet extends HttpServlet {

	// Process the http POST of the form
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Greeting greeting;

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser(); // Find out who the user is.

		String guestbookName = req.getParameter("guestbookName");
		String content = req.getParameter("content");
		if (user != null) {
			greeting = new Greeting(guestbookName, content, user.getUserId(),
					user.getEmail());
		} else {
			greeting = new Greeting(guestbookName, content);
		}

		// Use Objectify to save the greeting and now() is used to make the call
		// synchronously as we
		// will immediately get a new page using redirect and we want the data
		// to be present.
		final Greeting gr = greeting;
		final Key<GuestBook> theBook = Key.create(GuestBook.class, guestbookName);
		
		ObjectifyService.ofy().transact(new VoidWork () {

			@Override
			public void vrun() {
				GuestBookStatistics stats = ObjectifyService.ofy()
				.load()
				.type(GuestBookStatistics.class) 
				.ancestor(theBook) 
				.first().now();
				
				
				ObjectifyService.ofy().save().entity(gr).now();
								
				
				if (stats == null) {
					stats = new GuestBookStatistics(theBook, 1);
				} else {				
					stats.messageCount = stats.messageCount + 1;
				}
				
				System.out.println("Message count is: " + stats.messageCount);
				
				ObjectifyService.ofy().save().entity(stats).now();

				
			}
		});
		
		resp.sendRedirect("/guestbook.jsp?guestbookName=" + guestbookName);
	}
}

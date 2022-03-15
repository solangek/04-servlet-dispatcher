# servlet dispatcher
Two servlets are used to handle the requests.
The landing page is a html form to enter a first and last name.

The DispatchServlet is used to process the form and build a User object
that is passed to the second servlet. A message string is also passed to the
second servlet.

The second servlet (InternalServlet) is used to process the User object
and display a welcome message.


# Assignment

*** Open APIs (No Authentication Required) ***
The following APIs are publicly accessible and do not require authentication:

Login
  Used for user authentication.
Register User
  Allows a new user to register in the system.
Get All Books
  Fetches the list of all available books.
  
  
*** Admin-Only APIs (hasRole('ADMIN')) ***
The following APIs are restricted to admin users only and require admin role authorization:
  
Get All Users
  Retrieves details of all registered users.
Add New Book
  Allows admin to add a new book to the system.
Update User Membership
  Used to update or renew a user’s membership.
Check Valid Membership
  Fetches users with valid/active memberships.
Books Read by Category Percentage
  Provides category-wise book reading statistics.
  
*** Authenticated APIs (Login Required) ***
The following APIs require the user to be logged in but do not require admin privileges:
Get Book By Name
  Fetches book details using book name.
Get Book By Author
  Retrieves books written by a specific author.
Get Book By Category
  Returns books belonging to a particular category.
Get Book
  Allows a user to issue/borrow a book.
Return Book
  Used by users to return a borrowed book.
Current Books
  Displays books currently issued to the logged-in user.
Previous Read Books
  Shows the user’s book reading history.

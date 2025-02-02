## Basic authentication need to be setup
- [x] When running the application, the first window should ask the user
- [x] to create an account (will require a email/password)
- [x] a way to login (using only login/password)
- [ ] To successfully create an account the email should have been "whitelisted" first
- [x] Password should not be stored directly on the database (think about security)
- [x] Login should be an email
- [x] If an error is thrown during the login or create user action you need to give the user what is the issue
## User management solution
- [x] Create, Read, Delete user
- [x] Update user
- [x] User is at least {id, email, pseudo, password, role}
- [x] Normal users can read information about another user (not the password)
- [x] You can create a new user even without being logged (just need your email to be whitelisted)
- [x] You can only update yourself (other users cannot update you EXCEPT if admin)
- [x] You can only delete yourself (other users cannot delete you EXCEPT if admin)
## Admin management
- [x] The first user is by default an admin (or you can create a default admin/admin user)
- [ ] Admin can "whitelist" emails to allow people to register
- [x] Admin can update an employee account
- [x] Admin can delete an employee account
- [x] Admin can create a new "store"
- [ ] Admin can create and delete a new item in the inventory
## Inventory management
- [x] An inventory is linked linked to a store (one inventory per store)
- [x] An inventory contains items
- [x] items have at least the following properties: id, name, price
- [x] each item should have a limited number in storage (cannot be lower than 0)
- [x] Inventory need to be browsable - you need to be able to display all the items
- [ ] An employee can increase or decrease the number of one item in stock (selling or receiving item)
## Store management
- [x] Store can only be created / deleted by Admin
- [x] Should only have two properties: id and name
- [x] A store only have one inventory
- [ ] Admin can add an employee to the store
- [ ] Employee only have access to store they have been added
- [ ] You need to display (for admin and employee with access) the list of all people having access to the store
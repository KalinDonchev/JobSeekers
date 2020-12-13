# JobSeekers - platform for freelancers to offer their skills to the world and users finding the perfect employee for their task

JobSeekers is an freelance application built on Angular and Spring as a project for the SoftUni Angular Course. It's purpose is to find your required person to do your task or to offer your help(work) to someone. Css code is written by me. Angular Material is used only for some components. Validation is included.

The application has three levels of accessibility: guest user, admin and registered user

This is the FrontEnd part of the project.

## Routes
| Route  | Description | Access Level |
| ------------- | ------------- | ------------- |
| /  | Home Page  | The home page changes dynamically dependig on logged in status | All users and Guest Users |
| /auth/login  | Login Page  | Guests only |
| /auth/register  | Register Page  | Guests only |
| /offer/create/  | Page for creating offer  | Registered only |
| /offer/details/:id  | Page for looking offer's details  | Registered only |
| /offer/my-offers/:username  | Current user's offers  | Registered only |
| /offer/favourite-offers/:username  | Current user's favourite offers  | Registered only |
| /user/profile/:username  | Page for user's details page  | Registered only |
    
The already logged in user is authenticated to create offers , delete his own offers , add other user's offers to favourites. He can see other users details on the offer details page and contact the user if interested.


## Usage

**Home page**

route: '/'

A Welcome page or Home page depending on logged in status.

**Offers create page**

route: '/offer/create'

Page for creating an offer.

**Single offer details page**

route: '/offer/details/:id'

A page displaying the selected offer's details.

**Favourite offers page**

route: '/offer/favourite-offers/:username'

A page displaying all offers that the user has in his favourite list.

**All offers page**

route: '/offer/my-offers/:username'

A page displaying all created offers of current user.

**User profile page**

route: '/user/profile/:username'

A page where you can see your details if you are logged in.


**Login page**

route: '/auth/login'

Login Page.

**Register page**

route: '/auth/register'

Register Page.

## Run the application

Install Angular if needed.

Download the code from github, extract the zip file with WinRar or other PAID software. For both folders you have to open terminal and write: 

    npm install

Open the application in an editor (VS Code for example) 
    
To start the Back-End part, you have to download it from the other folder (JobSeekers_BackEnd) and configure it :)

    It is running on http://localhost:8080
   
The last thing you have to do is to write this command to start the Front-End part and the site will appear on the browser:

    ng serve --open or ng serve. The website will wait you on http://localhost:4200
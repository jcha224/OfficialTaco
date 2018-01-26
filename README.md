# OfficialTaco
An android application for restaurant searches.

School project

Features:
    Registeration: Allows the user to create an account with a username and password. This 
                   account will be store so that the user can sign in with the user's login 
                   information.
    Login: Allows the user to use sign in with previously created account. 
    Search by Location: Allows the user to search for a restaurant based on a desired location.
    Search by Type: Allows the user to search for a restaurant based on a desired type of food.
    
Use Cases:

    Use Case 1: Register 
        Actor: App User
        Preconditions: Desired account information
        Successful Condition: Account information is stored in database and home page is opened.
        Main Scenario:    
            1)	User will start the application and the start screen will appear.
            2)	User will click the register button taking them to the registration screen.
            3)	User will enter a username and password. The password will be entered twice 
                for confirmation.
            4)  User will click the register button and then be promptly passed to the start 
                screen and promoted to a registered user.
        Alternative Scenario:
            1)  User presses cancel button: entered information is deleted and user is brought 
                to the home screen.
            1)  User presses back button: entered information is deleted and user is brought 
                to the home screen.

    Use Case 2: Log in
        Actor: Registered User
        Preconditions: Account information
        Successful Condition: Account information is retrieved from database and home page 
                              is opened. 
        Main Scenario:    
            1)	User will start the application and the start screen will appear.
            2)	User will click the login button taking them to the login screen.
            3)	User will enter the account's username and password.
            4)  User will click the login button and then be promptly passed to the home 
                screen.
        Alternative Scenario:
            1)  User presses cancel button: entered information is deleted and user is brought 
                to the start screen.
            2)  User presses back button: entered information is deleted and user is brought 
                to the start screen.

    Use Case 3: Restaurant search by location/type
        Actor: Registered User
        Preconditions: Account information, restaurant information
        Successful Condition: List of restaurants is retrieved from database and restaurant
                              information page is opened.
        Main Scenario:  
            1)  User will enter the restaurant location information in the text box labeled 
                "Location:".
            2)  User will enter the restaurant type information in the text box labeled "Type:".
            3)  User will click the search button located at the bottom of the screen and the 
                user will be brought to the results page.
            4)  User will look through the results and click the button labeled "more..." on the
                desired restaurant and the user will be brought to the restaurant information 
                page.
        Alternative Scenario:
            1)  User presses back button on the search page: entered information is deleted and 
                user is brought to the login screen.
            2)  User presses back button on the results page: user is brought to the search 
                screen.
            
    Use Case 4: Add to favorites list/history list
        Actor: Registered User
        Preconditions: Account information, restaurant information
        Successful Condition: Restaurant is stored into the database and is displayed in the
                              favorites list.
        Main Scenario:    
            1)	User will start the application and login. (refer to use case 2)
            2)	User will search for a restaurant based on the restaurant information. 
                (refer to use case 3)
            3)	User will click the "Add to favorites" button located at the top of the 
                restaurant information page. (Restaurant is automatically added to history
                when the Restaurant page is displayed)
            4)  User will open the navigation bar and click the "Favorites" button and be 
                brought to the list of restaurants with a newly added Restaurant.
        Alternative Scenario:
            1)  User will open the navigation bar and click the "History" button and be 
                brought to the list of restaurants with a newly added Restaurant.
                      
    Use Case 5: Clear favorites/history list
        Actor: Registered User
        Preconditions: Account information
        Successful Condition: Favorites list or history list is cleared and an empty favorites 
                              page is displayed.
        Main Scenario:    
            1)	User will start the application and login. (refer to use case 2)
            2)	User will open the navigation bar and click the "Account" button and be 
                brought to the account settings page.
            3)  User will click the "Clear favorites" button.
            4)  User will open the navigation bar and click the "Favorites" button and be 
                brought to the an empty list of restaurants.
        Alternative Scenario:
            1)  User will click the "Clear history" button
            2)  User will open navigation bar and click the "History" button and will be 
                brought to the history page and a empty list is displayed.
            
    Use Case 6: Change password
        Actor: Registered User
        Preconditions: Account information, new password
        Successful Condition: Password is changed.
        Main Scenario:    
            1)	User will start the application and login. (refer to use case 2)
            2)	User will open the navigation bar and click the "Account" button and be 
                brought to the account settings.
            3)  User will enter a password in the text box labeled "New password"
            4)  User will re-enter the password in the text box label "Confirm Password"
            5)  User will click the "Change password" button. The password is now changed to
                the new password.
        Alternative Scenario:
            n/a
            

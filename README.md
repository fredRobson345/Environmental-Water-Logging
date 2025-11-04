# Environmental Water Logging Software

## Overview
This is a program that uses java to log user inputs into a .CSV file and only allowing one user to update the file at a time.
To run this program you now only need to run the Main class unlike before where both the server and client class had to be run concurrently.
This will allow you, the user, to input several types of data, and then these are passed on and logged in the .CSV along with a timestamp.

## Why
This program was created to allow a user to create a logging system which could have multiple users having to enter data into it, the reason it only
allows one user to input data at a time is so that each part of the data is stored correctly. The example use of the Environmental Water readings can be 
change and used in a number of ways. The data from the .CSV file can also be taken and converted into a graph.

## Next steps
The first steps will be to continue to follow SOLID and DRY principles and design patterns to refactor the code.
Then I will be taking the data from the .CSV file and creating a graph from it, so that trends can be spotted.

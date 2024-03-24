

## WeirdSalad
I was able to implement a few of the issues requested in the following order:
1. Record deliveries
2. Make a customer order and the staff records it.
3. Reporting ->
    - Sum of deliveries.
    - Sum of sales.
    - Sum of current inventory.
    - All changes to the inventory.

I prioritized these in the above order, if we are going by the MVP ideas, we do not want to build one huge app due to time, so the MVP given the time would be to Record deliveries, Register customer order and show inventory changes, then went ahead to implement some reporting.

## Technologies used
- Database -> H2 (file based lightweight DB because we want to keep persistence as we want the app to survive and keep state even when server shuts down)
- Backend -> Spring boot. Java17
- Frontend -> Reactjs

### How to get the app started to test:
Since this repo has both the frontend and backend bits of the app, these are the steps to start the application and test.

1. Start the backend application from the root directory, This auto generates all the models and tables needed.
   Please make sure you have the Java JDK installed on your machine, OpenJdk 17 can be installed via homebrew.
   >   ./gradlew bootRun

2. Download the frontend project to the `weird-salad-frontend` directory
   https://github.com/DareJava/weirdsalad-frontend/
   Make sure you have node installed on your machine
   then run:
    - `npm install` to install all libs.
    - `npm start` to start service.
   
3. Because you start from scratch, i assume we will need some way to create a new Restaurant location, so i created a page with form for you to create by name and address. Go to http://localhost:3000/

   [Start page](https://drive.google.com/file/d/1aYAkT6kgSFHuDrxL-CN95Y7gouEygdCa/view?usp=sharing)  
   Enter the location values and i generated random Staff information, ingredients and Menu for that restaurant and redirects to the home page  
   Once all of this is done, everytime the start page is loaded it will take you to the homepage with all the data previously generated for the restaurant.

4. On the homepage, you can see boxes with the functionality  
   [Homepage](https://drive.google.com/file/d/1SklimH4eTI5CPSNNpLsIbpZSjsKOMWfZ/view?usp=sharing)
5. Steps to test:
    - Select a staff from the [dropdown](https://drive.google.com/file/d/18gs_WIxVlgfXw3OhI-zwcCo2DkfCwqFk/view?usp=sharing)
    - add count for all the ingredients generated
    - Click on the `record delivery` button, good to check the base of the page to see the inventory changes (refresh page)
    - Register a customer order, good to check the base of the page to see the inventory changes (refresh page).
    - Reload the page and check the [reports](https://drive.google.com/file/d/12_jnrhpTFmV8MfruR4QUWNZosZ4Qb-zD/view?usp=sharing)

### Some context
Controller:
- DeliveryController : handles everything that has to do with deliveries
- OrderController: handles everything with orders from customers.
- MachineStateController: Checks if the the machine has been initialised, if not it initialises with location data, in many cases it will reload data from the file db so they wont need to re-init again. First point of call for the system user via the homepage.
- ReportController : handles everything that has to do with reports, i.e numbers and inventory changes.
- IngredientController : handles everything that has to do with Ingredients, loads all the ingredients need to input for delivery.

I also made sure as much as possible to avoid direct access to the model/entities by creating boundaries, i made sure all access to the data goes through the repository.

Controller -> Service -> Repository -> Entity/Model




### Things i would like to improve if i had more time
1. Better design and structure for the frontend(auto reloads inclusive)
2. Took more time to get my calculations right around the stats and numbers especially with cost of ingredients, pretty sure i hard coded some values.
3. Abstraction of some of the  entities/models, could have made it a lot thinner.
4. Didnt have enough time to refactor the services in the backend as i could make the methods a bit smaller.
5. Tests!!!
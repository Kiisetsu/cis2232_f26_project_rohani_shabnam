# cis2232_f26_project_rohani_shabnam
Pet Adoption App

| Developer Team | Name |
| -------------- | ---- |
| BA/Business Client | Bridget |
| Developer | Shab |
| Project Manager/QA | Richard |

| Project Base Colour |
| ------------------- |
| Pastel Lavender (#D9C2F0) |

| Description |
| ----------- |
| This project will allow a user to calculate the cost associated with adopting some pets. Adoption costs are calculated based on the species, coat, and age of the pet. 
Only cats and dogs are supported and should be chosen from a drop-down menu. The coat should be selected from the following options: Short, Medium, Long, Bald. The sex should be selected from Male or Female (M or F is also acceptable). The age should be within the appropriate range: Cats can be between 0 and 20 while dogs can be between 0 and 15 (inclusive). All fields are required.
The user should be able to add multiple animals to the list and click a button to calculate the individual fees and overall cost. The user should be able to add, remove, and update entries in the list, and they should be able to clear all fields and the list with the click of a single button. |


Fields
| Name | Data Type | Description |
| ---- | --------- | ----------- |
|  ID  | Integer   | The unique ID of the animal (PK). |
| Name | String | The name of the animal. |
| Sex | String | The animal's sex. |
| Species | String | The animal's species. |
| Colour | String | The primary colour of pattern of the animal's coat. |
| Coat | String | The length of the animal's fur. |
| Age | Double | The animal's age in years. Decimals are supported and encouraged. |


| Calculation |
| ----------- |
| Once the user enters all the values, the program will calculate the cost to adopt each animal. 
Fee = (speciesCost + coatCost) * ageRate
speciesCost is 200 for cats and 500 for dogs.
coatCost is 50 for short, 100 for medium, 150 for long, and 200 for bald.
ageRate depends on the animal’s species and age.
The rates for ageRate are:
-	Young: 1.5
-	Adult: 1
-	Senior: 0.5
-	Geriatric: 0.25
Young covers ages from 0 up to 1 (not inclusive) for both cats and dogs. Adult covers ages 1 (inclusive) to 8 (not inclusive) for cats and 1 (inclusive) to 7 (not inclusive) for dogs. Senior covers ages 8 (inclusive) to 15 (not inclusive) for cats and 7 (inclusive) to 12 (not inclusive) for dogs. Geriatrics covers ages 15 (inclusive) to 20 (inclusive) for cats and 12 (inclusive) to 15 (inclusive) for dogs.
For example, the user wants to adopt an adult shorthaired cat.
Fee = (200 + 50) * 1 = 250 * 1 = $250
For example, the user wants to adopt a young dog with medium-length fur.
Fee = (500 + 100) * 1.5 = 600 * 1.5 = $900 |

## Sprint 2 - Animals by Species and Age Report (Work Item 5)

### Purpose
Lets staff list the animals at the adoption agency that match a chosen species and age range.

### Inputs
The user enters the criteria on the report input page (`/reports/animals`):

| Input | Type | Description |
|---|---|---|
| Species | Selection (Cat or Dog) | The species of animal to report on |
| Minimum age | Number (years) | Youngest age to include |
| Maximum age | Number (years) | Oldest age to include |

### Output (displayed on screen)
After submitting the form, the user is taken to the report results page, which shows:
- The criteria that were used (species, minimum age, maximum age)
- A table of every matching animal with: ID, name, sex, species, colour, coat and age
- The list is sorted by age, then name
- A bar chart showing the age of each matching animal
- A "No animals matched" message when nothing meets the criteria
- The name and location of the file the report was saved

## Sprint 3 - Animal CRUD with Spring Data JPA and Validation (Work Item 6)

### Purpose
Lets staff manage the animals at the adoption agency: view them in a list, add new animals, update existing ones and delete them. Animals are stored in the `Animal` table of the `cis2232_pet_adoption_app` MySQL database.

### Functionality

| Function | Page | Description |
|---|---|---|
| List | `/animals` | Shows all animals with their calculated adoption fee and the total of all fees |
| Add | `/animals/add` | Blank form to enter a new animal |
| Update | `/animals/edit/{id}` | Same form filled in with the animal's current values |
| Delete | Delete button on the list | Asks for confirmation, then removes the animal |

After add, update or delete, the user returns to the list with a confirmation message.

### Fields
| Field | Input | Rules |
|---|---|---|
| Name | Text | Required, maximum 50 characters |
| Sex | Dropdown | Required, Male or Female (M or F is accepted and saved as Male or Female) |
| Species | Dropdown | Required, Cat or Dog |
| Colour | Text | Required, maximum 30 characters |
| Coat | Dropdown | Required, Short, Medium, Long or Bald |
| Age (years) | Number | Required, must be a number, cannot be negative, cats 0 to 20 and dogs 0 to 15 (inclusive) |

### Validation
Validation runs on both add and update using Spring Validation (`jakarta.validation` annotations on the `Animal` entity). If any rule fails, the form is shown again with a message beside each field that has a problem, and nothing is saved.
- Required fields left blank show "... is required".
- Entering text that is not a number for age shows "Age must be a number".
- Age limits depend on species, so a dog aged 18 is rejected while a cat aged 18 is accepted. This check is made in the controller because it compares two fields.

### Adoption fee
Calculated by the program, not stored: fee = (species cost + coat cost) x age rate.
- Species cost: Cat 200, Dog 500
- Coat cost: Short 50, Medium 100, Long 150, Bald 200
- Age rate: young 1.5, adult 1, senior 0.5, geriatric 0.25 (age bands differ for cats and dogs)









# cis2232_f26_project_rohani_shabnam
Pet Adoption App

Developer Team
BA/Business Client - Bridget
Developer - Shab
Project Manager/QA - Richard

Project Base Colour
Pastel Lavender (#D9C2F0)

Description
This project will allow a user to calculate the cost associated with adopting some pets. Adoption costs are calculated based on the species, coat, and age of the pet. 
Only cats and dogs are supported and should be chosen from a drop-down menu. The coat should be selected from the following options: Short, Medium, Long, Bald. The sex should be selected from Male or Female (M or F is also acceptable). The age should be within the appropriate range: Cats can be between 0 and 20 while dogs can be between 0 and 15 (inclusive). All fields are required.
The user should be able to add multiple animals to the list and click a button to calculate the individual fees and overall cost. The user should be able to add, remove, and update entries in the list, and they should be able to clear all fields and the list with the click of a single button.


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


Calculation
Once the user enters all the values, the program will calculate the cost to adopt each animal. 
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
Fee = (500 + 100) * 1.5 = 600 * 1.5 = $900


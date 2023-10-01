# Score Engine Middleware

This is a middleware for consuming the transactions data from the CBS and sending it to the score engines for processing


## System configuration


| Key                        | Description                                                                                                      |
|----------------------------|------------------------------------------------------------------------------------------------------------------|
| MIDDLEWARE_ENDPOINT        | The endpoint for the middleware service                                                                          |
| MAIN_SCORE_ENGINE_ENDPOINT | The Score Engines endpoint for the Customer Score API calls                                                      |
| MIDDLEWARE_USERNAME        | The middleware username for authentication                                                                       |
| MIDDLEWARE_PASSWORD        | The middleware password for authentication                                                                       |
| CBS_ENDPOINT               | The CBS endpoint for querying the customer transactions                                                          |


### Step 1: Customer Subscription

The customer API > /api/customer/subscription is used to register a customer, by querying the CBS KYC API then on result saves the customer details to the database. 

### Step 2: Loan Application

The loan application API > /api/loan/request is used for customers loan application by:

 1. Check if the customer exists in the database, if not the calls KYC API to get the customer details and saves to the database
 2. Retrieves the customer and checks if they have an existing loan, if not then proceeds to the next step
 3. Makes a call to the SCORE ENGINE API to get the customer score
 4. When customer score is returned successfully, the score is validated to determine if the customer is eligible for a loan
 5. Depending on the score, the customer is either approved or rejected for a loan

### Step 2: Loan Status

The loan status API > /api/loan/status is used to check the status of a loan



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


### Step 1: Client Registration API

The Client Registration API > /api/middleware/access-token is used to register the middleware to the SCORE ENGINE. This is called by LMS Service via REST when retrieving the CLIENT_TOKEN for customer score querying.

### Step 2: Transactions API

The transactions API > /api//middleware/transactions is used by the SCORE ENGINE to retrieve the customers transactions for score calculations





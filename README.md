# Code Challenge

We use this code challenge to assess how you work and think as a software developer. It is not intended to accept or decline you as a candidate, but more as a way to have an open conversation 
about your solution.

The assignment is split into four parts, in order of difficulty. **Please don't spend more than four hours on this**. It's not necessary to finish all the assignment. Remember: it's a way for us to have an initial idea of your
way of working.

The high level design of the solution is more or less:

![img_1.png](img_1.png)

- The `database` has one table, `token`. The schema is

```
CREATE TABLE token (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    email TEXT,
    token text
);
```

we also added one row in the database:

`insert into token(email, token) values('some@peneo.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiUGVubmVvIEVtcGxveWVlIiwiYWdlIjozMCwibWFycmllZCI6dHJ1ZX0.zghtiqakvwRQbONvUXL2YzIpQGxqYKpDHxZMqOiU-1s')`

you can check the content of this token on https://jwt.io. It was generated using the method [JwtToken.kt::generateToken](src/main/kotlin/com/penneo/jwt/JwtToken.kt#L12)

![img_2.png](img_2.png)

- The `person-api` retrieves information about a specific person. It is hosted on https://penneo-person.sofia.qa.hre.cl/. To get the data about a person, call it this way: https://penneo-person.sofia.qa.hre.cl/person/X where X is the id of the person. It contains the following list (hardcoded)
```
{Id: 1, Name: "John Doe", Age: 30, Married: true},
{Id: 2, Name: "Jane Smith", Age: 28, Married: false},
{Id: 3, Name: "Alice Johnson", Age: 35, Married: true},
{Id: 4, Name: "John Smith", Age: 40, Married: true},
{Id: 5, Name: "Theodor Roosevelt", Age: 55, Married: false},
```

this means you should be able to send the following requests:

```
https://penneo-person.sofia.qa.hre.cl/person/1
https://penneo-person.sofia.qa.hre.cl/person/2
https://penneo-person.sofia.qa.hre.cl/person/3
https://penneo-person.sofia.qa.hre.cl/person/4
https://penneo-person.sofia.qa.hre.cl/person/5
```

- The `API` solution returns and retrieves information about the tokens. This is the `API` where you will work. We also included one ORM to query the database. 
You can find an example how to use it in [TokenResources.kt::getEmails](src/main/kotlin/com/penneo/TokenResources.kt#L25) 

## Task 1: Query the list of emails

The method `getEmails` in `TokenResources` should only return the list of emails from the table (as `strings`), but it's returning the complete `List<Token>`. Please change the method to only return the emails:

![img.png](img.png)

Make sure you are not returning an object with a property `email`, but a `List<String>` instead.

## Task 2: Create a new endpoint to get the information of a specific `tokenId`

Create a `GET` method that receives a `tokenId` and returns all the information of the token. For example, for the token we have saved in the table, we expect to receive:

```
{
  email: 'some@penneo.com',
  name: 'Penneo Employee',
  age: 30,
  married: true
}
```


## Task 3: Add an Endpoint to save tokens

Create a new endpoint to add tokens, in the same resource file `TokenResources`. The data should be saved to the `token` table

- The method should be a POST method

Here are the parameters you should pass to the endpoint:

```
{
  email: string
  name: string
  age: number
  married: boolean
}
```

- Use the same ORM to save the data
- In the table use the `email` property to save it in the `email` column
- Save the token (as string) using `JwtToken::generateToken` and passing the parameters `name, age, married`

## Task 4: Get the information from the `person-api`

Add a second `POST` method to save tokens. This time, instead of passing all the information to the endpoint, we will only send the email and the id of the person  

```
{
  email: string
  id: number
}
```

- Use the same ORM to save the data
- In the table use the `email` property to save it in the `email` column
- You need to query https://penneo-person.sofia.qa.hre.cl/person/:id to get the information of the person
- Save the token (as string) using `JwtToken::generateToken`. This time, you need to pass as parameters the values that come from the external endpoint

# Additional notes

- We expect you to spend no more than 4 hours in the assignment. If you want you can spend more, but please consider that as a recommendation for the efforts you need to put in this challenge.
- Make all the design decisions that you consider pertinent.
- We highly value a solution with tests.
- Any idea for performance improvements is welcome. You don’t need to implement them if you don’t have enough time.

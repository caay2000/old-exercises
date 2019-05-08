# Background

Our client, SkiTV, is a company that provides broadband entertainment services to its subscribers.
They have hired us to create new JSON REST APIs for their frontend on top of the infrastructure they already have.

## Current available infrastructure
Our client already has a repository infrastructure that they have asked us to reuse, and we cannot change it.

The dependency contains the following repositories:

### Customer Repository:

This repository will return a `Customer` object with the following attributes:

| Type    | Name          |
|---------|---------------|
| String  | id            |
| String  | accountNumber |
| String  | name          |
| String  | gender        |
| String  | birthday      |
| String  | email         |
| String  | phoneNumber   |
| Address | address       |

The `Address` object consists of the following attributes: 

| Type    | Name         |
|---------|--------------|
| String  | addressLine1 |
| String  | addressLine2 |
| String  | city         |
| String  | postCode     |
| String  | country      |

This repository also has a method to return a relationship between customers and their products. The 
`CustomerRepository` object has the following attributes:

| Type    | Name          |
|---------|---------------|
| String  | customerId    |
| String  | productId     |
| String  | effectiveFrom |
| String  | effectiveTo   |

### Products Repository:

The Product Repository will provide us with the `Product` object with the following attributes:

| Type    | Name  |
|---------|-------|
| String  | id    |
| String  | name  |
| String  | price |

# New Requirements

## 1) Customer information REST API

Our client has asked us to provide a REST API that will return information about their subscribers.

They are expecting the following `GET` http request `http://localhost:8080/com.merkleinc.interviewkata.customer/1` to return the following
com.merkleinc.interviewkata.customer information in json format:

```json
{
    "firstName":"Miguel",
    "middleName":"",
    "lastName":"Ferrandis",
    "gender":"M",
    "birthday":"12th December 1980",
    "age":"32",
    "address":"C/Bergara 22 P3 P6 08085-Barcelona (Spain)",
    "contactNumber":"+34 666 111 333",
    "contactEmail":"miguel@gmail.com"
}
```

However, as you might have realised, the repository is not returning the com.merkleinc.interviewkata.customer data in this format. 
We have already agreed with them the following rules:

* **Name:**
    * if it is composed only by one word, it is the firstName
    * if it is composed by two words, those should be firstName and lastName
    * if it is composed by three words, they represent firstName, middleName and lastName
    * if it is composed by more than three words, 1 -> firstName, 2 -> middleName, rest -> lastName

* **Gender:**
    * M -> male
    * F -> female
    * X -> unspecified

* **Birthday:**
    
    The repository will return an String with the date with format: `yyyy/mm/dd`.
    The REST API should return the date with the format of the example (`12th December 1980`).

* **Address:**
    
    We should concatenate all the fields to a `String` variable, like this example:
    `C/Bergara 22 P3 P6 08085-Barcelona (Spain)`.

* **Email:**
    
    _no rules for email_

* **Telephone Number:**

    We should return the international number format, using the following country list:
    
    - Spain: +34
    - UK:    +44
    - IRL:   +353


> NOTES FOR OURSELVES:
>
>Possible updates/changes
>* make a request for an unknown com.merkleinc.interviewkata.customer
>* do updates to the com.merkleinc.interviewkata.customer
 
## 2) Customer product information REST API

Our client also needs a REST API to provide a list of subscriptions a com.merkleinc.interviewkata.customer has.

They are expecting the following `GET` http request `http://localhost:8080/com.merkleinc.interviewkata.customer/1/products` to return the following
products information in json format:

```json
{
  "TODO" : "Define response format"
}

```

> NOTES FOR OURSELVES:
>
>We can also have the repo with two functions, getAll, and getAllByPartyId. Then make the com.merkleinc.interviewkata.customer repo to return partyId not for all the customers, so the ones with partyId will be able to use the filter function, while the others will need to pick the correct products using accountNumber
    

## 3) Customer invoice information REST API

Finally, our client needs a REST API to provide the monthly invoice for a com.merkleinc.interviewkata.customer. 
We will need to return the active products for the specified month, their prices and a total.

They are expecting the following `GET` http request `http://localhost:8080/com.merkleinc.interviewkata.customer/1/invoices` to return the following
invoice information in json format:

```json
{
	"com.merkleinc.interviewkata.customer": {
		"firstName": "Raquel",
		"middleName": "",
		"lastName": "",
		"address": "",
		"contactNumber": "",
		"contactEmail": ""
	},
	"invoiceDate:": "",
	"products": [{
			"name": "Product A",
			"price": 0.00
		},
		{
			"name": "product B",
			"price": 0.00
		}
	],
	"subtotal": 0.00,
	"total": 0.00
}
```

> NOTES FOR OURSELVES:
>
>- summary of the kata for the candidate
>- document for the interviewer (common questions for second interview or updates to the kata for pairing process)
>- create JARS and DATA for them
>- do the kata in your free time 
>- some test already done to validate kata
>- add instructions how to run Spring boot
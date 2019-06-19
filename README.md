# LOAN TEST

## How to compile the project
You need maven to compile the project.

From the root directory, simply run ```mvn clean package``` and it will compile the project and create a jar to be executed.

## How to run the program
The jar file is located inside target directory. You can run the program by 

```java -cp target\quote.jar Quote [path_to_your_csv_data_file] [amount]```

Your file should have the following format
```
Lender,Rate,Available
name,#.###,###
```

Your amount should be a numeric value from 1000 to 15000

## Domain consideration

After searching on how to calculate correctly the monthly compound interest I reached this [wikipedia](https://en.wikipedia.org/wiki/Compound_interest#Monthly_amortized_loan_or_mortgage_payments) page.
I implemented everything following this approach, causing me multiple hours of confusion given that my values are not the same as the given example. Finally, I decided to implement that, thinking that the given example was incorrect in purpose.

When writing this readme file, I wanted to review again the monthly compound interest formula, and luckily I found a solution... well, [THE solution](https://money.stackexchange.com/questions/54757/loan-repayment-calculation-and-monthly-compounding-interest-problem) 
Only had to change how the loan interest rate was calculated, and voila! 


## Technical considerations

For this project, I used maven, and the only external jar I used is junit. I also used an intellij plugin for running pitest and check mutation coverage.

The core code has been created following a TDD approach, mocking external dependencies manually without any library (creating my own stubs and spies).
I followed the Classic/Chicago TDD approach (testing module behaviour), this is why there is only on test class for the Loan api, testing all the logic from an initial given example.
For the other parts (not domain logic) I followed a mockist/London style, trying to mock my dependencies and discover what I need to reach my domain logic.

Some of the classes are not unit tested (the IO classes), as I don't want to test (or mock) system code. These classes has been tested manually. 

The application has the following structure

The main is located outside any package to be able to reduce the way to execute the application. This main, is creating the 3 needed parts and passing the to the App.
1. A CsvLendersProvider, that uses a FileSystemReader logic to be able to read from filesystem. This provider can be changed easily by any other kind of provider, like for example an XML file, but the business logic will not be modified.
2. A ConsoleSystemWriter, to be able to write to console. This can also be swapped by any other kind of writer without affecting the business logic.
3. A LoanApi, where the business logic lives. It receives a Lenders list and an amount and first calculates the rate with the lenders available. Then, with that rate, tries to calculate the monthly repayment following the monthly compound interest formula explained before.
4. The QuoteApp, is the orchestrator. it validates the input, calls the provider and the business logic, and then writes the output. 


## EXERCISE TO BE DONE

> There is a need for a rate calculation system allowing prospective borrowers to
> obtain a quote from our pool of lenders for 36 month loans. This system will
> take the form of a command-line application.
> 
> -You will be provided with a file containing a list of all the offers being made
> by the lenders within the system in CSV format, see the example market.csv file
> provided alongside this specification.
> 
> You should strive to provide as low a rate to the borrower as is possible to
> ensure that ****'s quotes are as competitive as they can be against our
> competitors'. You should also provide the borrower with the details of the
> monthly repayment amount and the total repayment amount.
> 
> Repayment amounts should be displayed to 2 decimal places and the rate of the
> loan should be displayed to one decimal place.
> 
> Borrowers should be able to request a loan of any £100 increment between
> £1000 and £15000 inclusive. If the market does not have sufficient offers from
> lenders to satisfy the loan then the system should inform the borrower that it
> is not possible to provide a quote at that time.
> 
> The application should take arguments in the form:
> ```
> cmd> [application] [market_file] [loan_amount]
> ``` 
>  
> Example:
> ```
>  cmd> quote.exe market.csv 1500
> ```
> 
> The application should produce output in the form:
> ```
>  cmd> [application] [market_file] [loan_amount]
>  Requested amount: £XXXX
>  Rate: X.X%
>  Monthly repayment: £XXXX.XX
>  Total repayment: £XXXX.XX
> ```
> Example:
> ```
> cmd> quote.exe market.csv 1000
> Requested amount: £1000
> Rate: 7.0%
> Monthly repayment: £30.78
> Total repayment: £1108.10
> ```
> 
> Remarks:
> - Please write your solution in Java
> - Your code should be tested to the appropriate degree.
> - The solution should exhibit readability, modularity and good use of abstraction.
> - The monthly and total repayment should use monthly compounding interest.
> - If you have any questions then don't hesitate to contact us.
# How to compile the project
You need maven to compile the project.

From the root directory, simply run ```mvn clean package``` and it will compile the project and create a jar to be executed.

# How to run the program
You can run the program using ```java -cp wordChainSolver [path_to_your_file]```

Your file should have the following format
```
[path to your dictionary file]
firstWord secondWord
```

You can have as many pairs of words as you want

# Considerations and assumptions

For this project, I used maven, and the only external jar I used is junit. I also used an intellij plugin for running pitest and check mutation coverage.

The core code has been created following a TDD approach, mocking external dependencies manually without any library (creating my own stubs and spies).
The TDD approach is behavioural (testing module behaviour) this is why the most important tests are the ones living in WordChainSolverTest (that are those tests that are testing the full application logic)

Some of the classes are not unit tested (the IO classes), as I don't want to test (or mock) system code. These classes has been tested manually. 

Leaving the IO part of the code outside, the code is divided in four simple steps.
 1. First of all we need to create a dictionary. This dictionary is organized to be efficient when searching for words with an specific size.
 2. With that dictionary we call our solver. Our solver is searching for all the possible word candidates for the next iteration (words with 1 character modified). These words will be sorted with a distance Graph (an structure that is sorting words depending on the number of characters different from what we want to reach)  
 3. With that sorted candidates, we try for each one of them recursively until we find a correct solution.
 4. Even if a solution if found, we continue searching for a better solution until no words are left, or until our solution is good enough (this is word size +1 words in the chain (This could also be improved))
  
The most important assumption in this code, is about performance. I coded it thinking that the memory and speed of our computer won't be a problem.


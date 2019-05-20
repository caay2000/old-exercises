Below is a programming problem. Please read the description thoroughly then create a program to solve the problem. Please Note: If you submit more than one solution, we will review only one.
- For the solution, we request that you use Java, Golang, Ruby, C#, Python or JavaScript.
- There must be a way to supply the application with the input data via text file
- The application must run
- You should provide sufficient evidence that your solution is complete by indicating that it works correctly against the supplied test data 

## Word Chain Solver :
Two items are connected by a word chain if it is possible to change one into the other by making a series of single-character changes, such that every intermediate form is also a word. For example, CAT and DOG are connected with a word chain because CAT, COT, COG and DOG are all items. DEMONIC and UMBRELLA are not. 

Write a program that takes a list of items (for example /usr/share/dict/items on a unix system or download the file from : https://github.com/dwyl/english-items/blob/master/items.txt if you are using windows) and then reads pairs of items on stdin and prints 'YES' if the items are connected by a chain, and 'NO' if they are not. If YES, the items in the word chain should be listed out on the console. The program should take the path to the word list from a file, and should then loop, reading pairs of whitespace-delimited items from the file and printing 'YES' or 'NO.' 

## Constraints:
Only one operation is allowed between items in the chain. 

The operation may consist of changing any single character but the length of the items should not change. 

All comparisons should be case insensitive. 

The answer should return the shortest word chain that solves each word chain puzzle. 

## Rules to remember while solving the assignment:
You may not use any external libraries to solve this problem, but you may use external
libraries or tools for building or testing purposes. Specifically, you may use unit-testing
libraries or build tools available for your chosen language (e.g., JUnit, Ant,). System security is very important to us and certain file extensions will be blocked for
security purposes, resulting in delays to your application. You should NOT include any
executable attachments, including those with .exe or .lib extensions. We need to be
able to run and build your code ourselves, so please submit your code as a zipped file of
source code and supporting files, without any compiled code. Please include a brief explanation of your design and assumptions, along with your code, as well as detailed instructions to run your application. We assess a number of things including the design aspect of your solution and your
object oriented programming skills. While these are small problems, we expect you to
submit what you believe is production-quality code; code that you’d be able to run, maintain, and evolve. You don’t need to gold plate your solution, however we are
looking for something more than a bare-bones algorithm. We want our hiring process to be fair, and for everyone to start from the same place. To
enable this, we request that you do not share or publish these problems. Please compress your files into a single .zip file before upload. Kindly ensure there are
no executables in your submission. Our system blocks executable files for security
purposes, and we want to avoid any delays in your process. Please share this assignment via an attachment in reply to this mail.
**Executables include asp, bat, class, cmd, com, cpl, dll, exe, fon, hta, ini, ins, iw, jar,
jsp, js, jse, pif, scr, shs, sh, vb, vbe, vbs, ws, wsc, wsf, wsh & msi
As a general rule, we allow two days from the date that you receive these instructions to
submit your code, but you can request more time from us if needed. If you have any
questions about the code as it relates to your interview process, please feel free to
contact us. Just to re-iterate, things we look for in code :
1. Clear and good object modelling
2. Clear and logical separation of responsibilities - (emphasis on single responsibility)
3. Unit tests
4. Easy to understand , not to complex code (something which can be extended by any
other person) and of course a functional completion.
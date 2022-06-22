## Cart tests

Guidelines a

### Build

*****************************************************
Run using maven command line or use intellij :
*****************************************************

mvn clean

mvn package -P shaded-build



### Execution

*****************************************************
Running tests using java :
*****************************************************

WINDOWS:

java -cp C:\${project_location where jar is ready}\com.proj\target\cart-data-bdd.jar com.proj.assgn.App2 C:\Users\Dell\IdeaProjects\com.proj\src\test\resources\Example3.txt voice,database

java -cp C:\${project_location where jar is ready}\com.proj\target\cart-data-bdd.jar com.proj.assgn.App1 C:\Users\Dell\IdeaProjects\com.proj\src\test\resources\Example1.txt

java -cp C:\${project_location where jar is ready}\com.proj\target\cart-data-bdd.jar com.proj.assgn.App1 C:\Users\Dell\IdeaProjects\com.proj\src\test\resources\Example2.txt



# EvoSuiteComparison

2 examples are provided in this project, ```WikiExample``` and ```FlagChecker```. 
For```WikiExample```, EvoSuite can produce a test suite with 100% branch and line coverage.

```FlagChecker``` has 2 more complicated cases, with 2 and 3 flags. Therefore, the test suites given by EvoSuite do not provide 100% branch and line coverage in 60 seconds.

## To run the examples ##
Please set the target class (for which you wish to get a test suite from EvoSuite) in the Assessment class, and run the ```main``` method.

### To make modifications in the code ###
* If you decide to play around with the code, make sure to generate the project jar file and then add it in the target folder _(as assess.jar is now added)_.
Make sure this new jar file is addressed in the Assessment.java _(as assess.jar is now addressed)_.
* Have a look at ```Assessment.java``` for inspiration, and see if you need to adjust any other parameter _(e.g. the target class)_!

###Tip!###
Other than looking at the printed coverage stats, you can look at the generated test suite in the ```evo-tests``` directory to analyze the test cases that EvoSuite could generate to optimize for branch and line criteria!
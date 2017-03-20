# Learning models for programs of the RERS challenge

This repo contains a basic setup for learning Mealy machine models of programs from the RERS challenge. It makes use of LearnLib ( http://learnlib.de/), a library for active model learning. A hassle-free, compiled version of the library has been added to this repository for convenience (getting started with LearnLib from their GitHub repo can be a bit confusing, because the library is not very well documented yet).

## Setup

Download and install the following before starting:
- Java SDK 1.8
- Graphviz (which contains the `dot` utility for visualizing the learned models, you need the command line tools, install using brew or apt-get or another package manager : brew install graphviz)

We recommend that you work inside an IDE for this project (as opposed to the CLI), as the different parameters for learning are hard-coded in the sources.

Importing the sources in your IDE of choice should be straightforward. Make sure to include `learnlib.jar` as a dependency. I have used JDK 1.8, but I think 1.7 should suffice.

The file `ExampleExperiment` contains a `main` method that learns a Mealy machine for the system described in `ExampleSUL` (take a look). When you run `ExampleExperiment.main()`, you should see the following output:

```
RERSLearner.ExampleExperiment

model size 1
0: Fri Mar 17 15:24:10 CET 2017
Hypothesis size: 1 states
learning queries/symbols: 3/3(3/3 this learning round)
testing queries/symbols: 4/6(1/3 this testing round)

model size 3
1: Fri Mar 17 15:24:10 CET 2017
Hypothesis size: 3 states
learning queries/symbols: 25/65(21/59 this learning round)
testing queries/symbols: 57/365(32/300 this testing round)

Finished learning!

Process finished with exit code 0
```

The learner should also have created the files `hypothesis0.dot`, `hypothesis0.pdf`, `hypothesis1.dot`, `hypothesis1.pdf`, `model.dot` and `model.pdf` in your working directory. If you do not see the pdf files, this means that the `dot` tool is not installed correctly --- so install the graphviz library again. Take a look at the pdf files. Do you see how the second hypothesis is a refinement of the fist? Do you see how the learned model relates to the code in `ExampleSUL`?

You are now ready to start working on the RERS problems.

## The RERS problems

We are going to learn models for the RERS 2016 or 2017 programs. These programs are not included in the sources, but you should have already seen them in previous assignments. For this assignment, we are going to use the binaries compiled from the C sources (although the Java sources should also work). Learning is a black-box technique, after all.

A learning setup (similar to the one you have seen before) can be found in `RERSExperiment`. Instead of using the example as a System Under Learning (SUL), this method uses `ProcessSUL` to connect with the RERS binaries. In `RERSExperiment.main()`, make sure to point the `ProcessSUL` to your compiled RERS binary:

```
        SUL<String,String> sul = new ProcessSUL(PATH_TO_BINARY);
```

In learning, you have to specify the input alphabet that the learner uses. In our case, this is straightforward, as it is specified at the top of the RERS source code. Change the following line if your RERS program uses more than 5 inputs:

```
        // the input alphabet
        Collection<String> inputAlphabet = ImmutableSet.of("1","2","3","4","5");
```

The following line can be modified to change certain aspects of the learning process. The actual setup for the learner is done in `BasicLearner`. Have a look to see what it does. Does the result change if you use a different learning or testing algorithm?

```
        BasicLearner.runControlledExperiment(sul, BasicLearner.LearningMethod.TTT, BasicLearner.TestingMethod.RandomWalk, inputAlphabet);
```

Happy learning!
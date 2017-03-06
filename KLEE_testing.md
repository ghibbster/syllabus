We are going to run concolic execution on programs from the RERS 2016 or 2017 challenges. The first step is to download and install KLEE:

https://klee.github.io/

This can be quite troublesome from scratch. The easiest way to install is via Docker:

https://www.docker.com/

Docker is a container, you can think of it as a small virtual machine. Use any of the standard isntallers for docker, then run:

docker pull klee/klee

to get KLEE, and run:

docker run --rm -ti --ulimit='stack=-1:-1' klee/klee

to start a small VM containing KLEE, more details can be found at: http://klee.github.io/docker/. One important command is to make Docker mount a local directory:

sudo docker run -v [PATH IN THE HOST MACHINE]:[PATH IN THE CONTAINER] -ti --name=[NAME OF THE CONTAINER] --ulimit='stack=-1:-1' klee/klee

which will load the host path and a path in the Docker container (I simply give it my home_dir).

Then download and unpack the RERS challenge programs:

The RERS 2016 reachability problems - http://www.rers-challenge.org/2016/problems/Reachability/ReachabilityRERS2016.zip
The RERS 2017 reachability training problems - http://rers-challenge.org/2017/problems/training/RERS17TrainingReachability.zip

The archives contain highly obfuscated c and java code.

The c code will not compile as given, we need to make a few changes, and some more to run KLEE on it.

First, replace:

```C++
    extern void __VERIFIER_error(int);
(line 6)
```

with:
   
```C++
#include <klee/klee.h>

void __VERIFIER_error(int i) {
    fprintf(stderr, "error_%d ", i);
    assert(0);
}

```

The assert causes a crash, which is what we want to find using KLEE.

Then, replace:

```C++
int main()
{
    // main i/o-loop
    while(1)
    {
        // read input
        int input;
        scanf("%d", &input);        
        // operate eca engine
        if((input != 2) && (input != 5) && (input != 3) && (input != 1) && (input != 4))
          return -2;
        calculate_output(input);
    }
}
```

with, e.g.,:

```C++
int main()
{
    int length = 20;
    int program[length];
    klee_make_symbolic(program, sizeof(program), "program");

    // main i/o-loop
    for (int i = 0; i < length; ++i) {
        // read input
        int input = program[i];
    if((input != 1) && (input != 2) && (input != 3) && (input != 4) && (input != 5)) return 0;
        // operate eca engine
        calculate_output(input);
    }
}
```

This makes all input symbolic, for up to length 20 inputs. KLEE will try to trigger new code branches with symbolic values for these 20 integer inputs. In contrast to AFL, we do not need to specify the input files. Everything is determined by analyzing the code.

.. TODO: give example command line ..

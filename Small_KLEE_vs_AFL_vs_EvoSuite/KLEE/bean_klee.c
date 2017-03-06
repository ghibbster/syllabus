#include <stdio.h>
#include <assert.h>
#include <klee/klee.h>

void f(int x, int y) {
	int z = 2*y;
     	if (x == 100000) {
         	if (x < z) {
             		//assert(0); /* error */
			klee_assert(0);
        	}
	}
}

int main(){
	int input1, input2;
        //scanf("%d %d", &input1, &input2);
	klee_make_symbolic(&input1,sizeof(int),"input1");
	klee_make_symbolic(&input2,sizeof(int),"input2");
	f(input1, input2);
}

#include <stdio.h>
#include <assert.h>

void f(int x, int y) {
	int z = 2*y;
     	if (x == 100000) {
         	if (x < z) {
             		assert(0); /* error */
        	}
	}
}

int main(){
	int input1, input2;
        scanf("%d %d", &input1, &input2);
	f(input1, input2);
}

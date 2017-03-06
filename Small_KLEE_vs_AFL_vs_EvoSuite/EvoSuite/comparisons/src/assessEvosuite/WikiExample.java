package assessEvosuite;

public class WikiExample {
		
	public WikiExample(int x, int y) {
		target(x, y);
	}

	public static void target(int x, int y) {
	    int z = 2*y;
	    if (x == 100000) {
	        if (x < z) {
	        	System.out.println("The line is covered!");
	        }
	    }
	}
	
}

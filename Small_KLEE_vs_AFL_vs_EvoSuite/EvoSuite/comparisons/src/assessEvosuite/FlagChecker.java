package assessEvosuite;

public class FlagChecker {

	
	public FlagChecker (){
	}
	
	
	public void assessFlags (double input , int input2){
//		check2Flags(input);
		check3Flags(input , input2);
	}
	
	
	private void check3Flags (double input , int input2){
		boolean flagOne = false;
		boolean flagTwo = false;
		boolean flagThree = false;
		
		if (isInputInRangeOne(input)){
			flagOne = true;
			if (isInputInRangeTwo(input)){
				flagTwo = true;
				if (flagTwo){
					if (input2 == 10){
						flagThree = true;
					}
				}
			}
			
		}
		
		if (flagThree){
			System.out.println("All flags were set to true! - EvoSuite is awesome! :) ");
		} else if (flagTwo){
			System.out.println("Flag two and one were set to true - EvoSuite needs improvement on flag problems!");
		} else if (flagOne){
			System.out.println("Only Flag one was set to true - EvoSuite needs more improvement on flag problems!");
		} else {
			System.out.println("No flag was set to true, EvoSuite needs a lot of improvement! :) ");
		}
		
		
	}
	
	private void check2Flags (double input){
		boolean flagOne = false;
		boolean flagTwo = false;
		
		if (isInputInRangeOne(input)){
			flagOne = true;
			if (isInputInRangeTwo(input)){
				flagTwo = true;
			}
		}
		
		if (flagTwo){
			System.out.println("Both flags were set to true! - EvoSuite is awesome! :) ");
		} else if (flagOne && !flagTwo){
			System.out.println("Flag one was set to true - EvoSuite needs improvement on flag problems!");
		} else {
			System.out.println("No flag was set to true, EvoSuite needs a lot of improvement! :) ");
		}
		
		
	}
	
	
	private boolean isInputInRangeOne(double input){
		if (input < 2.9 && input > 2.5){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isInputInRangeTwo(double input){
		if (input < 2.8 && input > 2.6){
			return true;
		} else {
			return false;
		}
	}
}

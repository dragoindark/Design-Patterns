package commandNew;


public class CommandPattern {
	public static void main(String[] args) {

		// Create user and let her compute
		int value=0;
		Command command = null;
		Calculator calculator = new Calculator();
		System.out.println("One by one with each concrete class");		
        Command adder=new CalculatorCommandAdd(calculator,82);
        adder.Execute();
        Command substract=new CalculatorCommandSubstract(calculator,67);
        substract.Execute();
        Command divide=new CalculatorCommandDivide(calculator,5);
        divide.Execute();
        Command times=new CalculatorCommandTimes(calculator,27);
        times.Execute();
        
        System.out.println("Using old and some new concrete class and executing all at the same time");        
        Command macro=new MacroExecute();
        macro.add(adder);
        macro.add(times);
        macro.add(new CalculatorCommandSubstract(calculator,82));
        macro.Execute();
		

		// Undo 4 commands
		//user.Undo(4);
		// Redo 2 commands
		//user.Redo(2);
	}
}

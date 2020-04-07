package command;


public class CommandPattern {
	public static void main(String[] args) {

		// Create user and let her compute
		Command command = null;
		User user = new User();
		Calculator calculator = new Calculator();

		command = new CalculatorCommandAdd(calculator, 100);
		MacroCommand root = new CompositeElement(command);
		//user.Compute(command);
		command = new CalculatorCommandSubstract(calculator, 50);
		root.Add(new PrimitiveElement(command));
		//user.Compute(command);
		command = new CalculatorCommandTimes(calculator, 10);
		root.Add(new PrimitiveElement(command));
		//user.Compute(command);
		command = new CalculatorCommandDivide(calculator, 2);
		root.Add(new PrimitiveElement(command));
		//user.Compute(command);
		root.executeMacro();
		

		// Undo 4 commands
		//user.Undo(4);
		// Redo 2 commands
		//user.Redo(2);
	}
}

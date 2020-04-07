package command;

import java.util.ArrayList;


//
//The classes and/or objects participating in this pattern are:
//
//1. Command  (Command)
//		- declares an interface for executing an operation.
//2. ConcreteCommand  (CalculatorCommand)
//		- defines a binding between a Receiver object and an action.
//		- implements Execute by invoking the corresponding operation(s) on Receiver
//3. Client  (Calculator Application)
//		- creates a ConcreteCommand object and sets its receiver.
//4. Invoker  (User)
//		- asks the command to carry out the request
//5. Receiver  (Calculator)
//		- knows how to perform the operations associated with carrying out
//		  a request. Any class may serve as a Receiver.
//
//

//"Command"
//
interface Command {
	public void Execute();
	public void UnExecute();
}
//4 elements of "ConcreteCommand"
//+
class CalculatorCommandAdd implements Command {
	// Constructor
	public CalculatorCommandAdd(Calculator calculator,int operand) { 
		_calculator = calculator;
		_operand = operand;
	}
	public void Execute() {
		_calculator.Action(_operator, _operand);
	}
	public void UnExecute() {
		_calculator.Action(_undoOperator, _operand);
	}
	private Calculator _calculator;
	private char _operator='+';
	private char _undoOperator='-';
	private int _operand;
	//override toString
	public String toString(){ 
    return "Operation is "+_operator+" the value is "+_operand+" the undo operator is "+_undoOperator;  
    }  
}
//-
class CalculatorCommandSubstract implements Command {
	// Constructor
	public CalculatorCommandSubstract(Calculator calculator,int operand) { 
		_calculator = calculator;
		_operand = operand;
	}
	public void Execute() {
		_calculator.Action(_operator, _operand);
	}
	public void UnExecute() {
		_calculator.Action(_undoOperator, _operand);
	}
	private Calculator _calculator;
	private char _operator='-';
	private char _undoOperator='+';
	private int _operand;
	//override toString
		public String toString(){ 
	    return "Operation is "+_operator+" the value is "+_operand+" the undo operator is "+_undoOperator;  
	    }  
}
// "/"
class CalculatorCommandDivide implements Command {
	// Constructor
	public CalculatorCommandDivide(Calculator calculator,int operand) { 
		_calculator = calculator;
		_operand = operand;
	}
	public void Execute() {
		_calculator.Action(_operator, _operand);
	}
	public void UnExecute() {
		_calculator.Action(_undoOperator, _operand);
	}
	private Calculator _calculator;
	private char _operator='/';
	private char _undoOperator='*';
	private int _operand;
	//override toString
		public String toString(){ 
	    return "Operation is "+_operator+" the value is "+_operand+" the undo operator is "+_undoOperator;  
	    }  
}




//*
class CalculatorCommandTimes implements Command {
	// Constructor
	public CalculatorCommandTimes(Calculator calculator,int operand) { 
		_calculator = calculator;
		_operand = operand;
	}
	public void Execute() {
		_calculator.Action(_operator, _operand);
	}
	public void UnExecute() {
		_calculator.Action(_undoOperator, _operand);
	}
	private Calculator _calculator;
	private char _operator='*';
	private char _undoOperator='/';
	private int _operand;
	//override toString
		public String toString(){ 
	    return "Operation is "+_operator+" the value is "+_operand+" the undo operator is "+_undoOperator;  
	    }  
}
// "Receiver"
//
class Calculator {
	public Calculator() { 
		current_value = 0; 
	}
	public void Action(char _operator, int operand) {
		switch (_operator) {
		case '+': current_value += operand; break;
		case '-': current_value -= operand; break;
		case '*': current_value *= operand; break;
		case '/': current_value /= operand; break;
		}
		System.out.println("Current value " + current_value + " (following "
				+ _operator + " " + operand + ")");
	}
	private int current_value;
}

// "Invoker"
class User {
	public User() {	current = 0; }
	public void Redo(int levels) {
		System.out.println("\n---- Redo " + levels + " levels ");
		// Perform redo operations
		for (int i = 0; i < levels; i++) {
			if (current < _commands.size()) {
				Command command = _commands.get(current++);
				command.Execute();
			}
		}
	}

	void Undo(int levels) {
		System.out.println("\n---- Undo " + levels + " levels ");
		// Perform undo operations
		for (int i = 0; i < levels; i++) {
			if (current > 0) {
				Command command = _commands.get(--current);
				command.UnExecute();
			}
		}
	}
	void Compute(Command command) {
		command.Execute();
		// Add command to undo list
		_commands.add(command);
		current++;
	}

	// Initializers.
	private int current;
	private ArrayList<Command> _commands = new ArrayList<Command>();
}


interface MacroCommand {
	void Add(MacroCommand d);
	void Remove(MacroCommand d);
	void Display(int indent);
	public Command getCommand();
	public void executeMacro();
}
//This is the "Leaf".
class PrimitiveElement implements MacroCommand {
	private Command c;
	public Command getCommand() { return c;}
	public PrimitiveElement(Command c) {this.c = c;}
	public void Add(MacroCommand c) {
		System.out.println("Addition to primitive element is illegal");
	}
	public  void Remove(MacroCommand c) {
		System.out.println("Removing from primitive element is illegal");
	}
	public void Display(int indent) {
		for(int i = 1;i <= indent;i++) 	System.out.print("-");
		System.out.println(getCommand().toString());
	}
	@Override
	public void executeMacro() {
		System.out.println("Macro commands are illegal inside primitive element");		
	}
}
// This is the "Composite"
class CompositeElement implements MacroCommand {
	private Command c;
	public Command getCommand() { return c;}
	public CompositeElement(Command c) {this.c = c;}
	public void Add(MacroCommand d) {elements.add(d);};
	public void Remove(MacroCommand d) {
		for (int i = 0; i< elements.size(); i++) {
			if (elements.get(i) == d) {
				elements.remove(i);
				return;
			}
		}
	}
	public	void Display(int indent) {
		for(int i = 0;i <= indent;i++) System.out.print("-");
		System.out.println(c.toString());

		// Display each child element on this node
		for (int i= 0; i< elements.size(); i++) {
			elements.get(i).Display(indent+2);
		}
	}
	public void executeMacro() {
		getCommand().Execute();
		for(int i=0;i<elements.size();i++) {
			elements.get(i).getCommand().Execute();
		}
	}
	private	ArrayList<MacroCommand> elements = new ArrayList<MacroCommand>();
}













































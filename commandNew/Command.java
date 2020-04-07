package commandNew;

import java.util.ArrayList;


interface Command{
	void add(Command com);
	void remove(Command com);
	void Execute();
	void UnExecute();
	char getOperator();
	char getUndoOperator();
	int getOperand();
	Calculator getCalculator();
}

class CalculatorCommandAdd implements Command{

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
	@Override
	public void add(Command com) {
		System.out.println("Addition to primitive element is illegal");
		
	}

	@Override
	public void remove(Command com) {
		System.out.println("Removing from primitive element is illegal");
		
	}
	@Override
	public char getOperator() {
		return _operator;
	}
	@Override
	public char getUndoOperator() {
		return _undoOperator;
	}
	@Override
	public int getOperand() {
		return _operand;
	}
	@Override
	public Calculator getCalculator() {
		return _calculator;
	}	
}
class CalculatorCommandSubstract implements Command{

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
	@Override
	public void add(Command com) {
		System.out.println("Addition to primitive element is illegal");
		
	}
	@Override
	public void remove(Command com) {
		System.out.println("Removing from primitive element is illegal");
		
	}
	@Override
	public char getOperator() {
		return _operator;
	}
	@Override
	public char getUndoOperator() {
		return _undoOperator;
	}
	@Override
	public int getOperand() {
		return _operand;
	}
	@Override
	public Calculator getCalculator() {
		return _calculator;
	}
}
class CalculatorCommandTimes implements Command{

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
	@Override
	public void add(Command com) {
		System.out.println("Addition to primitive element is illegal");
		
	}
	@Override
	public void remove(Command com) {
		System.out.println("Removing from primitive element is illegal");
		
	}
	@Override
	public char getOperator() {
		return _operator;
	}
	@Override
	public char getUndoOperator() {
		return _undoOperator;
	}
	@Override
	public int getOperand() {
		return _operand;
	}
	@Override
	public Calculator getCalculator() {
		return _calculator;
	}
}
class CalculatorCommandDivide implements Command{

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
	@Override
	public void add(Command com) {
		System.out.println("Addition to primitive element is illegal");
		
	}

	@Override
	public void remove(Command com) {
		System.out.println("Removing from primitive element is illegal");
		
	}	
	@Override
	public char getOperator() {
		return _operator;
	}
	@Override
	public char getUndoOperator() {
		return _undoOperator;
	}
	@Override
	public int getOperand() {
		return _operand;
	}
	@Override
	public Calculator getCalculator() {
		return _calculator;
	}
}

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


class MacroExecute implements Command{
	public void Execute() {
		for(int i=0;i<comms.size();i++) {
			comms.get(i).getCalculator().Action(comms.get(i).getOperator(),comms.get(i).getOperand());
		}
	}
	public void UnExecute() {
		for(int i=0;i<comms.size();i++) {
			comms.get(i).getCalculator().Action(comms.get(i).getUndoOperator(),comms.get(i).getOperand());
		}
	}
	private Calculator _calculator;
	private char _operator='-';
	private char _undoOperator='+';
	private int _operand;
	//override toString
	public String toString(){ 
    return "Operation is "+_operator+" the value is "+_operand+" the undo operator is "+_undoOperator;  
    }  
	public void add(Command com) {
		comms.add(com);
		
	}
	public void remove(Command com) {
		for (int i = 0; i< comms.size(); i++) {
			if (comms.get(i) == com) {
				comms.remove(i);
				return;
			}
		}
	}
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null || getClass()!=obj.getClass()) {
			return false;
		}
		MacroExecute macro= (MacroExecute) obj;
		return _operator==macro._operator && _operand==macro._operand && _undoOperator==macro._undoOperator && 
				_calculator==macro._calculator;
		
	}
	
	private	ArrayList<Command> comms = new ArrayList<Command>();
	@Override
	public char getOperator() {
		return _operator;
	}
	@Override
	public char getUndoOperator() {
		return _undoOperator;
	}
	@Override
	public int getOperand() {
		return _operand;
	}
	@Override
	public Calculator getCalculator() {
		return _calculator;
	}
	
}







































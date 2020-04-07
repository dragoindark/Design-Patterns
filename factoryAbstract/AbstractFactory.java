package factoryAbstract;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



abstract class drink{
	abstract public String displayName();
	abstract double getPrice();
	abstract String producer();
	abstract double co2Percentage();
}
//ABSTRACT CLASSES A,B AND C
abstract class colaBased extends drink{
	protected double price;
	protected String name;
	protected double percentage;
	protected String producer;
	public double getPrice(){ return price; }
	public String displayName() { return name;	}
	public double co2Percentage() {return percentage;}
	public String producer() {return producer;}
}

abstract class orangeBased extends drink{
	protected double price;
	protected String name;
	protected double percentage;
	protected String producer;
	public double getPrice(){ return price; }
	public String displayName() { return name;	}
	public double co2Percentage() {return percentage;}
	public String producer() {return producer;}
}

abstract class clearBased extends drink{
	protected double price;
	protected String name;
	protected double percentage;
	protected String producer;
	public double getPrice(){ return price; }
	public String displayName() { return name;	}
	public double co2Percentage() {return percentage;}
	public String producer() {return producer;}
}

class PEPSI_Cola extends colaBased{
	public PEPSI_Cola(double price,double percentage) {
		this.price=price;
		this.name=new String("Pepsi cola");
		this.percentage=percentage;
		this.producer=new String("PepsiCo Company");
		System.out.println(this.name+" created");
	}
}

class COCA_Cola extends colaBased{
	public COCA_Cola(double price,double percentage) {
		this.price=price;
		this.name=new String("Coca cola");
		this.percentage=percentage;
		this.producer=new String("Coca-Cola Company");
		System.out.println(this.name+" created");
	}
}

class YEDIGUN_Orange extends orangeBased{
	public YEDIGUN_Orange(double price,double percentage) {
		this.price=price;
		this.name=new String("Yedigün");
		this.percentage=percentage;
		this.producer=new String("PepsiCo Company");
		System.out.println(this.name+" orange based drink created");
	}
}

class FANTA_Orange extends orangeBased{
	public FANTA_Orange(double price,double percentage) {
		this.price=price;
		this.name=new String("Fanta");
		this.percentage=percentage;
		this.producer=new String("Coca-Cola Company");
		System.out.println(this.name+" orange based drink created");
	}
}

class FRUKO_Clear extends clearBased{
	public FRUKO_Clear(double price,double percentage) {
		this.price=price;
		this.name=new String("Fruko");
		this.percentage=percentage;
		this.producer=new String("PepsiCo Company");
		System.out.println(this.name+" clear drink created");
	}
}

class SPRITE_Clear extends clearBased{
	public SPRITE_Clear(double price,double percentage) {
		this.price=price;
		this.name=new String("Sprite");
		this.percentage=percentage;
		this.producer=new String("Coca-Cola Company");
		System.out.println(this.name+" clear drink created");
	}
}
//Abstract Factory
interface vendingMachine{
	abstract public drink createDrink(String drinkType,String producer,double price,double percentage);
	abstract public drink createHutDrink(String drinkType,double price,double percentage);
	abstract public drink createDominosDrink(String drinkType,double price,double percentage);
}
//Smart concrete factory with parameter type to find out the company type
class smartMachine implements vendingMachine{
	
	private static volatile smartMachine staticMulti=null;
	private static final Lock locker = new ReentrantLock(); 
	private smartMachine() {}
	public static smartMachine newMachine() {
		if(staticMulti==null) {
			locker.lock();
			try {
				if(staticMulti==null) {
					staticMulti=new smartMachine();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticMulti;
	}
	@Override
	public drink createDrink(String drinkType, String producer, double price, double percentage) {
		drink newDrink=null;
		if(drinkType.equalsIgnoreCase("cola")&& producer.equalsIgnoreCase("pepsi")) {
			newDrink=new PEPSI_Cola(price,percentage);
		}else if(drinkType.equalsIgnoreCase("cola")&& producer.equalsIgnoreCase("cocacola")) {
			newDrink=new COCA_Cola(price,percentage);
		}else if(drinkType.equalsIgnoreCase("orange") && producer.equalsIgnoreCase("pepsi")) {
			newDrink=new YEDIGUN_Orange(price,percentage);
		}else if(drinkType.equalsIgnoreCase("orange") && producer.equalsIgnoreCase("cocacola")) {
			newDrink=new FANTA_Orange(price,percentage);
		}else if(drinkType.equalsIgnoreCase("clear") && producer.equalsIgnoreCase("pepsi")) {
			newDrink=new FRUKO_Clear(price,percentage);
		}else if(drinkType.equalsIgnoreCase("clear") && producer.equalsIgnoreCase("cocacola")) {
			newDrink=new SPRITE_Clear(price,percentage);
		}else {
			newDrink=null;
			System.out.println("Illegal parameters");
		}
		return newDrink;
	}
	//Pizza hut have pepsi vending machine
	@Override
	public drink createHutDrink(String drinkType, double price, double percentage) {
		drink newDrink=null;
		if(drinkType.equalsIgnoreCase("cola")) {
			newDrink=new PEPSI_Cola(price,percentage);
		}else if(drinkType.equalsIgnoreCase("orange")) {
			newDrink=new YEDIGUN_Orange(price,percentage);
		}else if(drinkType.equalsIgnoreCase("clear")) {
			newDrink=new FRUKO_Clear(price,percentage);
		}else {
			newDrink=null;
			System.out.println("Illegal parameters");
		}
		return newDrink;
	}
	//Dominos have coca cola vending machine
	@Override
	public drink createDominosDrink(String drinkType, double price, double percentage) {
		drink newDrink=null;
		if(drinkType.equalsIgnoreCase("cola")) {
			newDrink=new COCA_Cola(price,percentage);
		}else if(drinkType.equalsIgnoreCase("orange")) {
			newDrink=new FANTA_Orange(price,percentage);
		}else if(drinkType.equalsIgnoreCase("clear") ) {
			newDrink=new SPRITE_Clear(price,percentage);
		}else {
			newDrink=null;
			System.out.println("Illegal parameters");
		}
		return newDrink;
	}
	
}

//Facade interface

class vendingFacade{
	private smartMachine vending;
	private static volatile vendingFacade staticFacade=null;
	private static final Lock locker=new ReentrantLock();
	private ArrayList<drink> newDrinks;

	
	public static vendingFacade newFacade() {
		if(staticFacade==null) {
			locker.lock();
			try {
				if(staticFacade==null) {
					staticFacade=new vendingFacade();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticFacade;
	}
	
	private vendingFacade() {
		vending=smartMachine.newMachine();
	}
	public smartMachine getMachine() {
		return vending;
	}
	void displayDrinks() {
		System.out.println("\tListing Drinks\n\t-------------");
		newDrinks.forEach(p  -> System.out.println("\t"+ p.displayName() + 
				            " " + p.getPrice()+" %"+p.co2Percentage()+" CO2 and producer is  "+p.producer()));
	}
	public void createDrinkMulti(smartMachine smart,String drinkType,String producer,double price,double percentage) {
		newDrinks=new ArrayList<drink>();
		newDrinks.add(smart.createDrink(drinkType, producer, price, percentage));		
	}
	public void createDrinkHut(smartMachine smart,String drinkType,double price,double percentage) {
		newDrinks=new ArrayList<drink>();
		newDrinks.add(smart.createHutDrink(drinkType, price, percentage));
	}
	public void createDrinkDominos(smartMachine smart,String drinkType,double price,double percentage) {
		newDrinks=new ArrayList<drink>();
		newDrinks.add(smart.createDominosDrink(drinkType, price, percentage));
	}
}

public class AbstractFactory {
	public static void main(String[] args) {
		
		vendingFacade myFacade=vendingFacade.newFacade();
		smartMachine smartMac=smartMachine.newMachine();
		System.out.println("Creating drink with multiple type cola producer pepsi");
		System.out.println("This one can take producer and drink type parameters and return the things");
		myFacade.createDrinkMulti(smartMac,"cola", "pepsi", 5.6, 8.2);
		myFacade.displayDrinks();
		System.out.println();
		System.out.println("Creating drink from Pizza Hut type orange");
		System.out.println("This one has the producer hardcoded as pepsi");
		myFacade.createDrinkHut(smartMac, "orange", 4.2, 4.7);
		myFacade.displayDrinks();
		System.out.println();
		System.out.println("Creating drink from Dominos type clear drink");
		System.out.println("This one has the producer hardcoded as coca-cola");
		myFacade.createDrinkDominos(smartMac, "clear", 2.6, 0.0);
		myFacade.displayDrinks();
		
	}
}
































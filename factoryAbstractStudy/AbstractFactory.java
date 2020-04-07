package factoryAbstractStudy;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

abstract class Part {
	abstract public String displayName();
	abstract double getPrice();
}
//An 'Abstract Product A' class 
//Engine base class.
abstract class Engine extends Part {
	protected double price;
	protected String name;
	public double getPrice(){ return price; }
	public String displayName() { return name;	}
}

//A 'ConcreteProduct A1' class
class OPEL_Engine extends Engine {	
	public OPEL_Engine(double p) { 
		price = p;
		name = new String("OPEL Engine");
		System.out.println("OPEL Engine is created..."); 
	}	
}
//A 'ConcreteProduct A2' class
class FORD_Engine extends Engine {
	public FORD_Engine(double p) { 
		price = p;
		name = new String("FORD Engine");
		System. out.println("FORD Engine is created...");
	}
}
//An 'Abstract Product B' class 
//Transmission base class
abstract class Transmission extends Part {
	protected double price;
	protected String name;
	public double getPrice(){ return price; }
	public String displayName() { return name;	}
}

//A 'ConcreteProduct B1' class
class OPEL_Transmission extends Transmission {
	public OPEL_Transmission(double p) { 
		price = p;
		name = new String("OPEL Transmission");
		System. out.println("OPEL Transmission is created...");
	}
}
//A 'ConcreteProduct B2' class
class FORD_Transmission extends Transmission {
	public FORD_Transmission(double p) { 
		price = p;
		name = new String("FORD Transmission");
		System. out.println("FORD Transmission is  created...");
	}
}	

abstract class CarFactory {
	abstract public Engine createEngine();
	abstract public Transmission createTransmission();
}
class concreteFacade{
	private OPELFactory opel;
	private FORDFactory ford;
	private multiFactory multi;
	private static volatile concreteFacade staticFacade=null;
	private static final Lock locker=new ReentrantLock();
	private ArrayList<Part> carParts;

	
	public static concreteFacade newFacade() {
		if(staticFacade==null) {
			locker.lock();
			try {
				if(staticFacade==null) {
					staticFacade=new concreteFacade();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticFacade;
	}
	
	private concreteFacade() {
		opel=OPELFactory.newOPELFactory();
		ford=FORDFactory.newFORDFactory();
		multi=multiFactory.newmultiFactory();
	}
	public OPELFactory getOpel() {
		return opel;
	}
	public FORDFactory getFord() {
		return ford;
	}
	public multiFactory getMulti() {
		return multi;
	}
	public void createCar(CarFactory fac){
		carParts=new ArrayList<Part>();
		carParts.add(fac.createEngine());
		carParts.add(fac.createTransmission());			
	}
	void displayParts() {
		System.out.println("\tListing Parts\n\t-------------");
		carParts.forEach(p  -> System.out.println("\t"+ p.displayName() + 
				            " " + p.getPrice()));
	}
	public void createCarMulti(multiPartFactory multiFac,String engineType,String transmissionType) {
		carParts=new ArrayList<Part>();
		carParts.add(multiFac.createEngine(engineType));
		carParts.add(multiFac.createTransmission(transmissionType));
		
	}
}
//A 'Concrete Factory' class
class OPELFactory extends CarFactory {
	private static volatile OPELFactory staticOpel=null;
	private static final Lock locker = new ReentrantLock(); 
	private OPELFactory() {}
	public static OPELFactory newOPELFactory() {
		if(staticOpel==null) {
			locker.lock();
			try {
				if(staticOpel==null) {
					staticOpel=new OPELFactory();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticOpel;
	}
	public OPEL_Engine createEngine() {
		return new OPEL_Engine (25000.00);
	}
	public OPEL_Transmission createTransmission() {
		return new OPEL_Transmission(10000.00);
	}
}

//Another 'Concrete Factory' class
class FORDFactory extends CarFactory {
	private static volatile FORDFactory staticFord=null;
	private static final Lock locker = new ReentrantLock(); 
	private FORDFactory() {}
	public static FORDFactory newFORDFactory() {
		if(staticFord==null) {
			locker.lock();
			try {
				if(staticFord==null) {
					staticFord=new FORDFactory();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticFord;
	}
	public FORD_Engine createEngine() {
		return new FORD_Engine (20000.00);
	}
	public FORD_Transmission createTransmission() {
		return new FORD_Transmission(12000.00);
	}
}

interface multiPartFactory{
	abstract public Engine createEngine(String type);
	abstract public Transmission createTransmission(String type);
}

class multiFactory implements multiPartFactory {
	
	private static volatile multiFactory staticMulti=null;
	private static final Lock locker = new ReentrantLock(); 
	private multiFactory() {}
	public static multiFactory newmultiFactory() {
		if(staticMulti==null) {
			locker.lock();
			try {
				if(staticMulti==null) {
					staticMulti=new multiFactory();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticMulti;
	}

	@Override
	public Engine createEngine(String type) {
		Engine newPart=null;
		if(type.equalsIgnoreCase("ford")) {
			newPart=new FORD_Engine(200000.0);
		}else if(type.equalsIgnoreCase("opel")) {
			newPart=new OPEL_Engine(2500000.0);
		}else {
			newPart=null;
		}
		return newPart;
	}

	@Override
	public Transmission createTransmission(String type) {
		Transmission newPart=null;
		if(type.equalsIgnoreCase("ford")) {
			newPart=new FORD_Transmission(12000.0);
		}else if(type.equalsIgnoreCase("opel")) {
			newPart=new OPEL_Transmission(10000.0);
		}else {
			newPart=null;
		}
		return newPart;
	}
	
}

//The 'Client'.
class BuildCar {
	// Object creation is delegated to factory.
	public void createCar(CarFactory factory) {
		parts = new ArrayList<Part>();
		parts.add(factory.createEngine());
		parts.add(factory.createTransmission());
	}
	void displayParts() {
		System.out.println("\tListing Parts\n\t-------------");
		parts.forEach(p  -> System.out.println("\t"+ p.displayName() + 
				            " " + p.getPrice()));
	}
	private ArrayList<Part> parts;
}
//Abstract Factory Method Design Pattern.
//Entry point into main application.
public class AbstractFactory {
	public static void main(String[] args) {
	   // Create factories.
	   /*CarFactory OPEL = OPELFactory.newOPELFactory();
	   CarFactory FORD = FORDFactory.newFORDFactory();

	   BuildCar car = new BuildCar();
	   System.out.println("Creating OPEL");
	   car.createCar(OPEL);
	   car.displayParts();
	   
	   System.out.println("Creating FORD");
	   car.createCar(FORD);
	   car.displayParts();*/
		
		concreteFacade myFacade=concreteFacade.newFacade();
		CarFactory OPEL = OPELFactory.newOPELFactory();
		CarFactory FORD = FORDFactory.newFORDFactory();
		
		System.out.println("Creating OPEL");
		myFacade.createCar(OPEL);
		myFacade.displayParts();
		System.out.println();
        System.out.println();
		System.out.println("Creating FORD");
		myFacade.createCar(FORD);
		myFacade.displayParts();
		System.out.println();
		System.out.println();
		System.out.println("Now creating with multiple factory ");
		String ford="ford";
		String opel="opel";
		
		multiPartFactory multiFac=multiFactory.newmultiFactory();
		System.out.println("Creating car with multiple factory,engine ford and transmission opel");
        myFacade.createCarMulti(multiFac, ford, opel);
		myFacade.displayParts();
	    
	}
}



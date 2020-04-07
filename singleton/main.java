package singleton;

public class main {

	public static void main(String[] args) {
		Part harddisk=new Harddisk();
		Part memory=new Memory();
		Part motherboard=new Motherboard();
		Price policy=new PricePolicy();
		
		policy.setTax(0.2);
		
		harddisk.setPolicy(policy);
		harddisk.setPrice(50);
		harddisk.setPrice(100);
		System.out.println(harddisk.getPrice()+" is the price");
		
		memory.setPolicy(policy);
	    memory.setPrice(50);
		memory.setPrice(75);
		System.out.println(memory.getPrice()+" is the price");
		
		motherboard.setPolicy(policy);
		motherboard.setPrice(50);
		motherboard.setPrice(75);
		motherboard.setPrice(motherboard.getPrice());
		System.out.println(motherboard.getPrice()+" is the price");

	}

}

package singleton;

interface Price {
	
	public abstract double tax();
	public abstract double totalPrice();
	public abstract void setDiscount(double discount);
	public abstract void setPrice(double price);
	public abstract void setTax(double tax);
	public abstract double getPrice();
	public abstract double getDiscount();

}

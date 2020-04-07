package singleton;

public class PricePolicy implements Price{
	private double price;
	private double tax;
	private double totalPrice=0;
	private double discount;

	@Override
	public double tax() {
		return tax;
	}

	@Override
	public double totalPrice() {
		return totalPrice;
	}

	@Override
	public void setDiscount(double discount) {
		this.discount=discount;
		
	}
	@Override
	public void setPrice(double price) {
		if(tax>0) {
			this.totalPrice=this.totalPrice+price*tax();
			this.price=price*tax();
			
		}if(discount>0) {
			this.totalPrice=this.totalPrice+price*getDiscount();
			this.price=price*getDiscount();
		}
		
		else {
			this.totalPrice=this.totalPrice+price;
			this.price=price;
			
		}		
	}

	@Override
	public void setTax(double tax) {
		this.tax=tax;
		
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public double getDiscount() {
		return discount;
	}

}

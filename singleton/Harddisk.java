package singleton;

public class Harddisk implements Part{
	public double diskSize;
	private Price newPrice;

	@Override
	public void setPolicy(Price newPrice) {
		this.newPrice=newPrice;
		
	}

	@Override
	public void setPrice(double price) {
		getPolicy().setPrice(price);
		
	}

	@Override
	public double getPrice() {
		return getPolicy().getPrice();
	}

	@Override
	public Price getPolicy() {
		return newPrice;
	}

	@Override
	public double getTotal() {
		return getPolicy().totalPrice();
	}

}

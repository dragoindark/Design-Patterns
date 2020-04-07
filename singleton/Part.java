package singleton;

interface Part {
   public abstract void setPolicy(Price newPrice);
   public abstract void setPrice(double price);
   public abstract double getPrice();
   public abstract Price getPolicy();
   public abstract double getTotal();
}

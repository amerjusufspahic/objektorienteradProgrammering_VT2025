package lab1;

public class Beverage {
	private String name;
	private double price;
	private int stock;
	private int timesSelected;

	public Beverage(String name, double price, int stock, int timesSelected) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.timesSelected = 0;
	}

	public void setStock(int stock) {
		this.stock= stock;
	}



	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public int getTimesSelected(){
		return timesSelected;
	}
	public void decreaseStock() {
		if(stock > 0) {
			stock--;
			timesSelected++;
		}
	}

}



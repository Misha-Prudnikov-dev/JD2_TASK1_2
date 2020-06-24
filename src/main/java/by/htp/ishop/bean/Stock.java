package by.htp.ishop.bean;

public class Stock {
	
	private int id;
	private int quantity;
	private double priceUnitProduct;
	private Store store;
	private Product product;
	
	public Stock() {
		
	}

	public Stock(int id, int quantity, double priceUnitProduct, Store store, Product product) {
		this.id = id;
		this.quantity = quantity;
		this.priceUnitProduct = priceUnitProduct;
		this.store = store;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPriceUnitProduct() {
		return priceUnitProduct;
	}

	public void setPriceUnitProduct(double priceUnitProduct) {
		this.priceUnitProduct = priceUnitProduct;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(priceUnitProduct);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((store == null) ? 0 : store.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(priceUnitProduct) != Double.doubleToLongBits(other.priceUnitProduct))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity != other.quantity)
			return false;
		if (store == null) {
			if (other.store != null)
				return false;
		} else if (!store.equals(other.store))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", quantity=" + quantity + ", priceUnitProduct=" + priceUnitProduct + ", store="
				+ store + ", product=" + product + "]";
	}
	
	
	

}

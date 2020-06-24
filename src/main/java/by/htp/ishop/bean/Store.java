package by.htp.ishop.bean;

public class Store {

	private int id;
	private String title;
	private String description;
	private String email;
	private String phone;
	
	private StockAddress stockAddress;
	
	public Store() {
		
	}

	public Store(int id, String title, String description, String email, String phone, StockAddress stockAddress) {
 		this.id = id;
		this.title = title;
		this.description = description;
		this.email = email;
		this.phone = phone;
		this.stockAddress = stockAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public StockAddress getStockAddress() {
		return stockAddress;
	}

	public void setStockAddress(StockAddress stockAddress) {
		this.stockAddress = stockAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((stockAddress == null) ? 0 : stockAddress.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Store other = (Store) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (stockAddress == null) {
			if (other.stockAddress != null)
				return false;
		} else if (!stockAddress.equals(other.stockAddress))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", title=" + title + ", description=" + description + ", email=" + email + ", phone="
				+ phone + ", stockAddress=" + stockAddress + "]";
	}

	 
	
	
}

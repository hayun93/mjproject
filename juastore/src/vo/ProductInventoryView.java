package vo;

public class ProductInventoryView {
	private String product_code;
	private String product_name;
	private int product_price;
	private String image;
	private String etc;
	private int inventory_amount;
	
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public int getInventory_amount() {
		return inventory_amount;
	}
	public void setInventory_amount(int inventory_amount) {
		this.inventory_amount = inventory_amount;
	}
}

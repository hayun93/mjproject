package vo;

import java.util.Date;

public class Inventory {
	private int inventory_num;
	private String product_code;
	private Date inventory_date;
	private int inventory_amount;
	private String inventory_state;
	
	public int getInventory_num() {
		return inventory_num;
	}
	public void setInventory_num(int inventory_num) {
		this.inventory_num = inventory_num;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public Date getInventory_date() {
		return inventory_date;
	}
	public void setInventory_date(Date inventory_date) {
		this.inventory_date = inventory_date;
	}
	public int getInventory_amount() {
		return inventory_amount;
	}
	public void setInventory_amount(int inventory_amount) {
		this.inventory_amount = inventory_amount;
	}
	public String getInventory_state() {
		return inventory_state;
	}
	public void setInventory_state(String inventory_state) {
		this.inventory_state = inventory_state;
	}
}

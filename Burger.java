public class Burger{
	private String orderId;
	private String customerId;
	private String customerName;
	private int orderQuantity;
	private int orderStatus;
	private final double BURGERPRICE = 500.00;
	
	//--<Default constructor>--//
	public Burger(){}
	
	public Burger(String orderId, String customerId, String customerName, int orderQuantity, int orderStatus){
		this.orderId = orderId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.orderQuantity = orderQuantity;
		this.orderStatus = orderStatus;
	}
	
	public Burger(String customerId, String customerName, int orderQuantity){
		this.customerId = customerId;
		this.customerName = customerName;
		this.orderQuantity = orderQuantity;
	}
	
	public void setOrderId(String orderId){this.orderId = orderId;}
	public void setCustomerName(String customerName){this.customerName = customerName;}
	public void setCustomerId(String customerId){this.customerId = customerId;}
	public void setOrderQuantity(int orderQuantity){this.orderQuantity = orderQuantity;}
	public void setOrderStatus(int orderStatus){this.orderStatus = orderStatus;}
	
	public String getOrderId(){return orderId;}
	public String getCustomerId(){return customerId;}
	public String getCustomerName(){return customerName;}
	public int getOrderQuantity(){return orderQuantity;}
	public int getOrderStatus(){return orderStatus;}
	
	public boolean equals(Burger burger){
		return this.orderId.equalsIgnoreCase(burger.orderId);
	}
	
	public String getOrderStatus(int orderType){
		return (orderType==0)?"Canceled":(orderType==1)?"Processing":(orderType==2)?"Delivered":null;
	}
	
	public int getOrderStatusNumber(String orderType){
		return (orderType.equalsIgnoreCase("Canceled"))?0:(orderType.equalsIgnoreCase("Processing"))?1:(orderType.equalsIgnoreCase("Delivered"))?2:-1;
	}
	
	
	
	public String toString(){
		return orderId+","+customerId+","+customerName+","+Integer.toString(orderQuantity)+","+Integer.toString(orderStatus);
	}
	
	public double getBurgerPrice(){
		return BURGERPRICE;
	}
	
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class AddNewOrderForm extends JFrame {
    
    // Declare all components
    private JLabel headerLabel;
    private JLabel orderIdLabel;
    private JLabel customerIdLabel;
    private JLabel customerNameLabel;
    private JLabel burgerQtyLabel;
    private JLabel orderStatusLabel;
    private JLabel netTotalLabel;
    private JLabel copyrightLabel;
    
    private JTextField orderIdField;
    private JTextField customerIdField;
    private JTextField customerNameField;
    private JTextField burgerQtyField;
    private JTextField orderStatusField;
    private JLabel netTotalField;
    
    private JButton placeOrderButton;
    private JButton backToHomeButton;
    private JButton cancelButton;
    
    Burger burger;
    
    public AddNewOrderForm() {
		burger = new Burger();
		
        // Set up the main frame
        setTitle("Place Order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,550);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        
        // Create main panel with light gray background
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setLayout(null);
        
        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 900, 80);
        headerPanel.setBackground(new Color(207, 85, 85)); // Red color matching the image
        headerPanel.setLayout(new BorderLayout());
        
        // Initialize header label
        headerLabel = new JLabel("Place Order");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        
        // Initialize labels
        orderIdLabel = new JLabel("Order Id :");
        orderIdLabel.setBounds(80, 140, 100, 25);
        orderIdLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        customerIdLabel = new JLabel("Customer Id :");
        customerIdLabel.setBounds(80, 190, 120, 25);
        customerIdLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        customerNameLabel = new JLabel("Customer Name :");
        customerNameLabel.setBounds(80, 240, 140, 25);
        customerNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        burgerQtyLabel = new JLabel("Burger QTY :");
        burgerQtyLabel.setBounds(80, 290, 120, 25);
        burgerQtyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        orderStatusLabel = new JLabel("Order Status :");
        orderStatusLabel.setBounds(80, 340, 120, 25);
        orderStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        netTotalLabel = new JLabel("NET Total : ");
        netTotalLabel.setBounds(550, 470, 200, 30);
        netTotalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        copyrightLabel = new JLabel("©Ravindu Yasith");
        copyrightLabel.setBounds(40, 520, 100, 20);
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        copyrightLabel.setForeground(Color.GRAY);
        
        // Initialize text fields
        orderIdField = new JTextField(5);
        orderIdField.setBounds(240, 140, 150, 30);
        orderIdField.setFont(new Font("",1,20));
        orderIdField.setEditable(false);
        String orderId = generateOrderId();
		orderIdField.setText(orderId);
        orderIdField.setFont(new Font("Arial", Font.PLAIN, 14));
        orderIdField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        
        customerIdField = new JTextField(10);
        customerIdField.setBounds(240, 190, 150, 30);
        customerIdField.requestFocus();
        customerIdField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				String customerId = customerIdField.getText();
				if(customerId.length()!=10 || Character.getNumericValue(customerId.charAt(0))!=0){
					JOptionPane.showMessageDialog(null,"Invalid input !... Enter a valid phone number again..");
					customerIdField.setText("");
					customerIdField.requestFocus();
				}else{
					String[] ifFoundCustomer = checkCustomerByCustomerId(customerId);
					if(ifFoundCustomer==null){
						customerNameField.requestFocus();
					}else{
						customerNameField.setText(ifFoundCustomer[2]);
						customerNameField.setEditable(false);
						burgerQtyField.requestFocus();
					}
				}
			}
		});
        customerIdField.setFont(new Font("Arial", Font.PLAIN, 14));
        customerIdField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        
        customerNameField = new JTextField(15);
        customerNameField.setBounds(240, 240, 200, 30);
        customerNameField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				burgerQtyField.requestFocus();
			}
		});
        customerNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        customerNameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        
        burgerQtyField = new JTextField(3);
        burgerQtyField.setBounds(240, 290, 150, 30);
        burgerQtyField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				int burgerQuantity = Integer.parseInt(burgerQtyField.getText());
				if(burgerQuantity<1){
					JOptionPane.showMessageDialog(null,"Quantity should be positive !");
					burgerQtyField.requestFocus();
				}else{
					orderStatusField.setText("Processing");
					netTotalField.setVisible(true);
					netTotalField.setText(Double.toString(burgerQuantity*burger.getBurgerPrice()));
				}
			}
		});
        burgerQtyField.setFont(new Font("Arial", Font.PLAIN, 14));
        burgerQtyField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        
        orderStatusField = new JTextField(10);
        orderStatusField.setBounds(240, 340, 150, 30);
        orderStatusField.setFont(new Font("Arial", Font.PLAIN, 14));
        orderStatusField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        
        netTotalField = new JLabel();
        netTotalField.setBounds(640, 470, 100, 30);
        netTotalField.setFont(new Font("Arial", Font.BOLD, 16));
        netTotalField.setVisible(false);
        
        placeOrderButton = new JButton("Place Order") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        placeOrderButton.setBounds(550, 200, 150, 40);
        placeOrderButton.setFont(new Font("Arial", Font.BOLD, 14));
        placeOrderButton.setBackground(new Color(76, 175, 80)); // Green color
        placeOrderButton.setForeground(Color.WHITE);
        placeOrderButton.setBorder(BorderFactory.createEmptyBorder());
        placeOrderButton.setFocusPainted(false);
        placeOrderButton.setContentAreaFilled(false);
        placeOrderButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        backToHomeButton = new JButton("Back to home Page") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        backToHomeButton.setBounds(550, 260, 150, 40);
        backToHomeButton.setFont(new Font("Arial", Font.BOLD, 12));
        backToHomeButton.setBackground(new Color(207, 85, 85)); // Red color matching header
        backToHomeButton.setForeground(Color.WHITE);
        backToHomeButton.setBorder(BorderFactory.createEmptyBorder());
        backToHomeButton.setFocusPainted(false);
        backToHomeButton.setContentAreaFilled(false);
        backToHomeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        cancelButton = new JButton("Cancel") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        cancelButton.setBounds(550, 320, 150, 40);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(207, 85, 85)); // Red color
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBorder(BorderFactory.createEmptyBorder());
        cancelButton.setFocusPainted(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add components to main panel
        mainPanel.add(headerPanel);
        mainPanel.add(orderIdLabel);
        mainPanel.add(customerIdLabel);
        mainPanel.add(customerNameLabel);
        mainPanel.add(burgerQtyLabel);
        mainPanel.add(orderStatusLabel);
        mainPanel.add(netTotalLabel);
        mainPanel.add(copyrightLabel);
        mainPanel.add(orderIdField);
        mainPanel.add(customerIdField);
        mainPanel.add(customerNameField);
        mainPanel.add(burgerQtyField);
        mainPanel.add(orderStatusField);
        mainPanel.add(netTotalField);
        mainPanel.add(placeOrderButton);
        mainPanel.add(backToHomeButton);
        mainPanel.add(cancelButton);
        
        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);
        
        // Add action listeners (you can implement these as needed)
        placeOrderButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				
				//--
				
				int response = JOptionPane.showConfirmDialog(
                null,
                "Do you confirm this order?",
                "Confirm",
                JOptionPane.YES_NO_OPTION);

				if(response == JOptionPane.YES_OPTION) {
					System.out.println("User clicked YES");
					String OrderId = orderIdField.getText();
					String customerId = customerIdField.getText();
					String customerName = customerNameField.getText();
					String orderQuantity = burgerQtyField.getText();
					try{
						FileWriter fw = new FileWriter("Burger.txt",true);
						fw.write(OrderId+","+customerId+","+customerName+","+orderQuantity+","+"1\n");
						fw.close();
						JOptionPane.showMessageDialog(null,"Order Added Success");
						
						
					}catch(IOException ex){
						ex.printStackTrace();
						
					}
					
						
					//
					int orderAgain = JOptionPane.showConfirmDialog(
					null,
					"Do you want to place another order?",
					"Confirm",
					JOptionPane.YES_NO_OPTION);

					if (orderAgain == JOptionPane.YES_OPTION) {
						System.out.println("User clicked YES");
						//--add here
						AddNewOrderForm.this.dispose();
						new AddNewOrderForm().setVisible(true);
					} else if (orderAgain == JOptionPane.NO_OPTION) {
						System.out.println("User clicked NO");
					} else {
						System.out.println("User closed the dialog");
					}
					//
					
				} else if (response == JOptionPane.NO_OPTION) {
					System.out.println("User clicked NO");
				} else {
					System.out.println("User closed the dialog");
				}
				
				//--
				
				
				//String OrderId = orderIdField.getText();
				//String customerId = customerIdField.getText();
				//String customerName = customerNameField.getText();
				//String orderQuantity = burgerQtyField.getText();
				//try{
				//	FileWriter fw = new FileWriter("Burger.txt",true);
				//	fw.write(OrderId+","+customerId+","+customerName+","+orderQuantity+","+burger.getOrderStatus(1)+"\n");
				//	fw.close();
				//	JOptionPane.showMessageDialog(null,"Order Added Success");
				//	orderIdField.setText(generateOrderId());
				//	customerIdField.setText("");
				//	customerNameField.setText("");
				//	burgerQtyField.setText("");
				//	orderStatusField.setText("");
				//	netTotalField.setText("");
				//	customerIdField.requestFocus();
				//	
				//}catch(IOException ex){
				//	ex.printStackTrace();
				//	
				//}
			}
		});
        
        backToHomeButton.addActionListener(e -> {
			new BurgerShopMainForm().setVisible(true);
			AddNewOrderForm.this.setVisible(false);
            // Add your back to home logic here
            System.out.println("Back to Home button clicked");
        });
        
        cancelButton.addActionListener(e -> {
            // Add your cancel logic here
            System.out.println("Cancel button clicked");
        });
        
        
    }
    
    private String generateOrderId(){
			try{
				Scanner input = new Scanner(new File("Burger.txt"));
				String line = null;
				while(input.hasNext()){
					line = input.nextLine();
				}
				input.close();
				if(line == null){
					return "B0001";
				}
					String lastId = line.substring(0,5);
					int lastIdNo = Integer.parseInt(lastId.substring(1));
					return String.format("B%04d",lastIdNo+1);
				
			}catch(FileNotFoundException ex){
				ex.printStackTrace();
				return null;
			}
	}
	
	private String[] checkCustomerByCustomerId(String customerId){
		try{
			Scanner input = new Scanner(new File("Burger.txt"));
			String line = null;
			while(input.hasNext()){
				line = input.nextLine();
				if(customerId.equalsIgnoreCase(line.substring(6,16))){
					break;
				}
			}
			if(line!=null && customerId.equalsIgnoreCase(line.substring(6,16))){
				String[] foundOne = line.split(",");
				return foundOne;
				//int orderQuantity = Integer.parseInt(foundOne[3]);
				//int orderStatus = Integer.parseInt(foundOne[4]);
				//foundOne[3] = orderQuantity;
				//foundOne[4] = orderStatus;
				//Burger foundCustomer = new Burger(foundOne[0],foundOne[1],foundOne[2],Integer.parseInt(foundOne[3]),Integer.parseInt(foundOne[4]));
				//return foundCustomer;
			}else{
				return null;
			}
		}catch(IOException ex){
			ex.printStackTrace();
			return null;
		}
	}
    
    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> {
        //    try {
        //        // Set look and feel to system default
        //        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        //    } catch (Exception e) {
        //        e.printStackTrace();
        //    }
        //    
        //    // Create and show the form
        //    AddNewOrderForm form = new AddNewOrderForm();
        //    form.setVisible(true);
        //});
    }
}

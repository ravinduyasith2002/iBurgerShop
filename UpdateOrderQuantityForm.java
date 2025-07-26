import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class UpdateOrderQuantityForm extends JFrame {
    
    // Declare all components at the beginning
    private JPanel headerPanel;
    private JPanel mainPanel;
    private JPanel bottomPanel;
    
    private JLabel headerLabel;
    private JLabel orderIdLabel;
    private JLabel customerIdLabel;
    private JLabel nameLabel;
    private JLabel orderQtyLabel;
    private JLabel orderStatusLabel;
    private JLabel totalLabel;
    private JLabel totalValueLabel;
    private JLabel errorMessageLabel;
    private JLabel copyrightLabel;
    
    private JTextField orderIdField;
    private JTextField customerIdField;
    private JTextField nameField;
    private JTextField orderQtyField;
    private JTextField orderStatusField;
    
    private JButton updateOrderButton;
    private JButton backButton;
    private int currentOrderStatus;
    
    public UpdateOrderQuantityForm() {
        initializeComponents();
        setupLayout();
        addActionListeners();
        configureFrame();
    }
    
    private void initializeComponents() {
        // Initialize panels
        headerPanel = new JPanel();
        mainPanel = new JPanel();
        bottomPanel = new JPanel();
        
        // Initialize labels
        headerLabel = new JLabel("Update Quantity", SwingConstants.CENTER);
        orderIdLabel = new JLabel("Order Id");
        customerIdLabel = new JLabel("Customer Id");
        nameLabel = new JLabel("Name");
        orderQtyLabel = new JLabel("Order QTY");
        orderStatusLabel = new JLabel("Order Status");
        totalLabel = new JLabel("Total");
        totalValueLabel = new JLabel("");
        errorMessageLabel = new JLabel("");
        copyrightLabel = new JLabel("@Ravindu Yasith");
        
        // Initialize text fields
        orderIdField = new JTextField();
        customerIdField = new JTextField();
        nameField = new JTextField();
        orderQtyField = new JTextField();
        orderStatusField = new JTextField();
        
        // Initialize buttons
        updateOrderButton = new JButton("Update Order");
        backButton = new JButton("Back");
        
        // Configure components
        configureComponents();
    }
    
    private void configureComponents() {
		Burger burger = new Burger();
        // Header configuration
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.setBackground(Color.decode("#A82222")); // Dark red color
        headerPanel.setPreferredSize(new Dimension(900, 80));
        
        // Error message label configuration
        errorMessageLabel.setForeground(Color.RED);
        errorMessageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Copyright label configuration
        copyrightLabel.setForeground(Color.GRAY);
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        
        // Labels configuration
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        orderIdLabel.setFont(labelFont);
        customerIdLabel.setFont(labelFont);
        nameLabel.setFont(labelFont);
        orderQtyLabel.setFont(labelFont);
        orderStatusLabel.setFont(labelFont);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalValueLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        //make the total value by add action listener to orderQty field
        orderQtyField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				totalValueLabel.setText(Double.toString(burger.getBurgerPrice()*(Integer.parseInt(orderQtyField.getText()))));
			}
		});
        
        // Text fields configuration
        Font fieldFont = new Font("Arial", Font.BOLD, 20);
        Dimension fieldSize = new Dimension(200, 35);
        //orderIdField.requestFocus();
        orderIdField.setFont(fieldFont);
        orderIdField.setPreferredSize(fieldSize);
        //Check orderId field
        orderIdField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				String orderId = orderIdField.getText();
				if(orderId.length()!=5 || orderId.charAt(0)!='B'){
					JOptionPane.showMessageDialog(null,"Invalid order Id\nEnter again");
					orderIdField.requestFocus();
				}else{
					Burger order = checkForPreparing(orderId);
					if(order==null){
						JOptionPane.showMessageDialog(null,"No order in this id !");
					}else{
						customerIdField.setText(order.getCustomerId());
						customerIdField.setEditable(false);
						nameField.setText(order.getCustomerName());
						nameField.setEditable(false);
						currentOrderStatus = order.getOrderStatus();
						orderStatusField.setText(burger.getOrderStatus(currentOrderStatus));
						orderStatusField.setEditable(false);
						totalValueLabel.setText(Double.toString(burger.getBurgerPrice()*order.getOrderQuantity()));
						orderQtyField.setText(Integer.toString(order.getOrderQuantity()));
						if(order.getOrderStatus()!=1){
							orderQtyField.setEditable(false);
							Color darkRed = new Color(230, 0, 0); 
							errorMessageLabel.setForeground(darkRed);
							errorMessageLabel.setText("<html>This order is already "+burger.getOrderStatus(order.getOrderStatus())+" !<br>You cannot update this order !</html>");
							JOptionPane.showMessageDialog(null,"This order is already "+burger.getOrderStatus(order.getOrderStatus())+" !\nYou cannot update this order !");
							updateOrderButton.setEnabled(false);
						}else{
							orderQtyField.setEditable(true);
							errorMessageLabel.setText("You can update this order !");
							Color darkGreen = new Color(0, 128, 0); // RGB: (0, 128, 0)
							errorMessageLabel.setForeground(darkGreen);
							orderQtyField.requestFocus();
							updateOrderButton.setEnabled(true);
						}
					}
				}
			}
		});
        orderIdField.setBackground(Color.WHITE);
        orderIdField.setBorder(BorderFactory.createLoweredBevelBorder());
        
        //turn off the update button until user enter a valid order id
        updateOrderButton.setEnabled(false);
        
        //Update button action listener
        updateOrderButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				String orderId = orderIdField.getText();
				int newQty = Integer.parseInt(orderQtyField.getText());
				if(orderId.length()!=5 || orderId.charAt(0)!='B'){
					JOptionPane.showMessageDialog(null,"Invalid order Id\nEnter again");
					orderIdField.requestFocus();
				}else{
					Burger order = checkForPreparing(orderId);
					if(order==null){
						JOptionPane.showMessageDialog(null,"No order in this id !");
					}else{
						customerIdField.setText(order.getCustomerId());
						customerIdField.setEditable(false);
						nameField.setText(order.getCustomerName());
						nameField.setEditable(false);
						orderStatusField.setText(burger.getOrderStatus(order.getOrderStatus()));
						orderStatusField.setEditable(false);
						totalValueLabel.setText(Double.toString(burger.getBurgerPrice()*order.getOrderQuantity()));
						//orderQtyField.setText(Integer.toString(order.getOrderQuantity()));
						if(order.getOrderStatus()!=1){
							orderQtyField.setEditable(false);
							errorMessageLabel.setText("<html>This order is already "+burger.getOrderStatus(order.getOrderStatus())+" !<br>You cannot update this order !</html>");
							JOptionPane.showMessageDialog(null,"This order is already "+burger.getOrderStatus(order.getOrderStatus())+" !\nYou cannot update this order !");
							updateOrderButton.setEnabled(false);
						}else{
							orderQtyField.requestFocus();
							updateOrderButton.setEnabled(true);
							//declaring a list from the text file
							List burgerList = new List(100,0.5);
							try{
								Scanner input = new Scanner(new File("Burger.txt"));
								while(input.hasNext()){
									String line = input.nextLine();
									String[] rowData = line.split(",");
									Burger burgerForRetrieve = new Burger(rowData[0],rowData[1],rowData[2],Integer.parseInt(rowData[3]),Integer.parseInt(rowData[4]));
									burgerList.add(burgerForRetrieve);
								}
								//order id has initialized previous
								String customerId = customerIdField.getText();
								String name = nameField.getText();
								int quantity = Integer.parseInt(orderQtyField.getText());
								int status = currentOrderStatus; 
								Burger updatedOrder = new Burger(orderId,customerId,name,quantity,status);
								boolean isUpdated = burgerList.set(updatedOrder);
								if(isUpdated){
									FileWriter fw = new FileWriter("Burger.txt",false);
									for (int i = 0; i < burgerList.size(); i++){
										Burger b1 = burgerList.get(i);
										fw.write(b1.toString()+"\n");
										JOptionPane.showMessageDialog(null,b1.toString());
									}
									fw.close();
									JOptionPane.showMessageDialog(null,"Updated SuccessFully !");
								}else{
									JOptionPane.showMessageDialog(null,"Update failed !");
								}
							}catch(IOException ex){
								
							}
							orderIdField.setText("");
							customerIdField.setText("");
							nameField.setText("");
							orderQtyField.setText("");
							orderStatusField.setText("");
							totalValueLabel.setText("");
						}
					}
				}
			}
		});
        
        customerIdField.setFont(fieldFont);
        customerIdField.setPreferredSize(fieldSize);
        customerIdField.setBackground(Color.WHITE);
        customerIdField.setBorder(BorderFactory.createLoweredBevelBorder());
        
        nameField.setFont(fieldFont);
        nameField.setPreferredSize(fieldSize);
        nameField.setBackground(Color.WHITE);
        nameField.setBorder(BorderFactory.createLoweredBevelBorder());
        
        orderQtyField.setFont(fieldFont);
        orderQtyField.setPreferredSize(fieldSize);
        orderQtyField.setBackground(Color.WHITE);
        orderQtyField.setBorder(BorderFactory.createLoweredBevelBorder());
        
        orderStatusField.setFont(fieldFont);
        orderStatusField.setPreferredSize(fieldSize);
        orderStatusField.setBackground(Color.WHITE);
        orderStatusField.setBorder(BorderFactory.createLoweredBevelBorder());
        
        // Buttons configuration
        updateOrderButton.setBackground(new Color(46, 204, 113));
        updateOrderButton.setForeground(Color.WHITE);
        updateOrderButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateOrderButton.setPreferredSize(new Dimension(150, 40));
        updateOrderButton.setBorderPainted(false);
        updateOrderButton.setFocusPainted(false);
        
        backButton.setBackground(new Color(231, 76, 60));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        
        // Make buttons rounded
        makeButtonRounded(updateOrderButton);
        makeButtonRounded(backButton);
        
        // Add hover effects to buttons
        addHoverEffect(updateOrderButton, new Color(46, 204, 113), new Color(39, 174, 96));
        addHoverEffect(backButton, new Color(231, 76, 60), new Color(192, 57, 43));
    }
    
    private Burger checkForPreparing(String orderId){
		Burger b1;
		try{
			Scanner beforeInput = new Scanner(new File("Burger.txt"));
			String lineBefore = null;
			while(beforeInput.hasNext()){
				lineBefore = beforeInput.nextLine();
				String[] rowData = lineBefore.split(",");
				if(orderId.equalsIgnoreCase(rowData[0])){
					b1 = new Burger(rowData[0],rowData[1],rowData[2],Integer.parseInt(rowData[3]),Integer.parseInt(rowData[4]));
					return b1;
				}
			}
			return null;
			
		}catch(IOException ex){
			return null;
		}
		
		
	}
    
    private void makeButtonRounded(JButton button) {
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                JButton b = (JButton) c;
                g2d.setColor(b.getBackground());
                g2d.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 15, 15);
                
                FontMetrics fm = g2d.getFontMetrics();
                Rectangle stringBounds = fm.getStringBounds(b.getText(), g2d).getBounds();
                int textX = (b.getWidth() - stringBounds.width) / 2;
                int textY = (b.getHeight() - stringBounds.height) / 2 + fm.getAscent();
                
                g2d.setColor(b.getForeground());
                g2d.drawString(b.getText(), textX, textY);
                g2d.dispose();
            }
        });
    }
    
    private void addHoverEffect(JButton button, Color normalColor, Color hoverColor) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(normalColor);
            }
        });
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Header panel
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        
        // Main panel with form
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(245, 245, 245));
        
        // Position components manually for exact placement
        orderIdLabel.setBounds(50, 50, 120, 30);
        orderIdField.setBounds(190, 50, 200, 35);
        errorMessageLabel.setBounds(410, 40, 200, 40);
        
        customerIdLabel.setBounds(50, 100, 120, 30);
        customerIdField.setBounds(190, 100, 200, 35);
        
        nameLabel.setBounds(50, 150, 120, 30);
        nameField.setBounds(190, 150, 200, 35);
        
        orderQtyLabel.setBounds(50, 200, 120, 30);
        orderQtyField.setBounds(190, 200, 200, 35);
        
        orderStatusLabel.setBounds(50, 250, 120, 30);
        orderStatusField.setBounds(190, 250, 200, 35);
        
        totalLabel.setBounds(50, 320, 120, 30);
        totalValueLabel.setBounds(190, 320, 100, 30);
        
        // Add components to main panel
        mainPanel.add(orderIdLabel);
        mainPanel.add(orderIdField);
        mainPanel.add(errorMessageLabel);
        mainPanel.add(customerIdLabel);
        mainPanel.add(customerIdField);
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(orderQtyLabel);
        mainPanel.add(orderQtyField);
        mainPanel.add(orderStatusLabel);
        mainPanel.add(orderStatusField);
        mainPanel.add(totalLabel);
        mainPanel.add(totalValueLabel);
        
        // Bottom panel with buttons and copyright
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(new Color(245, 245, 245));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(updateOrderButton);
        buttonPanel.add(backButton);
        
        JPanel copyrightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        copyrightPanel.setBackground(new Color(245, 245, 245));
        copyrightPanel.add(copyrightLabel);
        
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(copyrightPanel, BorderLayout.SOUTH);
        
        // Add panels to frame
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private void addActionListeners() {
        updateOrderButton.addActionListener(e -> {
            // Add your update order logic here
            System.out.println("Update Order button clicked");
        });
        
        backButton.addActionListener(e -> {
            UpdateOrderQuantityForm.this.setVisible(false);
            new UpdateOrderForm().setVisible(true);
        });
        
        // You can add action listeners for other components here
        totalValueLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add your total value click logic here
                System.out.println("Total value clicked");
            }
        });
    }
    
    private void configureFrame() {
        setTitle("Update Quantity");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    //public static void main(String[] args) {
    //    SwingUtilities.invokeLater(() -> {
    //        try {
    //           // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        
    //        new UpdateOrderQuantityForm().setVisible(true);
    //    });
    //}
}

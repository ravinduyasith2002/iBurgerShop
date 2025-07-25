import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;



public class SearchOrderDetailsForm extends JFrame {

    // Declare all components
    private JLabel enterOrderIdLabel;
    private JTextField orderIdTextField;

    private JLabel customerIdLabel;
    private JLabel customerIdValueLabel; // For "071 234 5678"
    private JLabel nameLabel;
    private JLabel nameValueLabel;       // For "Saman Kumara"
    private JLabel qtyLabel;
    private JLabel qtyValueLabel;        // For "6"
    private JLabel totalLabel;
    private JLabel totalValueLabel;      // For "3000.00"
    private JLabel orderStatusLabel;
    private JLabel orderStatusValueLabel; // For "PREPARING"

    private JButton backButton;
    
    Burger burger;

    public SearchOrderDetailsForm() {
		burger = new Burger();
        // Set frame properties
        setTitle("Search Order Details");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Initialize components
        enterOrderIdLabel = new JLabel("Enter OrderID :");
        enterOrderIdLabel.setFont(new Font("Arial", Font.PLAIN, 20)); // Slightly larger font

        orderIdTextField = new JTextField(5); // Pre-filled value
        orderIdTextField.setFont(new Font("",1,20));
        orderIdTextField.setPreferredSize(new Dimension(150, 30)); // Set preferred size for text field

        customerIdLabel = new JLabel("Customer ID :");
        customerIdLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        customerIdValueLabel = new JLabel("");
        customerIdValueLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Make value bold

        nameLabel = new JLabel("Name :");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameValueLabel = new JLabel("");
        nameValueLabel.setFont(new Font("Arial", Font.BOLD, 20));

        qtyLabel = new JLabel("QTY :");
        qtyLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        qtyValueLabel = new JLabel("");
        qtyValueLabel.setFont(new Font("Arial", Font.BOLD, 20));

        totalLabel = new JLabel("Total :");
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        totalValueLabel = new JLabel("");
        totalValueLabel.setFont(new Font("Arial", Font.BOLD, 20));

        orderStatusLabel = new JLabel("Order Status :");
        orderStatusLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        orderStatusValueLabel = new JLabel("");
        orderStatusValueLabel.setFont(new Font("Arial", Font.BOLD, 20));

        backButton = new JButton("Back");
        backButton.setBackground(new Color(220, 20, 60)); // Crimson red
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.BOLD, 20)); // Make button text a bit larger

        // Set up the layout
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(220, 20, 60)); // Crimson red
        JLabel headerLabel = new JLabel("Search Order Details");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content panel using GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20); // Padding around components
        gbc.anchor = GridBagConstraints.WEST; // Default to left alignment

        // Row 0: Enter OrderID
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(enterOrderIdLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(orderIdTextField, gbc);

        // Spacer to push subsequent elements down slightly
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(30, 20, 5, 20); // More top padding for this spacer
        mainPanel.add(new JLabel(" "), gbc); // Empty label as a spacer
        gbc.gridwidth = 1; // Reset gridwidth
        gbc.insets = new Insets(15, 20, 15, 20); // Reset padding

        // Row 2: Customer ID
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(customerIdLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(customerIdValueLabel, gbc);

        // Row 3: Name
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(nameValueLabel, gbc);

        // Row 4: QTY
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(qtyLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(qtyValueLabel, gbc);

        // Row 5: Total
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(totalLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(totalValueLabel, gbc);

        // Row 6: Order Status
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(orderStatusLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(orderStatusValueLabel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Footer Panel for the Back button at bottom right
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20)); // Padding at bottom and right
        footerPanel.add(backButton);
        add(footerPanel, BorderLayout.SOUTH);

        // Add ActionListener to the text field (e.g., for pressing Enter)
        orderIdTextField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				String orderId = orderIdTextField.getText();
				if(orderId.length()!=5 || orderId.charAt(0)!='B'){
					JOptionPane.showMessageDialog(null,"Invalid Order Id ! \nEnter again.");
				}else{
					try{
						Scanner input = new Scanner(new File("Burger.txt"));
						String line = null;
						while(input.hasNext()){
							line = input.nextLine();
							if(orderId.equalsIgnoreCase(line.substring(0,5))){
								break;
							}
						}
						String[] rowData = line.split(",");
						if(line!=null && orderId.equalsIgnoreCase(rowData[0])){
							customerIdValueLabel.setText(rowData[1]);
							nameValueLabel.setText(rowData[2]);
							qtyValueLabel.setText(rowData[3]);
							totalValueLabel.setText(Double.toString((Double.parseDouble(rowData[3]))*burger.getBurgerPrice()));
							orderStatusValueLabel.setText(burger.getOrderStatus(Integer.parseInt(rowData[4])));
						}else{
							JOptionPane.showMessageDialog(null,"Customer not found !");
						}
					}catch(IOException ex){
						ex.printStackTrace();
					}
				}
			}
		});

        // Add ActionListener to the Back button
        backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				SearchOrderDetailsForm.this.setVisible(false);
				new SearchForm().setVisible(true);
			}
		});
    }
}

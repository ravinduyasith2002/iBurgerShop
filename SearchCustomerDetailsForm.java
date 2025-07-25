import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableCellRenderer;



public class SearchCustomerDetailsForm extends JFrame {
    // Declare components
    public JTextField txtCustomerId;
    public JLabel lblCustomerName;
    public JLabel lblCustomerNameValue;
    public JTable orderTable;
    public DefaultTableModel dtm;
    public JButton btnBack;
    Burger burger;

    public SearchCustomerDetailsForm() {
		burger = new Burger();
        setTitle("Search Customer Details");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Font labelFont = new Font("SansSerif", Font.PLAIN, 20);
        Font headingFont = new Font("SansSerif", Font.BOLD, 24);

        // Heading - Search Customer
        JLabel lblHeading = new JLabel("Search Customer", SwingConstants.CENTER);
        lblHeading.setOpaque(true);
        lblHeading.setBackground(new Color(200, 50, 50));
        lblHeading.setForeground(Color.WHITE);
        lblHeading.setFont(headingFont);
        lblHeading.setBounds(0, 0, 900, 60);
        add(lblHeading);

        // Customer ID Label
        JLabel lblCustomerId = new JLabel("Enter Customer Id :");
        lblCustomerId.setFont(labelFont);
        lblCustomerId.setBounds(100, 100, 200, 30);
        add(lblCustomerId);

        // Customer ID TextField
        txtCustomerId = new JTextField();
        txtCustomerId.setFont(labelFont);
        txtCustomerId.setBounds(310, 100, 250, 30);
        txtCustomerId.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				String customerId = txtCustomerId.getText();
				if(customerId.length()!=10 || customerId.charAt(0)!='0'){
					JOptionPane.showMessageDialog(null,"Invalid Customer Id ! \nEnter again.");
				}else{
					Burger[] burgerCustomerDetails;
					try{
						Scanner input = new Scanner(new File("Burger.txt"));
						String line = null;
						int size = 0;
						while(input.hasNext()){
							line = input.nextLine();
							if(customerId.equalsIgnoreCase(line.substring(6,16))){
								size++;
							}
						}
						//checks if there are any orders from this customer
						if(size == 0){
							JOptionPane.showMessageDialog(null,"There is no customer in this ID !");
						}else{
							burgerCustomerDetails = new Burger[size];
							Scanner inputforFounds = new Scanner(new File("Burger.txt"));
							String foundLine = null;
							int index =0;
							while(inputforFounds.hasNext()){
								foundLine = inputforFounds.nextLine();
								if(customerId.equalsIgnoreCase(foundLine.substring(6,16))){
									//JOptionPane.showMessageDialog(null, burgerCustomerDetails.length);
									String[] rowDataForArray = foundLine.split(",");
									burgerCustomerDetails[index++] = new Burger(rowDataForArray[0],rowDataForArray[1],rowDataForArray[2],Integer.parseInt(rowDataForArray[3]),Integer.parseInt(rowDataForArray[4]));
								}
							}
							dtm.setRowCount(0);
							lblCustomerNameValue.setText(burgerCustomerDetails[0].getCustomerName());
							for (int i = 0; i < burgerCustomerDetails.length; i++){
								Object[] rowData = {burgerCustomerDetails[i].getOrderId(),
													  burgerCustomerDetails[i].getOrderQuantity(),
													  burgerCustomerDetails[i].getOrderQuantity()*burger.getBurgerPrice()
													  };
								dtm.addRow(rowData);
							}
							
							
							
						}
					}catch(IOException ex){
						ex.printStackTrace();
					}
				}
			}
		});
        add(txtCustomerId);

        // Name Label (static)
        JLabel lblCustomerName = new JLabel("Name :");
        lblCustomerName.setFont(labelFont);
        lblCustomerName.setBounds(100, 150, 100, 30);
        add(lblCustomerName);

        // Name Value Label (dynamic)
        lblCustomerNameValue = new JLabel("");  // Will be updated dynamically
        lblCustomerNameValue.setFont(labelFont);
        lblCustomerNameValue.setBounds(200, 150, 300, 30);
        add(lblCustomerNameValue);

        // Subheading - Order Details
        JLabel lblOrderDetails = new JLabel("Order Details", SwingConstants.CENTER);
        lblOrderDetails.setOpaque(true);
        lblOrderDetails.setBackground(new Color(200, 50, 50));
        lblOrderDetails.setForeground(Color.WHITE);
        lblOrderDetails.setFont(headingFont);
        lblOrderDetails.setBounds(0, 200, 900, 50);
        add(lblOrderDetails);

        // Table setup
        String[] columnNames = {"Order Id", "Order QTY", "Total"};
        dtm = new DefaultTableModel(columnNames, 0);
        orderTable = new JTable(dtm);
        orderTable.setFont(labelFont);
        orderTable.setRowHeight(30);
        orderTable.getTableHeader().setFont(labelFont);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < orderTable.getColumnCount(); i++) {
			orderTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

        JScrollPane scrollPane = new JScrollPane(orderTable);
        scrollPane.setBounds(150, 260, 600, 150);
        add(scrollPane);

        // Back button with rounded corners and color change on click
        btnBack = new JButton("Back") {
            private Color normalColor = new Color(200, 50, 50);
            private Color clickedColor = new Color(150, 30, 30);
            private boolean isPressed = false;

            {
                setForeground(Color.WHITE);
                setFont(labelFont);
                setContentAreaFilled(false);
                setFocusPainted(false);
                setBorderPainted(false);
                setOpaque(false);

                // Mouse listener to change color on press
                addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        isPressed = true;
                        repaint();
                    }

                    public void mouseReleased(MouseEvent e) {
                        isPressed = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(isPressed ? clickedColor : normalColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnBack.setBounds(700, 440, 100, 40);
        btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				SearchCustomerDetailsForm.this.setVisible(false);
				new SearchForm().setVisible(true);
			}
		});
        add(btnBack);
    }

    // For testing the UI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        //    new SearchCustomerDetailsForm().setVisible(true);
        });
    }
}

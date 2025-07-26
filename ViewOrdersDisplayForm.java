import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer; // Import for cell renderer
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader; // For potential file reading
import java.io.FileReader;    // For potential file reading
import java.io.IOException;   // For potential file reading
import java.util.ArrayList;   // For potential file reading
import java.util.List;        // For potential file reading
import java.util.*;
import java.io.*;


public class ViewOrdersDisplayForm extends JFrame {
	Burger burger;

    // Declare all components
    private JLabel titlePanelLabel; // For the "Delivered Orders" title in the red bar
    public JTable deliveredOrdersTable; // Changed to public as in your example
    public DefaultTableModel dtm;      // Declared as public dtm
    private JScrollPane tableScrollPane; // To make the table scrollable

    private JButton selectAndUpdateButton;
    private JButton backButton;
    private JLabel watermarkLabel; // For "@Ravindu Yasith"

    public ViewOrdersDisplayForm(int orderType) {
		burger = new Burger();
        // Set up the main frame
        String title = (orderType==0)?"Canceled":(orderType==1)?"Preparing":(orderType==2)?"Delivered":"Invalid !";
        setTitle(title+" Order Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 550);
        setMinimumSize(new Dimension(900, 550)); // Ensure fixed size
        setLocationRelativeTo(null); // Center the frame
        setResizable(false); // Disable resizing
        setLayout(null); // Use absolute positioning for components

        // --- Top Red Panel for Title ---
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, 900, 80); // Full width, 80px height
        topPanel.setBackground(new Color(178, 34, 34)); // Dark Red color (FireBrick)
        topPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering the label

        titlePanelLabel = new JLabel(title+" Orders");
        titlePanelLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titlePanelLabel.setForeground(Color.WHITE);
        topPanel.add(titlePanelLabel); // Centered by GridBagLayout default

        // --- Main Content Panel (White background behind table) ---
        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(0, 80, 900, 550 - 80); // Remaining height
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(null); // Use null layout for precise positioning of table and buttons

        // --- Table setup ---
        String[] columnNames = {"Order Id", "Customer Id", "Name", "Order QTY", "Total"};
        dtm = new DefaultTableModel(columnNames, 0) { // Initialized dtm with columnNames and 0 rows
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        deliveredOrdersTable = new JTable(dtm); // Use dtm for the table
        deliveredOrdersTable.setFont(new Font("Arial", Font.PLAIN, 14)); // Changed font size as per image look
        deliveredOrdersTable.setRowHeight(25); // Adjust row height for better appearance
        deliveredOrdersTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Changed font size as per image look
        deliveredOrdersTable.getTableHeader().setBackground(new Color(230, 230, 230)); // Light gray header
        deliveredOrdersTable.setGridColor(Color.LIGHT_GRAY); // Grid lines
        deliveredOrdersTable.setFillsViewportHeight(true); // Ensures table fills the height of the scroll pane


		//Add data to the table
		Burger[] orderDetails;
		try{
			//Check if orders are exist
			Scanner inputBefore = new Scanner(new File("Burger.txt"));
			int size = 0;
			String currentLine = null;
			// check size
			while(inputBefore.hasNext()){
				currentLine = inputBefore.nextLine();
				String[] currentRow = currentLine.split(",");
				if(Integer.parseInt(currentRow[4])==orderType){
					size++;
				}
			}
			if(size>0){
				orderDetails = new Burger[size];
				Scanner input = new Scanner(new File("Burger.txt"));
				String line = null;
				int index = 0;
				while(input.hasNext()){
					line = input.nextLine();
					String[] rowDataForTable = line.split(",");
					if(Integer.parseInt(rowDataForTable[4])==orderType){
						orderDetails[index++] = new Burger(rowDataForTable[0],rowDataForTable[1],rowDataForTable[2],Integer.parseInt(rowDataForTable[3]),Integer.parseInt(rowDataForTable[4]));
					}
				}
				//Load the data to  the table
				dtm.setRowCount(0);
				for (int i = 0; i < orderDetails.length; i++){
					Object[] rowData = {orderDetails[i].getOrderId(),orderDetails[i].getCustomerId(),orderDetails[i].getCustomerName(),orderDetails[i].getOrderQuantity(),burger.getBurgerPrice()*orderDetails[i].getOrderQuantity()};
					dtm.addRow(rowData);
				}
				
			}else{
				JOptionPane.showMessageDialog(null,"No "+title+" Orders !");
			}
			
		}catch(IOException ex){
			
		}
		
        // Center align table cell contents as in your example
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < deliveredOrdersTable.getColumnCount(); i++) {
            deliveredOrdersTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Add table to a scroll pane
        tableScrollPane = new JScrollPane(deliveredOrdersTable);
        tableScrollPane.setBounds(50, 20, 800, 240); // Position within contentPanel
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder()); // No border around scroll pane
        contentPanel.add(tableScrollPane);

        // --- Buttons ---
        final Color GREEN_BUTTON_COLOR = new Color(50, 205, 50); // Lime Green
        final Color GREEN_BUTTON_HOVER = new Color(60, 220, 60); // Slightly brighter green
        final Color RED_BUTTON_COLOR = new Color(178, 34, 34); // FireBrick
        final Color RED_BUTTON_HOVER = new Color(220, 50, 50); // Slightly brighter red

        // Select And Update Order Details Button - No action listener added
        selectAndUpdateButton = new JButton("Select And Update Order Details") {
            private final int ARC_SIZE = 30; // Rounded corners

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isRollover()) {
                    g2.setColor(GREEN_BUTTON_HOVER);
                } else {
                    g2.setColor(GREEN_BUTTON_COLOR);
                }
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE));
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            public boolean contains(int x, int y) {
                Shape shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE);
                return shape.contains(x, y);
            }
        };
        selectAndUpdateButton.setBounds(270, 380, 250, 50); // Position and size
        selectAndUpdateButton.setFont(new Font("Arial", Font.BOLD, 14));
        selectAndUpdateButton.setForeground(Color.WHITE);
        selectAndUpdateButton.setBorder(BorderFactory.createEmptyBorder());
        selectAndUpdateButton.setFocusPainted(false);
        selectAndUpdateButton.setContentAreaFilled(false);
        selectAndUpdateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(selectAndUpdateButton);

        // Back Button
        backButton = new JButton("Back") {
            private final int ARC_SIZE = 30; // Rounded corners

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isRollover()) {
                    g2.setColor(RED_BUTTON_HOVER);
                } else {
                    g2.setColor(RED_BUTTON_COLOR);
                }
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE));
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            public boolean contains(int x, int y) {
                Shape shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE);
                return shape.contains(x, y);
            }
        };
        backButton.setBounds(580, 380, 120, 50); // Position and size
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backButton);

        // --- Watermark Label ---
        watermarkLabel = new JLabel("@Ravindu Yasith");
        watermarkLabel.setBounds(20, contentPanel.getHeight() - 40, 150, 20); // Position at bottom-left
        watermarkLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        watermarkLabel.setForeground(Color.GRAY);
        contentPanel.add(watermarkLabel);


        // Add panels to frame
        add(topPanel);
        add(contentPanel);

        addBackButtonListener(e -> {
            new ViewOrderForm().setVisible(true);
            ViewOrdersDisplayForm.this.dispose();
        });
    }

    // Public methods to add ActionListeners separately
    public void addSelectAndUpdateButtonListener(java.awt.event.ActionListener listener) {
        selectAndUpdateButton.addActionListener(listener);
    }

    public void addBackButtonListener(java.awt.event.ActionListener listener) {
        backButton.addActionListener(listener);
    }

    
    public void addDataToTable(Object[][] newData) {
        // Clear existing rows before adding new ones
        dtm.setRowCount(0); // Use dtm directly
        for (Object[] row : newData) {
            dtm.addRow(row); // Use dtm directly
        }
    }

    
}

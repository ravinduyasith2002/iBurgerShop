import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class SearchBestCustomerForm extends JFrame { // Class name as requested

    // Declare all components
    private JLabel titlePanelLabel; // For the "Search Best Customers" title in the red bar
    public JTable bestCustomersTable; // Changed to public for external access
    public DefaultTableModel dtm;      // dtm as requested
    private JScrollPane tableScrollPane; // To make the table scrollable

    private JButton backButton;
    private JLabel watermarkLabel; // For "@Ravindu Yasith"
    Burger burger;

    public SearchBestCustomerForm() {
		burger = new Burger();
        // Set up the main frame
        setTitle("Search Best Customers"); // Title as per image
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 550);
        setMinimumSize(new Dimension(900, 550));
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // --- Top Red Panel for Title ---
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, 900, 80);
        topPanel.setBackground(new Color(178, 34, 34)); // Exact dark red from previous code
        topPanel.setLayout(new GridBagLayout());

        titlePanelLabel = new JLabel("Search Best Customers"); // Title text as per image
        titlePanelLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titlePanelLabel.setForeground(Color.WHITE);
        topPanel.add(titlePanelLabel);

        // --- Main Content Panel (White background behind table) ---
        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(0, 80, 900, 550 - 80); // Remaining height
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(null);

        // --- Table for Best Customers ---
        String[] columnNames = {"Customer ID", "Name", "Total"}; // Column titles as per image
        // Initialize dtm with only column headings, no records
        dtm = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        
        //Operations for find Best Customer
        try{
			Scanner inputBefore = new Scanner(new File("Burger.txt"));
			Burger[] allOrders;
			String currentLine = null;
			int size = 0;
			while(inputBefore.hasNext()){
				currentLine = inputBefore.nextLine();
				if(currentLine != null){
					size++;
				}
			}
			if(size<1){
				JOptionPane.showMessageDialog(null,"No Orders placed yet !");
			}else{
				allOrders = new Burger[size];
				Scanner input = new Scanner(new File("Burger.txt"));
				String line = null;
				int index =0;
				while(input.hasNext()){
					line = input.nextLine();
					String[] OrderRow = line.split(",");
					
					allOrders[index++] = new Burger(OrderRow[0],OrderRow[1],OrderRow[2],Integer.parseInt(OrderRow[3]),Integer.parseInt(OrderRow[4]));
					
				}
				Burger[] duplicateRemoved = removeDuplicates(allOrders);
				if(duplicateRemoved==null){
					JOptionPane.showMessageDialog(null,"No Orders Yet !"); //--<>
				}else{
					dtm.setRowCount(0);
					for (int i = 0; i < duplicateRemoved.length; i++){
						Object[] rowData = {duplicateRemoved[i].getCustomerId(),duplicateRemoved[i].getCustomerName(),duplicateRemoved[i].getOrderQuantity()*burger.getBurgerPrice()};
						dtm.addRow(rowData);
					}
					
				}	
			}
			
		}catch(IOException ex){
			
		}
        
        bestCustomersTable = new JTable(dtm); // Use dtm for the table
        bestCustomersTable.setFont(new Font("Arial", Font.PLAIN, 14));
        bestCustomersTable.setRowHeight(25);
        bestCustomersTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        bestCustomersTable.getTableHeader().setBackground(new Color(230, 230, 230)); // Light gray header
        bestCustomersTable.setGridColor(Color.LIGHT_GRAY);
        bestCustomersTable.setFillsViewportHeight(true);

        // Center align table cell contents
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < bestCustomersTable.getColumnCount(); i++) {
            bestCustomersTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tableScrollPane = new JScrollPane(bestCustomersTable);
        // Position and size the table to match the image's layout
        tableScrollPane.setBounds(50, 20, 800, 300); // Adjusted height to accommodate button below
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentPanel.add(tableScrollPane);

        // --- Back Button ---
        final Color RED_BUTTON_COLOR = new Color(178, 34, 34); // Exact dark red
        final Color RED_BUTTON_HOVER = new Color(220, 50, 50); // Slightly brighter red for hover

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
        // Position the back button relative to the bottom right as in the image
        backButton.setBounds(730, 380, 120, 50); // Adjusted position
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backButton);

        // --- Watermark Label ---
        watermarkLabel = new JLabel("@Ravindu Yasith"); // Watermark changed as requested
        watermarkLabel.setBounds(20, contentPanel.getHeight() - 40, 150, 20); // Position at bottom-left
        watermarkLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        watermarkLabel.setForeground(Color.GRAY);
        contentPanel.add(watermarkLabel);

        // Add panels to frame
        add(topPanel);
        add(contentPanel);

        // --- Add Action Listener for Back button ---
        addBackButtonListener(e -> {
            new SearchForm().setVisible(true); // Uncomment when ready
            SearchBestCustomerForm.this.dispose(); // Uncomment when ready
        });
    }

    // Public method to add ActionListener to the back button
    public void addBackButtonListener(java.awt.event.ActionListener listener) {
        backButton.addActionListener(listener);
    }
    
    private boolean search(String[] arr, String id){
		for (int i = 0; i < arr.length; i++){
			
			if(id.equals(arr[i])){
				return true;
			}
		}
		return false;
	}
    private Burger[] removeDuplicates(Burger[] allOrders){	
		
		String[] nonDuplicatedCustomerIdArray = new String[0];
		String[] nonDuplicatedCustomerNameArray = new String[0];
		for (int i = 0; i < allOrders.length; i++){
			String id = allOrders[i].getCustomerId();
			String name = allOrders[i].getCustomerName();
			if(!search(nonDuplicatedCustomerIdArray,id)){
				
				String[] tempIdArray = new String[nonDuplicatedCustomerIdArray.length+1];
				String[] tempNameArray = new String[nonDuplicatedCustomerIdArray.length+1];
				for (int j = 0; j < nonDuplicatedCustomerIdArray.length; j++){
					tempIdArray[j] = nonDuplicatedCustomerIdArray[j];
					tempNameArray[j] = nonDuplicatedCustomerNameArray[j];
				}
				//Assign new id
				nonDuplicatedCustomerIdArray = tempIdArray;
				nonDuplicatedCustomerIdArray[nonDuplicatedCustomerIdArray.length-1] = id;
				
				//Assign new name
				nonDuplicatedCustomerNameArray = tempNameArray;
				nonDuplicatedCustomerNameArray[nonDuplicatedCustomerNameArray.length-1] = name;	
			}
		}
		int[] nonDuplicatedOrderQtyArray = new int[nonDuplicatedCustomerIdArray.length];
		for (int i = 0; i < nonDuplicatedCustomerIdArray.length; i++){
			String id = nonDuplicatedCustomerIdArray[i];
			int totalQty = 0;
			for (int j = 0; j < allOrders.length; j++){
				if(id.equals(allOrders[j].getCustomerId())){
					totalQty+=allOrders[j].getOrderQuantity();
				}
			}
			nonDuplicatedOrderQtyArray[i] = totalQty;
		}
		
		//Sorting
		for (int i = 1; i < nonDuplicatedOrderQtyArray.length; i++){
			for (int j = 0; j < i; j++){
				if(nonDuplicatedOrderQtyArray[i]>nonDuplicatedOrderQtyArray[j]){
					//Quantity sort
					int tempQty = nonDuplicatedOrderQtyArray[i];
					nonDuplicatedOrderQtyArray[i] = nonDuplicatedOrderQtyArray[j];
					nonDuplicatedOrderQtyArray[j] = tempQty;
					
					//Name sort according to quantity
					String tempName = nonDuplicatedCustomerNameArray[i];
					nonDuplicatedCustomerNameArray[i] = nonDuplicatedCustomerNameArray[j];
					nonDuplicatedCustomerNameArray[j] = tempName;
					
					//Customer ID sort according to quantity
					String tempId = nonDuplicatedCustomerIdArray[i];
					nonDuplicatedCustomerIdArray[i] = nonDuplicatedCustomerIdArray[j];
					nonDuplicatedCustomerIdArray[j] = tempId;
				}
			}
		}
		Burger[] sortedCustomers = new Burger[nonDuplicatedCustomerIdArray.length];
		for (int i = 0; i < nonDuplicatedCustomerIdArray.length; i++){
			sortedCustomers[i] = new Burger(nonDuplicatedCustomerIdArray[i],nonDuplicatedCustomerNameArray[i],nonDuplicatedOrderQtyArray[i]);
		}
		return sortedCustomers;
		
	}

    //public static void main(String[] args) {
    //    SwingUtilities.invokeLater(() -> {
    //        try {
    //            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
	//
    //        SearchBestCustomerForm form = new SearchBestCustomerForm();
    //        form.setVisible(true);
	//
    //        // Example of how to add data from a text file (conceptual)
    //        // form.loadDataFromTextFile("path/to/your/best_customers.txt");
    //    });
    //}

    /**
     * Method to add data to the table from an external source (e.g., a text file).
     * This method directly adds rows to the dtm (DefaultTableModel).
     * You would call this method after initializing the form, passing your data.
     *
     * @param newData A 2D array of Objects representing the rows of data.
     */
    public void addDataToTable(Object[][] newData) {
        dtm.setRowCount(0); // Clear existing rows before adding new ones
        for (Object[] row : newData) {
            dtm.addRow(row);
        }
    }

    /**
     * Example of how to load data from a text file (conceptual).
     * This method would parse your text file and call addDataToTable.
     * You'll need to implement the actual file reading logic.
     */
    public void loadDataFromTextFile(String filePath) {
        System.out.println("Loading data from: " + filePath);
        List<Object[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Assuming comma-separated values
                if (parts.length == dtm.getColumnCount()) {
                    rows.add(parts);
                } else {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
        addDataToTable(rows.toArray(new Object[0][0]));
        JOptionPane.showMessageDialog(this, "Data loaded from file (or attempted).");
    }
}

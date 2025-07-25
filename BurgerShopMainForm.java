import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BurgerShopMainForm extends JFrame {
    private JButton btnPlaceOrder;
    private JButton btnSearch;
    private JButton btnViewOrders;
    private JButton btnUpdateOrderDetails;
    private JButton btnExit;

    public BurgerShopMainForm() {
        setTitle("Burger Shop Management");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Left Panel - Image and Title
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Welcome to Burgers", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(new Color(204, 153, 0));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        leftPanel.add(lblTitle, BorderLayout.NORTH);

        // Load and scale the image
		ImageIcon originalIcon = new ImageIcon("burger_shop.png");
		Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		JLabel lblImage = new JLabel();
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(scaledIcon);
		leftPanel.add(lblImage, BorderLayout.CENTER);


        JLabel lblCredit = new JLabel("@Ravindu Yasith.", SwingConstants.CENTER);
        lblCredit.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblCredit.setForeground(Color.GRAY);
        leftPanel.add(lblCredit, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);
        leftPanel.setPreferredSize(new Dimension(450, getHeight()));

        // Right Panel - Buttons
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(230, 230, 230));
        rightPanel.setLayout(null);

        // Button Panel
        btnPlaceOrder = createRoundedButton("Place Order", 60);
        rightPanel.add(btnPlaceOrder);

        btnSearch = createRoundedButton("Search", 130);
        rightPanel.add(btnSearch);

        btnViewOrders = createRoundedButton("View Orders", 200);
        btnViewOrders.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new ViewOrderForm().setVisible(true);
				BurgerShopMainForm.this.setVisible(false);
			}
		});
        rightPanel.add(btnViewOrders);

        btnUpdateOrderDetails = createRoundedButton("Update Order Details", 270);
        rightPanel.add(btnUpdateOrderDetails);

        btnExit = createRoundedButton("Exit", 390);
        btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				BurgerShopMainForm.this.dispose();
				System.out.println("User left the program !");
			}
		});
        rightPanel.add(btnExit);

        add(rightPanel, BorderLayout.CENTER);
        
        btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new AddNewOrderForm().setVisible(true);
				BurgerShopMainForm.this.setVisible(false);
			}
		});
		
		btnSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new SearchForm().setVisible(true);
				BurgerShopMainForm.this.setVisible(false);
			}
		});

        
    }
	
    private JButton createRoundedButton(String text, int y) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isRollover()) {
                    g.setColor(new Color(255, 80, 80));
                } else {
                    g.setColor(new Color(204, 0, 0));
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }
        };

        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBounds(150, y, 200, 45);

        return button;
    }

    public static void main(String args[]) {
        new BurgerShopMainForm().setVisible(true);
    }
}

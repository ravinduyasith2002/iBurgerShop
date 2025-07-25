import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class ViewOrderForm extends JFrame {

    // Declare all components
    private JLabel viewOrderLabel;
    private JLabel burgerImageLabel;
    private JLabel signatureLabel; // For "@Ravindu Yasith"

    private JButton canceledOrdersButton;
    private JButton preparingOrdersButton;
    private JButton deliveredOrdersButton;
    private JButton backToHomePageButton;
    
    private int canceledOrdersOption = 0;
    private int PreparingOrdersOption = 1;
    private int deliveredOrdersOption = 2;

    public ViewOrderForm() {
        // Set up the main frame
        setTitle("View Order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 550);
        setMinimumSize(new Dimension(900, 550)); // Ensure fixed size
        setLocationRelativeTo(null); // Center the frame
        setResizable(false); // Disable resizing
        setLayout(null); // Use absolute positioning for panels

        // Create left panel (white) and right panel (gray)
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 450, 550); // Adjusted width for visual balance
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(null); // Use absolute positioning for components within left panel

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(450, 0, 450, 550); // Adjusted width for visual balance
        rightPanel.setBackground(new Color(220, 220, 220)); // Light gray
        rightPanel.setLayout(null); // Use absolute positioning for components within right panel

        // --- Left Panel Components ---

        // Initialize title label
        viewOrderLabel = new JLabel("View Order");
        viewOrderLabel.setBounds(80, 50, 250, 40); // Adjusted position
        viewOrderLabel.setFont(new Font("Arial", Font.BOLD, 36));
        viewOrderLabel.setForeground(new Color(255, 179, 0)); // Orange color
        leftPanel.add(viewOrderLabel);

        // Initialize image label
        burgerImageLabel = new JLabel();
        burgerImageLabel.setBounds(65, 120, 320, 290); // Adjusted position and size
        // Try to load the burger shop image
        try {
            // Using getClass().getResource() is better for packaged applications
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/burger_shop.png"));
            if (originalIcon.getImage() != null) {
                // Scale the image to fit the label
                Image scaledImage = originalIcon.getImage().getScaledInstance(320, 290, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                burgerImageLabel.setIcon(scaledIcon);
            } else {
                burgerImageLabel.setText("Image not found"); // Fallback if image fails to load
                burgerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                burgerImageLabel.setVerticalAlignment(SwingConstants.CENTER);
                burgerImageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                burgerImageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                burgerImageLabel.setOpaque(true);
                burgerImageLabel.setBackground(Color.WHITE);
            }
        } catch (Exception e) {
            // Catching any exception during image loading
            System.err.println("Error loading image: " + e.getMessage());
            burgerImageLabel.setText("Image not found");
            burgerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            burgerImageLabel.setVerticalAlignment(SwingConstants.CENTER);
            burgerImageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            burgerImageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            burgerImageLabel.setOpaque(true);
            burgerImageLabel.setBackground(Color.WHITE);
        }
        leftPanel.add(burgerImageLabel);

        // Initialize copyright label
        signatureLabel = new JLabel("@Ravindu Yasith");
        signatureLabel.setBounds(150, 450, 150, 20); // Adjusted position
        signatureLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signatureLabel.setForeground(Color.GRAY);
        leftPanel.add(signatureLabel);

        // --- Right Panel Components (Buttons) ---

        // Define dark red colors
        final Color DARK_RED = new Color(139, 0, 0); // Standard Dark Red
        final Color DARK_RED_HOVER = new Color(178, 34, 34); // FireBrick, slightly lighter for hover

        // Initialize Canceled Orders Button
        canceledOrdersButton = new JButton("Canceled Orders") {
            private final int ARC_SIZE = 40; // Adjust for desired roundness

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Set color for the button background
                if (getModel().isRollover()) {
                    g2.setColor(DARK_RED_HOVER);
                } else {
                    g2.setColor(DARK_RED);
                }

                // Fill the rounded rectangle
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE));

                // Paint the text and icon
                super.paintComponent(g2); // Let the JButton paint its text and icon

                g2.dispose();
            }

            @Override
            public boolean contains(int x, int y) {
                // Ensure mouse events are detected within the rounded shape
                Shape shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE);
                return shape.contains(x, y);
            }
        };
        canceledOrdersButton.setBounds(75, 100, 300, 70); // Position and size
        canceledOrdersButton.setFont(new Font("Arial", Font.BOLD, 24));
        canceledOrdersButton.setForeground(Color.WHITE);
        canceledOrdersButton.setBorder(BorderFactory.createEmptyBorder()); // No default border
        canceledOrdersButton.setFocusPainted(false); // No focus border
        canceledOrdersButton.setContentAreaFilled(false); // Important: don't fill content area, we paint it
        canceledOrdersButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        rightPanel.add(canceledOrdersButton);


        // Initialize Preparing Orders Button
        preparingOrdersButton = new JButton("Preparing Orders") {
            private final int ARC_SIZE = 40; // Adjust for desired roundness

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Set color for the button background
                if (getModel().isRollover()) {
                    g2.setColor(DARK_RED_HOVER);
                } else {
                    g2.setColor(DARK_RED);
                }

                // Fill the rounded rectangle
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE));

                // Paint the text and icon
                super.paintComponent(g2); // Let the JButton paint its text and icon

                g2.dispose();
            }

            @Override
            public boolean contains(int x, int y) {
                Shape shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE);
                return shape.contains(x, y);
            }
        };
        preparingOrdersButton.setBounds(75, 200, 300, 70); // Position and size
        preparingOrdersButton.setFont(new Font("Arial", Font.BOLD, 24));
        preparingOrdersButton.setForeground(Color.WHITE);
        preparingOrdersButton.setBorder(BorderFactory.createEmptyBorder());
        preparingOrdersButton.setFocusPainted(false);
        preparingOrdersButton.setContentAreaFilled(false);
        preparingOrdersButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightPanel.add(preparingOrdersButton);

        // Initialize Delivered Orders Button
        deliveredOrdersButton = new JButton("Delivered Orders") {
            private final int ARC_SIZE = 40; // Adjust for desired roundness

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Set color for the button background
                if (getModel().isRollover()) {
                    g2.setColor(DARK_RED_HOVER);
                } else {
                    g2.setColor(DARK_RED);
                }

                // Fill the rounded rectangle
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE));

                // Paint the text and icon
                super.paintComponent(g2); // Let the JButton paint its text and icon

                g2.dispose();
            }

            @Override
            public boolean contains(int x, int y) {
                Shape shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE);
                return shape.contains(x, y);
            }
        };
        deliveredOrdersButton.setBounds(75, 300, 300, 70); // Position and size
        deliveredOrdersButton.setFont(new Font("Arial", Font.BOLD, 24));
        deliveredOrdersButton.setForeground(Color.WHITE);
        deliveredOrdersButton.setBorder(BorderFactory.createEmptyBorder());
        deliveredOrdersButton.setFocusPainted(false);
        deliveredOrdersButton.setContentAreaFilled(false);
        deliveredOrdersButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightPanel.add(deliveredOrdersButton);

        // Initialize Back to Home Page Button
        backToHomePageButton = new JButton("Back to Home Page") {
            private final int ARC_SIZE = 40; // Adjust for desired roundness

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Set color for the button background
                if (getModel().isRollover()) {
                    g2.setColor(DARK_RED_HOVER);
                } else {
                    g2.setColor(DARK_RED);
                }

                // Fill the rounded rectangle
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE));

                // Paint the text and icon
                super.paintComponent(g2); // Let the JButton paint its text and icon

                g2.dispose();
            }

            @Override
            public boolean contains(int x, int y) {
                Shape shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE);
                return shape.contains(x, y);
            }
        };
        backToHomePageButton.setBounds(75, 400, 300, 70); // Position and size
        backToHomePageButton.setFont(new Font("Arial", Font.BOLD, 24));
        backToHomePageButton.setForeground(Color.WHITE);
        backToHomePageButton.setBorder(BorderFactory.createEmptyBorder());
        backToHomePageButton.setFocusPainted(false);
        backToHomePageButton.setContentAreaFilled(false);
        backToHomePageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightPanel.add(backToHomePageButton);


        // Add panels to frame
        add(leftPanel);
        add(rightPanel);

        addCanceledOrdersButtonListener(e -> {
            new ViewDeliveredOrderDetailsForm(canceledOrdersOption).setVisible(true);
            ViewOrderForm.this.dispose();
        });

        addPreparingOrdersButtonListener(e -> {
            new ViewDeliveredOrderDetailsForm(PreparingOrdersOption).setVisible(true);
            ViewOrderForm.this.dispose();
        });
		
        addDeliveredOrdersButtonListener(e -> {
            new ViewDeliveredOrderDetailsForm(deliveredOrdersOption).setVisible(true);
            ViewOrderForm.this.dispose();
        });

        addBackToHomePageButtonListener(e -> {
            new BurgerShopMainForm().setVisible(true); // Assuming BurgerShopMainForm is your main entry
            ViewOrderForm.this.dispose();
        });
    }

    // Public methods to add ActionListeners separately (as in your example)
    public void addCanceledOrdersButtonListener(java.awt.event.ActionListener listener) {
        canceledOrdersButton.addActionListener(listener);
    }

    public void addPreparingOrdersButtonListener(java.awt.event.ActionListener listener) {
        preparingOrdersButton.addActionListener(listener);
    }

    public void addDeliveredOrdersButtonListener(java.awt.event.ActionListener listener) {
        deliveredOrdersButton.addActionListener(listener);
    }

    public void addBackToHomePageButtonListener(java.awt.event.ActionListener listener) {
        backToHomePageButton.addActionListener(listener);
    }
}

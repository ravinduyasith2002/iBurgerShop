import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D; // Needed for rounded corners

public class SearchForm extends JFrame {

    // Declare all components
    private JLabel titleLabel;
    private JLabel imageLabel;
    private JLabel copyrightLabel;

    private JButton searchOrderButton;
    private JButton searchCustomerButton;
    private JButton searchBestCustomerButton; // New button declaration
    private JButton backToHomePageButton;

    public SearchForm() {
        // Set up the main frame
        setTitle("Search Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 550);
        setMinimumSize(new Dimension(900, 550));
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // Define the dark red colors for buttons
        final Color DARK_RED_BUTTON_COLOR = new Color(178, 34, 34); // FireBrick
        final Color DARK_RED_BUTTON_HOVER = new Color(205, 50, 50); // Slightly lighter red for hover

        // Create left panel (white) and right panel (gray)
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 380, 550);
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(null);

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(380, 0, 520, 550);
        rightPanel.setBackground(new Color(220, 220, 220)); // Light gray
        rightPanel.setLayout(null);

        // Initialize title label
        titleLabel = new JLabel("Search Order"); // Text as per image
        titleLabel.setBounds(85, 50, 250, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(255, 165, 0)); // Orange color

        // Initialize image label
        imageLabel = new JLabel();
        imageLabel.setBounds(60, 120, 320, 290);

        // Try to load the burger shop image from resource or local file
        // Ensure "burger_shop.png" is in your project's root or a specified resource folder
        try {
            // Attempt to load from resources (preferred for deployment)
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/burger_shop.png"));
            if (originalIcon.getIconWidth() == -1) { // Fallback if not found in resources
                originalIcon = new ImageIcon("burger_shop.png");
            }
            Image scaledImage = originalIcon.getImage().getScaledInstance(320, 290, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            imageLabel.setIcon(scaledIcon);
        } catch (Exception e) {
            // If image not found, create a placeholder
            imageLabel.setText("Image not found: burger_shop.png");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            imageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            imageLabel.setOpaque(true);
            imageLabel.setBackground(Color.LIGHT_GRAY);
            System.err.println("Error loading image: " + e.getMessage());
        }

        // Initialize copyright label
        copyrightLabel = new JLabel("@Ravindu Yasith");
        copyrightLabel.setBounds(145, 440, 150, 20);
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        copyrightLabel.setForeground(Color.GRAY);

        // Helper method to create styled buttons
        // This function sets up the common properties for all buttons
        // including rounded corners and hover effect.
        // It should be a method returning a JButton, then assigned to a variable.
        // The original code had the button creation directly where a statement was expected.
        // This is the source of the errors.
        
        // --- Button Initialization ---
        // Corrected way to call the helper method and assign the returned JButton
        searchOrderButton = createStyledButton("Search Order", 100, DARK_RED_BUTTON_COLOR, DARK_RED_BUTTON_HOVER); // Adjusted Y position for new button
        searchCustomerButton = createStyledButton("Search Customer", 190, DARK_RED_BUTTON_COLOR, DARK_RED_BUTTON_HOVER); // Adjusted Y position
        searchBestCustomerButton = createStyledButton("Search Best Customer", 280, DARK_RED_BUTTON_COLOR, DARK_RED_BUTTON_HOVER); // New button position
        backToHomePageButton = createStyledButton("Back to Home Page", 390, DARK_RED_BUTTON_COLOR, DARK_RED_BUTTON_HOVER); // Adjusted Y position

        // Add components to panels
        leftPanel.add(titleLabel);
        leftPanel.add(imageLabel);
        leftPanel.add(copyrightLabel);

        rightPanel.add(searchOrderButton);
        rightPanel.add(searchCustomerButton);
        rightPanel.add(searchBestCustomerButton); // Add new button to panel
        rightPanel.add(backToHomePageButton);

        // Add panels to frame
        add(leftPanel);
        add(rightPanel);

        // Add action listeners
        addSearchOrderButtonListener(e -> {
             new SearchOrderDetailsForm().setVisible(true); // Uncomment when ready
             SearchForm.this.setVisible(false);
        });

        addSearchCustomerButtonListener(e -> {
             new SearchCustomerDetailsForm().setVisible(true); // Uncomment when ready
             SearchForm.this.setVisible(false);
        });

        // Add action listener for the new "Search Best Customer" button
        addSearchBestCustomerButtonListener(e -> {
            new SearchBestCustomerForm().setVisible(true);
            SearchForm.this.setVisible(false);
        });

        addBackToHomePageButtonListener(e -> {
            new BurgerShopMainForm().setVisible(true); // Uncomment when ready
            SearchForm.this.setVisible(false);
        });
    }

    // Helper method to create styled buttons, now outside the constructor and correctly structured
    private JButton createStyledButton(String text, int yPos, Color normalColor, Color hoverColor) {
        JButton button = new JButton(text) {
            private final int ARC_SIZE = 50; // Rounded corners, adjusted to match image

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isRollover()) {
                    g2.setColor(hoverColor);
                } else {
                    g2.setColor(normalColor);
                }
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), ARC_SIZE, ARC_SIZE));
                super.paintComponent(g2); // Paint the text
                g2.dispose();
            }

            @Override
            public boolean contains(int x, int y) {
                // This ensures the mouse listener detects clicks/hovers only within the rounded shape
                Shape shape = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), ARC_SIZE, ARC_SIZE);
                return shape.contains(x, y);
            }
        };
        button.setBounds(110, yPos, 300, 60); // Common bounds
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setContentAreaFilled(false); // Crucial for custom painting
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }


    // Public methods to add ActionListeners for flexibility
    public void addSearchOrderButtonListener(java.awt.event.ActionListener listener) {
        searchOrderButton.addActionListener(listener);
    }

    public void addSearchCustomerButtonListener(java.awt.event.ActionListener listener) {
        searchCustomerButton.addActionListener(listener);
    }

    public void addSearchBestCustomerButtonListener(java.awt.event.ActionListener listener) {
        searchBestCustomerButton.addActionListener(listener);
    }

    public void addBackToHomePageButtonListener(java.awt.event.ActionListener listener) {
        backToHomePageButton.addActionListener(listener);
    }

    //public static void main(String[] args) {
    //    SwingUtilities.invokeLater(() -> {
    //        try {
    //            // Set look and feel to system default for consistency
    //            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
	//
    //        // Create and show the form
    //        SearchForm form = new SearchForm();
    //        form.setVisible(true);
    //    });
    //}
}

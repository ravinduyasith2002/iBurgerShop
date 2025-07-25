import javax.swing.*;
import java.awt.*;

public class SearchForm extends JFrame {
    
    // Declare all components
    private JLabel titleLabel;
    private JLabel imageLabel;
    private JLabel copyrightLabel;
    
    private JButton searchOrderButton;
    private JButton searchCustomerButton;
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
        
        // Create left panel (white) and right panel (gray)
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 380, 550);
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(null);
        
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(380, 0, 520, 550);
        rightPanel.setBackground(new Color(220, 220, 220));
        rightPanel.setLayout(null);
        
        // Initialize title label
        titleLabel = new JLabel("Search Order");
        titleLabel.setBounds(85, 50, 250, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(255, 165, 0)); // Orange color
        
        // Initialize image label
        imageLabel = new JLabel();
        imageLabel.setBounds(60, 120, 320, 290);
        
        // Try to load the burger shop image
        try {
            ImageIcon originalIcon = new ImageIcon("burger_shop.png");
            // Scale the image to fit the label
            Image scaledImage = originalIcon.getImage().getScaledInstance(320, 290, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            imageLabel.setIcon(scaledIcon);
        } catch (Exception e) {
            // If image not found, create a placeholder
            imageLabel.setText("burger_shop.png");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            imageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            imageLabel.setOpaque(true);
            imageLabel.setBackground(Color.WHITE);
        }
        
        // Initialize copyright label
        copyrightLabel = new JLabel("@Ravindu Yasith");
        copyrightLabel.setBounds(145, 440, 150, 20);
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        copyrightLabel.setForeground(Color.GRAY);
        
        // Initialize buttons with rounded corners and hover effects
        searchOrderButton = new JButton("Search Order") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        searchOrderButton.setBounds(110, 140, 300, 60);
        searchOrderButton.setFont(new Font("Arial", Font.BOLD, 18));
        searchOrderButton.setBackground(new Color(255, 119, 85)); // Orange-red color
        searchOrderButton.setForeground(Color.WHITE);
        searchOrderButton.setBorder(BorderFactory.createEmptyBorder());
        searchOrderButton.setFocusPainted(false);
        searchOrderButton.setContentAreaFilled(false);
        searchOrderButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        searchOrderButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchOrderButton.setBackground(new Color(255, 140, 105)); // Lighter shade on hover
                searchOrderButton.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchOrderButton.setBackground(new Color(255, 119, 85)); // Original color
                searchOrderButton.repaint();
            }
        });
        
        searchCustomerButton = new JButton("Search Customer") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        searchCustomerButton.setBounds(110, 230, 300, 60);
        searchCustomerButton.setFont(new Font("Arial", Font.BOLD, 18));
        searchCustomerButton.setBackground(new Color(255, 119, 85)); // Orange-red color
        searchCustomerButton.setForeground(Color.WHITE);
        searchCustomerButton.setBorder(BorderFactory.createEmptyBorder());
        searchCustomerButton.setFocusPainted(false);
        searchCustomerButton.setContentAreaFilled(false);
        searchCustomerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        searchCustomerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchCustomerButton.setBackground(new Color(255, 140, 105)); // Lighter shade on hover
                searchCustomerButton.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchCustomerButton.setBackground(new Color(255, 119, 85)); // Original color
                searchCustomerButton.repaint();
            }
        });
        
        backToHomePageButton = new JButton("Back to Home Page") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        backToHomePageButton.setBounds(110, 380, 300, 60);
        backToHomePageButton.setFont(new Font("Arial", Font.BOLD, 18));
        backToHomePageButton.setBackground(new Color(255, 119, 85)); // Orange-red color
        backToHomePageButton.setForeground(Color.WHITE);
        backToHomePageButton.setBorder(BorderFactory.createEmptyBorder());
        backToHomePageButton.setFocusPainted(false);
        backToHomePageButton.setContentAreaFilled(false);
        backToHomePageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        backToHomePageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backToHomePageButton.setBackground(new Color(255, 140, 105)); // Lighter shade on hover
                backToHomePageButton.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backToHomePageButton.setBackground(new Color(255, 119, 85)); // Original color
                backToHomePageButton.repaint();
            }
        });
        
        // Add components to panels
        leftPanel.add(titleLabel);
        leftPanel.add(imageLabel);
        leftPanel.add(copyrightLabel);
        
        rightPanel.add(searchOrderButton);
        rightPanel.add(searchCustomerButton);
        rightPanel.add(backToHomePageButton);
        
        // Add panels to frame
        add(leftPanel);
        add(rightPanel);
        
        // Add action listeners
        searchOrderButton.addActionListener(e -> {
            new SearchOrderDetailsForm().setVisible(true);
            SearchForm.this.setVisible(false);
        });
        
        searchCustomerButton.addActionListener(e -> {
			new SearchCustomerDetailsForm().setVisible(true);
			SearchForm.this.setVisible(false);
        });
        
        backToHomePageButton.addActionListener(e -> {
            new BurgerShopMainForm().setVisible(true);
            SearchForm.this.setVisible(false);
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set look and feel to system default
                //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // Create and show the form
            SearchForm form = new SearchForm();
            form.setVisible(true);
        });
    }
}

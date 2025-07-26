import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UpdateOrderForm extends JFrame {

    JButton btnUpdateQuantity;
    JButton btnUpdateStatus;
    JButton btnBack;

    public UpdateOrderForm() {
        setTitle("Update Order");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Left panel (White)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBounds(0, 0, 450, 550);
        leftPanel.setLayout(null);
        add(leftPanel);

        // Title
        JLabel lblTitle = new JLabel("Update Order");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblTitle.setForeground(new Color(244, 163, 0)); // Orange
        lblTitle.setBounds(140, 20, 200, 40);
        leftPanel.add(lblTitle);

        // Image
        ImageIcon imageIcon = new ImageIcon("burger_shop.png");
        Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel lblImage = new JLabel(new ImageIcon(image));
        lblImage.setBounds(75, 80, 300, 300);
        leftPanel.add(lblImage);

        // Right panel (Gray)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(224, 224, 224));
        rightPanel.setBounds(450, 0, 450, 550);
        rightPanel.setLayout(null);
        add(rightPanel);

        // Button styling
        Color darkRed = new Color(231, 76, 60);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 16);

        // Update Quantity button (Medium size)
        btnUpdateQuantity = createRoundedButton("Update Quantity", darkRed, buttonFont, 260, 45);
        btnUpdateQuantity.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				UpdateOrderForm.this.setVisible(false);
				new UpdateOrderQuantityForm().setVisible(true);
			}
		});
        btnUpdateQuantity.setBounds(95, 100, 260, 45);
        rightPanel.add(btnUpdateQuantity);

        // Update Status button (Medium size)
        btnUpdateStatus = createRoundedButton("Update Status", darkRed, buttonFont, 260, 45);
        btnUpdateStatus.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				//UpdateOrderForm.this.setVisible(false);
				//
			}
		});
        btnUpdateStatus.setBounds(95, 170, 260, 45);
        rightPanel.add(btnUpdateStatus);

        // Back to Home Page button (Smaller)
        btnBack = createRoundedButton("Back to Home Page", darkRed, buttonFont, 220, 40);
        btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				UpdateOrderForm.this.setVisible(false);
				new BurgerShopMainForm().setVisible(true);
			}
		});
        btnBack.setBounds(115, 260, 220, 40);
        rightPanel.add(btnBack);

        setVisible(true);
    }

    private JButton createRoundedButton(String text, Color bgColor, Font font, int width, int height) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(bgColor.darker());
                } else {
                    g.setColor(bgColor);
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(bgColor);
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
            }
        };
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setForeground(Color.WHITE);
        button.setFont(font);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    //public static void main(String[] args) {
    //    new UpdateOrderForm();
    //}
}

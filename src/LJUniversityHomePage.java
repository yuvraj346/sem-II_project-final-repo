import javax.swing.*;
import java.awt.*;

public class LJUniversityHomePage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LJUniversityHomePage().createUI());
    }



    public void createUI() {
        JFrame frame = new JFrame("LJ University - Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // ----- HEADER PANEL -----
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(24, 61, 89));
        header.setPreferredSize(new Dimension(1000, 60));

        JLabel uniName = new JLabel("LJ University");
        uniName.setFont(new Font("Segoe UI", Font.BOLD, 24));
        uniName.setForeground(Color.WHITE);
        uniName.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));

        // LJ Logo with scaling
        ImageIcon rawLogo = new ImageIcon("C:/Users/YUVRAJ/IdeaProjects/lj companion/src/img.png"); // Replace with actual image path
        Image scaledImage = rawLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(logoIcon);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 20));

        header.add(uniName, BorderLayout.WEST);
        header.add(logoLabel, BorderLayout.EAST);

        // ----- MAIN PANEL -----
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // Hero Section with Image Background
        JPanel heroPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("C:/Users/YUVRAJ/IdeaProjects/lj companion/src/bg.jpg").getImage(); // Replace with actual image path

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        heroPanel.setPreferredSize(new Dimension(1000, 200));
        heroPanel.setLayout(new BorderLayout());
        heroPanel.setOpaque(false);

        JLabel heroText = new JLabel("  EDUCATION IS THE BEST KEY SUCCESS IN LIFE");
        heroText.setForeground(Color.WHITE);
        heroText.setFont(new Font("Arial", Font.BOLD, 20));
        heroText.setBorder(BorderFactory.createEmptyBorder(40, 30, 10, 10));

        JButton learnMore = new JButton("Learn More");
        learnMore.setFocusPainted(false);
        learnMore.setBackground(new Color(255, 111, 0));
        learnMore.setForeground(Color.WHITE);
        learnMore.setFont(new Font("Segoe UI", Font.BOLD, 16));
        learnMore.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        heroPanel.add(heroText, BorderLayout.NORTH);
        heroPanel.add(learnMore, BorderLayout.SOUTH);

        // About Section
        JPanel aboutPanel = new JPanel();
        aboutPanel.setLayout(new BorderLayout());
        aboutPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        aboutPanel.setBackground(Color.WHITE);

        JLabel aboutTitle = new JLabel("A Few Words About the University");
        aboutTitle.setFont(new Font("Arial", Font.BOLD, 18));
        aboutPanel.add(aboutTitle, BorderLayout.NORTH);

        JTextArea aboutText = new JTextArea("LJ University is a leading education group with modern infrastructure and skilled faculty. Students are encouraged to innovate, explore, and lead.");
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        aboutText.setEditable(false);
        aboutText.setBackground(Color.WHITE);

        aboutPanel.add(aboutText, BorderLayout.CENTER);

        // New Info Buttons Section (after description)
        JPanel infoButtons = new JPanel(new GridLayout(2, 3, 10, 10));
        infoButtons.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        infoButtons.setBackground(Color.WHITE);

        String[] infoOptions = {
                "Education Service", "Apply for Admission", "Career Guidance",
                "Task Manager", "Complaint Box", "Students & Results"
        };

        Color[] buttonColors = {
                new Color(17, 138, 178), // teal
                new Color(255, 87, 34),  // deep orange
                new Color(76, 175, 80),  // green
                new Color(33, 150, 243), // blue
                new Color(244, 67, 54),  // red
                new Color(255, 193, 7)   // amber
        };

        for (int i = 0; i < infoOptions.length; i++) {
            JButton optionBtn = new JButton(infoOptions[i]);
            optionBtn.setFocusPainted(false);
            optionBtn.setBackground(buttonColors[i]);
            optionBtn.setForeground(Color.WHITE);
            optionBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            infoButtons.add(optionBtn);
        }

        // Contact Info Section
        JPanel contactPanel = new JPanel();
        contactPanel.setBackground(new Color(24, 61, 89));
        contactPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contactPanel.setPreferredSize(new Dimension(1000, 80));

        JLabel contactLabel = new JLabel("Contact us: info@ljuniversity.edu | +91-9876543210");
        contactLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contactLabel.setForeground(Color.WHITE);

        contactPanel.add(contactLabel);

        mainPanel.add(heroPanel);
        mainPanel.add(aboutPanel);
        mainPanel.add(infoButtons);
        mainPanel.add(contactPanel);

        frame.add(header, BorderLayout.NORTH);
        frame.add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createInfoBox(String title, String description, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(bgColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JTextArea descLabel = new JTextArea(description);
        descLabel.setWrapStyleWord(true);
        descLabel.setLineWrap(true);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descLabel.setForeground(Color.WHITE);
        descLabel.setEditable(false);
        descLabel.setBackground(bgColor);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(descLabel, BorderLayout.CENTER);

        return panel;
    }
}

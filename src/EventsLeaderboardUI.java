// Java Swing-based UI for Events & Leaderboard using JDBC and BST
// Futuristic Neon Dark Theme with Glowing Effects

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Ellipse2D;
import java.sql.*;

class BSTNode {
    String name;
    String enrollmentNo;
    int totalPoints;
    BSTNode left, right;

    BSTNode(String name, String enrollmentNo, int totalPoints) {
        this.name = name;
        this.enrollmentNo = enrollmentNo;
        this.totalPoints = totalPoints;
        left = right = null;
    }
}

class BST {
    BSTNode root;

    void insert(String name, String enrollmentNo, int totalPoints) {
        root = insertRec(root, name, enrollmentNo, totalPoints);
    }

    BSTNode insertRec(BSTNode root, String name, String enrollmentNo, int totalPoints) {
        if (root == null) return new BSTNode(name, enrollmentNo, totalPoints);
        if (totalPoints > root.totalPoints) root.right = insertRec(root.right, name, enrollmentNo, totalPoints);
        else root.left = insertRec(root.left, name, enrollmentNo, totalPoints);
        return root;
    }

    void fillTable(DefaultTableModel model) {
        reverseInOrder(root, model);
    }

    void reverseInOrder(BSTNode root, DefaultTableModel model) {
        if (root != null) {
            reverseInOrder(root.right, model);
            model.addRow(new Object[]{root.name, root.enrollmentNo, root.totalPoints});
            reverseInOrder(root.left, model);
        }
    }

    // Method to get sorted data as a list (similar to printReverseInOrder but returns data)
    java.util.List<BSTNode> getSortedData() {
        java.util.List<BSTNode> sortedList = new java.util.ArrayList<>();
        reverseInOrderToList(root, sortedList);
        return sortedList;
    }

    void reverseInOrderToList(BSTNode root, java.util.List<BSTNode> list) {
        if (root != null) {
            reverseInOrderToList(root.right, list);
            list.add(root);
            reverseInOrderToList(root.left, list);
        }
    }
}

// Futuristic Neon Gradient Panel
class NeonGradientPanel extends JPanel {
    // Blue-black theme
    private Color color1 = new Color(8, 10, 18);   // near black
    private Color color2 = new Color(15, 20, 35);  // deep navy
    private Color color3 = new Color(25, 35, 55);  // dark blue

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Create deep navy to blue gradient background
        GradientPaint gradient1 = new GradientPaint(
                0, 0, color1,
                getWidth(), getHeight(), color2
        );
        g2d.setPaint(gradient1);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Add blue glow effects
        RadialGradientPaint blueGlow1 = new RadialGradientPaint(
                getWidth() * 0.25f, getHeight() * 0.2f, getWidth() * 0.7f,
                new float[]{0.0f, 1.0f},
                new Color[]{new Color(40, 120, 255, 60), new Color(40, 120, 255, 0)}
        );
        g2d.setPaint(blueGlow1);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        RadialGradientPaint blueGlow2 = new RadialGradientPaint(
                getWidth() * 0.75f, getHeight() * 0.8f, getWidth() * 0.6f,
                new float[]{0.0f, 1.0f},
                new Color[]{new Color(80, 180, 255, 40), new Color(80, 180, 255, 0)}
        );
        g2d.setPaint(blueGlow2);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Add subtle grid pattern for futuristic feel
        g2d.setColor(new Color(255, 255, 255, 8));
        g2d.setStroke(new BasicStroke(1));
        for (int i = 0; i < getWidth(); i += 30) {
            g2d.drawLine(i, 0, i, getHeight());
        }
        for (int j = 0; j < getHeight(); j += 30) {
            g2d.drawLine(0, j, getWidth(), j);
        }

        g2d.dispose();
    }
}

// Neon Glowing Panel
class NeonPanel extends JPanel {
    private int radius = 15;
    private Color backgroundColor = new Color(15, 20, 30, 200);
    private boolean hasGlow = false;
    private Color glowColor = new Color(60, 140, 255); // electric blue
    private boolean isHovered = false;

    public NeonPanel() {
        setOpaque(false);
    }

    public NeonPanel(int radius) {
        this.radius = radius;
        setOpaque(false);
    }

    public void setGlowEnabled(boolean enabled) {
        this.hasGlow = enabled;
    }

    public void setGlowColor(Color color) {
        this.glowColor = color;
    }

    public void setHovered(boolean hovered) {
        this.isHovered = hovered;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);

        // Add glow effect
        if (hasGlow || isHovered) {
            Color glow = isHovered ? new Color(glowColor.getRed(), glowColor.getGreen(), glowColor.getBlue(), 80) :
                    new Color(glowColor.getRed(), glowColor.getGreen(), glowColor.getBlue(), 40);
            g2d.setColor(glow);
            g2d.fill(new RoundRectangle2D.Float(-3, -3, getWidth() + 6, getHeight() + 6, radius + 3, radius + 3));
        }

        // Fill background
        g2d.setColor(backgroundColor);
        g2d.fill(roundedRectangle);

        // Add neon border
        if (hasGlow || isHovered) {
            GradientPaint borderGradient = new GradientPaint(
                    0, 0, new Color(60, 140, 255),
                    getWidth(), getHeight(), new Color(140, 200, 255)
            );
            g2d.setPaint(borderGradient);
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(roundedRectangle);
        }

        g2d.dispose();
        super.paintComponent(g);
    }
}

// Neon Glowing Button
class NeonButton extends JButton {
    // Blue gradient colors
    private Color color1 = new Color(50, 120, 255);
    private Color color2 = new Color(120, 200, 255);
    private boolean isHovered = false;
    private boolean isPressed = false;

    public NeonButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setToolTipText(getText());

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isHovered = true;
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                isHovered = false;
                repaint();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                isPressed = true;
                repaint();
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                isPressed = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create gradient
        GradientPaint gradient = new GradientPaint(
                0, 0, isHovered ? color2 : color1,
                getWidth(), getHeight(), isHovered ? color1 : color2
        );
        g2d.setPaint(gradient);

        // Draw rounded rectangle
        int offset = isPressed ? 2 : 0;
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(offset, offset, getWidth() - offset * 2, getHeight() - offset * 2, 15, 15);
        g2d.fill(roundedRectangle);

        // Add inner glow
        if (isHovered) {
            g2d.setColor(new Color(255, 255, 255, 25));
            g2d.fill(roundedRectangle);
        }

        // Draw glowing text
        g2d.setColor(Color.WHITE);
        FontMetrics fm = g2d.getFontMetrics();
        int textX = (getWidth() - fm.stringWidth(getText())) / 2 + offset;
        int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2 + offset;

        // Add text glow
        if (isHovered) {
            g2d.setColor(new Color(200, 220, 255, 100));
            g2d.drawString(getText(), textX + 1, textY + 1);
        }
        g2d.setColor(Color.WHITE);
        g2d.drawString(getText(), textX, textY);

        g2d.dispose();
    }
}

// Event Manager Card Component
class EventManagerCard extends NeonPanel {
    private String managerName;
    private String description;
    private Color accentColor;
    private ActionListener clickListener;
    private String imagePath; // New field for image path

    public EventManagerCard(String managerName, String description, Color accentColor, ActionListener clickListener, String imagePath) {
        this.managerName = managerName;
        this.description = description;
        this.accentColor = accentColor;
        this.clickListener = clickListener;
        this.imagePath = imagePath; // Initialize imagePath

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Adjusted padding
        setGlowEnabled(true);
        setGlowColor(accentColor);
        setPreferredSize(new Dimension(300, 250)); // Increased preferred size to accommodate image
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        createComponents();
        addMouseListener();
    }

    private void createComponents() {
        // Image Label
        JLabel imageLabel = new JLabel();
        if (this.imagePath != null && !this.imagePath.isEmpty()) { // Use 'this.imagePath' explicitly
            try {
                ImageIcon originalIcon = new ImageIcon(this.imagePath); // Use 'this.imagePath'
                Image originalImage = originalIcon.getImage();
                // Scale image to fit within the card, e.g., 280x150 (adjust as needed)
                Image scaledImage = originalImage.getScaledInstance(280, 150, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                imageLabel.setBorder(new EmptyBorder(0, 0, 10, 0)); // Padding below image
            } catch (Exception e) {
                System.err.println("Error loading image: " + this.imagePath + " - " + e.getMessage()); // Use 'this.imagePath'
                imageLabel.setText("Image Error"); // Fallback text
                imageLabel.setForeground(Color.RED);
            }
        }
        add(imageLabel, BorderLayout.NORTH); // Add image to the top

        // Panel for name and description
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBorder(new EmptyBorder(0, 10, 0, 10)); // Padding for text

        // Manager name
        JLabel nameLabel = new JLabel(managerName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align text

        // Description
        JLabel descLabel = new JLabel("<html><div style='text-align: center;'>" + description + "</div></html>"); // HTML for center alignment and wrapping
        descLabel.setForeground(new Color(200, 200, 200));
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align text

        textPanel.add(nameLabel);
        textPanel.add(Box.createVerticalStrut(5)); // Small gap
        textPanel.add(descLabel);

        add(textPanel, BorderLayout.CENTER); // Add text panel to the center
    }

    private void addMouseListener() {
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (clickListener != null) {
                    clickListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, managerName));
                }
            }
        });
    }
}

// Event Card Component
class EventCard extends NeonPanel {
    private String eventName;
    private String description;
    private int points;
    private String date;
    private Color accentColor;

    public EventCard(String eventName, String description, int points, String date, Color accentColor) {
        this.eventName = eventName;
        this.description = description;
        this.points = points;
        this.date = date;
        this.accentColor = accentColor;

        setLayout(new BorderLayout(10, 10)); // Added hgap, vgap for better spacing
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setGlowEnabled(true);
        setGlowColor(accentColor);


        createComponents();
    }

    private void createComponents() {
        // Top panel for event name and points
        JPanel topInfoPanel = new JPanel(new BorderLayout());
        topInfoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(eventName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Increased font size
        nameLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
        topInfoPanel.add(nameLabel, BorderLayout.WEST);

        JLabel pointsLabel = new JLabel("Points: " + points);
        pointsLabel.setForeground(accentColor);
        pointsLabel.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Increased font size
        topInfoPanel.add(pointsLabel, BorderLayout.EAST);

        // Middle panel for truncated description
        JTextArea descPreviewArea = new JTextArea();
        descPreviewArea.setText(truncateDescription(description, 100)); // Truncate for preview
        descPreviewArea.setEditable(false);
        descPreviewArea.setWrapStyleWord(true);
        descPreviewArea.setLineWrap(true);
        descPreviewArea.setOpaque(false);
        descPreviewArea.setForeground(new Color(200, 200, 200));
        descPreviewArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descPreviewArea.setBorder(new EmptyBorder(0, 0, 10, 0)); // Added bottom padding

        // Bottom panel for date and description button
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 0));
        bottomPanel.setOpaque(false);

        JLabel dateLabel = new JLabel(date);
        dateLabel.setForeground(new Color(180, 180, 180));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        bottomPanel.add(dateLabel, BorderLayout.WEST);

        NeonButton descriptionBtn = new NeonButton("Description");
        descriptionBtn.setPreferredSize(new Dimension(100, 35));
        descriptionBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        descriptionBtn.addActionListener(e -> showDescriptionDialog());
        bottomPanel.add(descriptionBtn, BorderLayout.EAST);

        add(topInfoPanel, BorderLayout.NORTH);
        add(descPreviewArea, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private String truncateDescription(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength) + "...";
    }

    private void showDescriptionDialog() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), eventName + " Description", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(500, 350);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.setBackground(new Color(15, 20, 30));

        NeonPanel dialogPanel = new NeonPanel(15);
        dialogPanel.setLayout(new BorderLayout(15, 15));
        dialogPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        dialogPanel.setGlowEnabled(true);
        dialogPanel.setGlowColor(accentColor);

        JLabel titleLabel = new JLabel(eventName, SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        dialogPanel.add(titleLabel, BorderLayout.NORTH);

        JTextArea fullDescriptionArea = new JTextArea(description);
        fullDescriptionArea.setEditable(false);
        fullDescriptionArea.setWrapStyleWord(true);
        fullDescriptionArea.setLineWrap(true);
        fullDescriptionArea.setBackground(new Color(25, 30, 40));
        fullDescriptionArea.setForeground(new Color(220, 220, 220));
        fullDescriptionArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        fullDescriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(fullDescriptionArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(accentColor.darker(), 1));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        dialogPanel.add(scrollPane, BorderLayout.CENTER);

        NeonButton closeButton = new NeonButton("Close");
        closeButton.setPreferredSize(new Dimension(80, 35));
        closeButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        buttonPanel.add(closeButton);
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(dialogPanel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }
}

// Leaderboard Row Component
class LeaderboardRow extends NeonPanel {
    private int rank;
    private String name;
    private String enrollmentNo;
    private int totalPoints;

    public LeaderboardRow(int rank, String name, String enrollmentNo, int totalPoints) {
        this.rank = rank;
        this.name = name;
        this.enrollmentNo = enrollmentNo;
        this.totalPoints = totalPoints;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(15, 20, 15, 20));
        setGlowEnabled(true);
        setGlowColor(getRankColor(rank));
        setPreferredSize(new Dimension(800, 60));

        createComponents();
    }

    private Color getRankColor(int rank) {
        switch (rank) {
            case 1: return new Color(255, 215, 0); // Gold
            case 2: return new Color(192, 192, 192); // Silver
            case 3: return new Color(205, 127, 50); // Bronze
            default: return new Color(255, 50, 150); // Neon pink
        }
    }

    private void createComponents() {
        // Rank
        JLabel rankLabel = new JLabel("#" + rank);
        rankLabel.setForeground(getRankColor(rank));
        rankLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        rankLabel.setPreferredSize(new Dimension(60, 0));

        // Name
        JLabel nameLabel = new JLabel(name);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        // Enrollment
        JLabel enrollmentLabel = new JLabel(enrollmentNo);
        enrollmentLabel.setForeground(new Color(200, 200, 200));
        enrollmentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Points
        JLabel pointsLabel = new JLabel(String.valueOf(totalPoints));
        pointsLabel.setForeground(new Color(50, 255, 255));
        pointsLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        // Layout
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        leftPanel.add(rankLabel, BorderLayout.WEST);
        leftPanel.add(nameLabel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(enrollmentLabel, BorderLayout.WEST);
        rightPanel.add(pointsLabel, BorderLayout.EAST);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }
}
class EventsLeaderboardUI {
    static final String DB_URL = "jdbc:mysql://localhost:3306/events";
    static final String USER = "root";
    static final String PASS = "";

    private JPanel eventsContentPanel;
    private JPanel leaderboardContentPanel;
    private CardLayout cardLayout;
    private String currentView = "MANAGERS"; // Track current view: MANAGERS, EVENTS, LEADERBOARD
    private String currentManager = ""; // Track which manager is selected
    private NeonButton headerBackButton; // Reference to header back button

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventsLeaderboardUI().createAndShowGUI());
    }

    public void createAndShowGUI() {
        applyBlueTheme();
        JFrame frame = new JFrame("Gaming Leaderboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 900);
        frame.setLayout(new BorderLayout());

        // Set futuristic neon gradient background
        frame.setContentPane(new NeonGradientPanel());

        // Create main layout
        JPanel mainLayout = new JPanel(new BorderLayout());
        mainLayout.setOpaque(false);

        // Create header, content, footer
        JPanel headerPanel = createHeaderPanel();
        JPanel contentPanel = createContentPanel();
        JPanel footerPanel = createFooterPanel();

        mainLayout.add(headerPanel, BorderLayout.NORTH);
        mainLayout.add(contentPanel, BorderLayout.CENTER); // content fills center
        mainLayout.add(footerPanel, BorderLayout.SOUTH);   // footer at bottom

        frame.add(mainLayout, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void applyBlueTheme() {
        // UIManager tweaks for blue-black-white scheme
        UIManager.put("ToolTip.background", new Color(20, 24, 35));
        UIManager.put("ToolTip.foreground", Color.WHITE);
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        UIManager.put("Table.background", new Color(15, 20, 30));
        UIManager.put("Table.foreground", Color.WHITE);
        UIManager.put("Table.selectionBackground", new Color(50, 120, 255));
        UIManager.put("Table.selectionForeground", Color.WHITE);
        UIManager.put("Table.gridColor", new Color(40, 50, 70));
        UIManager.put("TableHeader.background", new Color(20, 28, 45));
        UIManager.put("TableHeader.foreground", Color.WHITE);
        UIManager.put("ScrollBar.thumb", new Color(50, 120, 255));
        UIManager.put("ScrollBar.track", new Color(15, 20, 30));
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(true);
        headerPanel.setBackground(new Color(10, 12, 18)); // solid full-width bar
        headerPanel.setBorder(new EmptyBorder(8, 16, 8, 16));

        // Back to Home button
        headerBackButton = new NeonButton("< Back");
        headerBackButton.setPreferredSize(new Dimension(96, 28));
        headerBackButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        headerBackButton.setToolTipText("Go back to Home");
        headerBackButton.addActionListener(e -> handleBackNavigation());

        // Centered navigation buttons
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 24, 0));
        navPanel.setOpaque(false);
        NeonButton eventsTab = new NeonButton("Events");
        eventsTab.setPreferredSize(new Dimension(120, 32));
        eventsTab.setFont(new Font("Segoe UI", Font.BOLD, 13));
        eventsTab.setToolTipText("View Events");
        eventsTab.addActionListener(e -> showEventsView());
        NeonButton leaderboardTab = new NeonButton("Leaderboard");
        leaderboardTab.setPreferredSize(new Dimension(120, 32));
        leaderboardTab.setFont(new Font("Segoe UI", Font.BOLD, 13));
        leaderboardTab.setToolTipText("View Leaderboard");
        leaderboardTab.addActionListener(e -> showLeaderboardView());
        navPanel.add(eventsTab);
        navPanel.add(leaderboardTab);

        headerPanel.add(headerBackButton, BorderLayout.WEST);
        headerPanel.add(navPanel, BorderLayout.CENTER);

        return headerPanel;
    }

    private void handleBackNavigation() {
        if (currentView.equals("EVENTS")) {
            // If viewing events, go back to managers
            showEventManagersView();
        } else {
            // If on managers or leaderboard, go to home
            Component comp = headerBackButton;
            while (comp != null && !(comp instanceof JFrame)) {
                comp = comp.getParent();
            }
            if (comp != null) {
                ((JFrame) comp).dispose();
            }
            HomePageSwing.show();
        }
    }

    private JPanel createTabPanel() {
        NeonPanel tabPanel = new NeonPanel(0);
        tabPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 15));
        tabPanel.setBackground(new Color(20, 20, 35, 200));
        tabPanel.setBorder(new EmptyBorder(10, 30, 10, 30));

        // Events tab
        NeonButton eventsTab = new NeonButton("📅 Events");
        eventsTab.setPreferredSize(new Dimension(120, 40));
        eventsTab.setToolTipText("View Events");
        eventsTab.addActionListener(e -> showEventsView());

        // Leaderboard tab
        NeonButton leaderboardTab = new NeonButton("🏆 Leaderboard");
        leaderboardTab.setPreferredSize(new Dimension(150, 40));
        leaderboardTab.setToolTipText("View Leaderboard");
        leaderboardTab.addActionListener(e -> showLeaderboardView());

        tabPanel.add(eventsTab);
        tabPanel.add(leaderboardTab);

        return tabPanel;
    }

    private JPanel createContentPanel() {
        cardLayout = new CardLayout();
        JPanel contentPanel = new JPanel(cardLayout);
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0)); // Removed padding

        // Create content panels
        eventsContentPanel = createEventsContentPanel();
        leaderboardContentPanel = createLeaderboardContentPanel();

        contentPanel.add(eventsContentPanel, "EVENTS");
        contentPanel.add(leaderboardContentPanel, "LEADERBOARD");

        // Show events by default
        cardLayout.show(contentPanel, "EVENTS");

        return contentPanel;
    }

    private JPanel createEventsContentPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        // Create event managers view (first view)
        JPanel eventManagersPanel = createEventManagersView();
        mainPanel.add(eventManagersPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createEventManagersView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(0, 0, 0, 0)); // Removed padding from this panel

        // Title (text only, no emoji)
        JLabel titleLabel = new JLabel("Select Event Manager to Browse Events", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setBorder(new EmptyBorder(0, 0, 15, 0)); // Reduced bottom padding

        // Event managers grid
        JPanel managersGrid = new JPanel(new GridLayout(0, 3, 30, 30));
        managersGrid.setOpaque(false);

        // Binary Brains
        EventManagerCard binaryBrainsCard = new EventManagerCard(
                "Binary Brains",
                "Technical and coding events",
                new Color(60, 140, 255),
                e -> showEventManagerEvents("Binary Brains"),
                "C:/Users/YUVRAJ/IdeaProjects/example/src/binarybrainsimg.jpeg" // Added image path
        );

        // LFA
        EventManagerCard lfaCard = new EventManagerCard(
                "LFA",
                "Innovation and technology events",
                new Color(120, 200, 255),
                e -> showEventManagerEvents("LFA"),
                "C:/Users/YUVRAJ/IdeaProjects/example/src/lfa.jpeg" // Added image path
        );

        // LJSC
        EventManagerCard ljscCard = new EventManagerCard(
                "LJSC Events",
                "Cultural and sports events",
                new Color(100, 160, 255),
                e -> showEventManagerEvents("LJSC"),
                "C:/Users/YUVRAJ/IdeaProjects/example/src/ljsc.jpeg" // Added image path
        );

        managersGrid.add(binaryBrainsCard);
        managersGrid.add(lfaCard);
        managersGrid.add(ljscCard);

        JScrollPane scrollPane = new JScrollPane(managersGrid);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void showEventManagerEvents(String managerName) {
        currentView = "EVENTS";
        currentManager = managerName;
        headerBackButton.setToolTipText("Go back to Event Managers");
        eventsContentPanel.removeAll();

        JPanel eventsPanel = new JPanel(new BorderLayout());
        eventsPanel.setOpaque(false);
        eventsPanel.setBorder(new EmptyBorder(0, 0, 0, 0)); // Removed padding from this panel

        // Title
        JLabel titleLabel = new JLabel(managerName + " Events", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(new EmptyBorder(20, 0, 30, 0));

        // Events grid (responsive columns)
        JPanel eventsGrid = new JPanel();
        eventsGrid.setLayout(new BoxLayout(eventsGrid, BoxLayout.Y_AXIS));
        eventsGrid.setOpaque(false);
        // Remove component listener for responsive columns as it's now a single column list
        // eventsGrid.addComponentListener(new java.awt.event.ComponentAdapter() {
        //     @Override public void componentResized(java.awt.event.ComponentEvent e) {
        //         // No dynamic column adjustment needed for single column layout
        //     }
        // });

        Color accentColor = getManagerColor(managerName);

        // Add events based on manager
        if (managerName.equals("Binary Brains")) {
            eventsGrid.add(new EventCard("Treasure Hunt", "Solve puzzles and find hidden treasures in a thrilling adventure across the campus. Team up with friends and follow clues to uncover the grand prize!", 100, "2024-01-15", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("Active Coding", "Participate in a fast-paced coding competition to test your algorithms and problem-solving skills against other talented coders. Prizes for top performers!", 150, "2024-01-20", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("Battle of Ground VO", "Showcase your vocal talents in a dynamic voice-over battle. Impress the judges with your character impressions and storytelling abilities.", 80, "2024-01-25", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
        } else if (managerName.equals("LFA")) {
            eventsGrid.add(new EventCard("Hackathon", "A 24-hour intense coding challenge where teams develop innovative solutions to real-world problems. Bring your ideas to life!", 200, "2024-02-01", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("Scavenger Hunt", "Embark on a digital scavenger hunt, decoding riddles and finding virtual clues to reach the final destination. Fun for all ages!", 120, "2024-02-05", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("Scramble Words", "A challenging word puzzle competition where you unscramble letters to form words. Sharpen your vocabulary and quick thinking!", 90, "2024-02-10", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
        } else if (managerName.equals("LJSC")) {
            eventsGrid.add(new EventCard("Lumina Dance", "A vibrant dance performance competition showcasing various styles and choreographies. Express yourself through movement and rhythm!", 130, "2024-02-15", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("Lumina Music", "A musical talent showcase featuring a diverse range of genres and instruments. Share your passion for music with the audience!", 140, "2024-02-20", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("Lumina Drama", "A dramatic arts competition where participants perform short plays and skits. Bring characters to life with your acting prowess!", 110, "2024-02-25", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("Mr & Ms LJ", "A prestigious beauty and talent pageant, celebrating charisma, intellect, and grace. Compete for the coveted titles!", 160, "2024-03-01", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("Carpedium - Cricket", "A thrilling cricket tournament for all enthusiasts. Form your teams and compete for the championship trophy!", 180, "2024-03-05", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("Carpedium - Football", "An exciting football championship for passionate players. Showcase your skills and teamwork on the field!", 170, "2024-03-10", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            // Add more events to force scrolling
            eventsGrid.add(new EventCard("Basketball Blitz", "Fast-paced basketball tournament. Shoot hoops and dominate the court!", 120, "2024-03-15", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("Volleyball Royale", "Spike, set, and block your way to victory in this volleyball showdown.", 110, "2024-03-20", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("Chess Challenge", "Outsmart your opponents in a strategic chess competition.", 90, "2024-03-25", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("Debate Duel", "Engage in intellectual debates and hone your public speaking skills.", 100, "2024-03-30", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("Photography Contest", "Capture stunning moments and express your creativity through photography.", 130, "2024-04-05", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
            eventsGrid.add(new EventCard("E-Sports Extravaganza", "Compete in popular video games and prove your gaming prowess.", 170, "2024-04-10", accentColor));
            eventsGrid.add(Box.createVerticalStrut(25));
        }

        JScrollPane scrollPane = new JScrollPane(eventsGrid);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Force vertical scrollbar to always show
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Ensure no horizontal scrollbar

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(titleLabel, BorderLayout.CENTER); // header back button handles navigation
        // eventsPanel.add(topPanel, BorderLayout.NORTH); // Removed eventsPanel

        // eventsPanel.add(scrollPane, BorderLayout.CENTER); // Removed eventsPanel

        eventsContentPanel.add(topPanel, BorderLayout.NORTH);
        eventsContentPanel.add(scrollPane, BorderLayout.CENTER); // Directly add scrollPane to eventsContentPanel
        eventsContentPanel.revalidate();
        eventsContentPanel.repaint();
    }

    private void showEventManagersView() {
        currentView = "MANAGERS";
        currentManager = "";
        headerBackButton.setToolTipText("Go back to Home");
        eventsContentPanel.removeAll();
        eventsContentPanel.add(createEventManagersView());
        eventsContentPanel.revalidate();
        eventsContentPanel.repaint();
    }

    private Color getManagerColor(String managerName) {
        switch (managerName) {
            case "Binary Brains": return new Color(60, 140, 255);
            case "LFA": return new Color(120, 200, 255);
            case "LJSC": return new Color(100, 160, 255);
            default: return new Color(60, 140, 255);
        }
    }

    private JPanel createLeaderboardContentPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        // Search bar
        NeonPanel searchPanel = new NeonPanel(10);
        searchPanel.setLayout(new BorderLayout());
        searchPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        searchPanel.setGlowEnabled(true);
        searchPanel.setGlowColor(new Color(255, 50, 150));

        String placeholder="Search by name or enrollment...";
        JTextField searchField = new JTextField(placeholder);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setForeground(Color.WHITE);
        searchField.setBackground(new Color(30, 30, 50));
        searchField.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        searchField.setCaretColor(Color.WHITE);
        searchField.setCaretPosition(0);

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(placeholder)) {
                    searchField.setText("");
                    searchField.setForeground(Color.WHITE); // normal typing color
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText(placeholder);
                    searchField.setForeground(Color.GRAY); // back to placeholder color
                    searchField.setCaretPosition(0);
                }
            }
        });


        searchPanel.add(searchField, BorderLayout.CENTER);

        // Leaderboard content
        JPanel leaderboardContent = new JPanel();
        leaderboardContent.setLayout(new BoxLayout(leaderboardContent, BoxLayout.Y_AXIS));
        leaderboardContent.setOpaque(false);

        // Try to load data from database, show error if fails
        try {
            loadLeaderboardData(leaderboardContent);
        } catch (Exception e) {
            showDatabaseError(leaderboardContent);
        }

        JScrollPane scrollPane = new JScrollPane(leaderboardContent);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private void loadLeaderboardData(JPanel container) throws Exception {
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);

             PreparedStatement pst = con.prepareStatement("SELECT name, enrollment_no, " +
                     "(SELECT SUM(points_earned) FROM participants p WHERE p.enrollment_no = u.enrollment_no) AS total_points " +
                     "FROM users u WHERE EXISTS (SELECT 1 FROM participants p WHERE p.enrollment_no = u.enrollment_no)");
             ResultSet rs = pst.executeQuery()) {

            BST leaderboard = new BST();

            // Insert each student into BST with their total points
            while (rs.next()) {
                String name = rs.getString("name");
                String enrollment = rs.getString("enrollment_no");
                int points = rs.getInt("total_points");

                leaderboard.insert(name, enrollment, points);
            }

            // Get sorted data from BST (highest points first)
            java.util.List<BSTNode> sortedData = leaderboard.getSortedData();

            // Add leaderboard rows from actual database data (sorted by points)
            int rank = 1;
            for (BSTNode node : sortedData) {
                container.add(new LeaderboardRow(rank, node.name, node.enrollmentNo, node.totalPoints));
                container.add(Box.createVerticalStrut(10));
                rank++;
            }

            // If no data found, show a message
            if (sortedData.isEmpty()) {
                JLabel noDataLabel = new JLabel("No leaderboard data available", SwingConstants.CENTER);
                noDataLabel.setForeground(Color.WHITE);
                noDataLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
                container.add(noDataLabel);
            }
        }
    }

    private void showDatabaseError(JPanel container) {
        NeonPanel errorPanel = new NeonPanel(15);
        errorPanel.setLayout(new BorderLayout());
        errorPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        errorPanel.setGlowEnabled(true);
        errorPanel.setGlowColor(new Color(255, 100, 100));

        JLabel errorLabel = new JLabel("⚠️ Database Connection Error", SwingConstants.CENTER);
        errorLabel.setForeground(Color.WHITE);
        errorLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        errorLabel.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel messageLabel = new JLabel("Unable to connect to database. Please check your connection and try again.", SwingConstants.CENTER);
        messageLabel.setForeground(new Color(200, 200, 200));
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        errorPanel.add(errorLabel, BorderLayout.NORTH);
        errorPanel.add(messageLabel, BorderLayout.CENTER);

        container.add(errorPanel);
    }

    private void showEventsView() {
        currentView = "MANAGERS";
        currentManager = "";
        headerBackButton.setToolTipText("Go back to Home");
        cardLayout.show(eventsContentPanel.getParent(), "EVENTS");
    }

    private void showLeaderboardView() {
        currentView = "LEADERBOARD";
        currentManager = "";
        headerBackButton.setToolTipText("Go back to Home");
        cardLayout.show(leaderboardContentPanel.getParent(), "LEADERBOARD");
    }

    private JPanel createFooterPanel() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(true);
        footer.setBackground(new Color(10, 12, 18));
        footer.setBorder(new EmptyBorder(6, 16, 6, 16));
        JLabel credits = new JLabel("© 2025 Gaming Leaderboard • v1.0");
        credits.setForeground(new Color(210, 215, 230));
        credits.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footer.add(credits, BorderLayout.EAST);
        return footer;
    }
}

// Java Swing-based UI for Events & Leaderboard using JDBC and BST
// Futuristic Neon Dark Theme with Glowing Effects

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public EventManagerCard(String managerName, String description, Color accentColor, ActionListener clickListener) {
        this.managerName = managerName;
        this.description = description;
        this.accentColor = accentColor;
        this.clickListener = clickListener;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setGlowEnabled(true);
        setGlowColor(accentColor);
        setPreferredSize(new Dimension(300, 150));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        createComponents();
        addMouseListener();
    }

    private void createComponents() {
        // Manager name
        JLabel nameLabel = new JLabel(managerName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        nameLabel.setBorder(new EmptyBorder(0, 0, 15, 0));

        // Description
        JLabel descLabel = new JLabel(description);
        descLabel.setForeground(new Color(200, 200, 200));
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        add(nameLabel, BorderLayout.NORTH);
        add(descLabel, BorderLayout.CENTER);
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

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setGlowEnabled(true);
        setGlowColor(accentColor);
        setPreferredSize(new Dimension(250, 120));

        createComponents();
    }

    private void createComponents() {
        // Event name
        JLabel nameLabel = new JLabel(eventName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        // Description
        JLabel descLabel = new JLabel(description);
        descLabel.setForeground(new Color(200, 200, 200));
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setBorder(new EmptyBorder(0, 0, 15, 0));

        // Bottom panel for points, date, and register button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        // Points and date
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setOpaque(false);

        JLabel pointsLabel = new JLabel("Points: " + points);
        pointsLabel.setForeground(accentColor);
        pointsLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JLabel dateLabel = new JLabel(date);
        dateLabel.setForeground(new Color(180, 180, 180));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        infoPanel.add(pointsLabel, BorderLayout.WEST);
        infoPanel.add(dateLabel, BorderLayout.EAST);

        // Register button
        NeonButton registerBtn = new NeonButton("Register");
        registerBtn.setPreferredSize(new Dimension(80, 30));

        bottomPanel.add(infoPanel, BorderLayout.CENTER);
        bottomPanel.add(registerBtn, BorderLayout.EAST);

        add(nameLabel, BorderLayout.NORTH);
        add(descLabel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
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

public class EventsLeaderboardUI {
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
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

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

        // Title (text only, no emoji)
        JLabel titleLabel = new JLabel("Select Event Manager to Browse Events", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setBorder(new EmptyBorder(0, 0, 30, 0));

        // Event managers grid
        JPanel managersGrid = new JPanel(new GridLayout(0, 3, 30, 30));
        managersGrid.setOpaque(false);

        // Binary Brains
        EventManagerCard binaryBrainsCard = new EventManagerCard(
                "Binary Brains",
                "Technical and coding events",
                new Color(60, 140, 255),
                e -> showEventManagerEvents("Binary Brains")
        );

        // LFA
        EventManagerCard lfaCard = new EventManagerCard(
                "LFA",
                "Innovation and technology events",
                new Color(120, 200, 255),
                e -> showEventManagerEvents("LFA")
        );

        // LJSC
        EventManagerCard ljscCard = new EventManagerCard(
                "LJSC Events",
                "Cultural and sports events",
                new Color(100, 160, 255),
                e -> showEventManagerEvents("LJSC")
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

        // Title
        JLabel titleLabel = new JLabel(managerName + " Events", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(new EmptyBorder(20, 0, 30, 0));

        // Events grid (responsive columns)
        JPanel eventsGrid = new JPanel();
        eventsGrid.setLayout(new GridLayout(0, 2, 20, 20)); // default 2 columns
        eventsGrid.setOpaque(false);
        // Adjust columns on resize: 3 columns if width > 1100
        eventsGrid.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override public void componentResized(java.awt.event.ComponentEvent e) {
                int w = eventsGrid.getWidth();
                int cols = (w > 1100) ? 3 : 2;
                GridLayout gl = (GridLayout) eventsGrid.getLayout();
                if (gl.getColumns() != cols) {
                    eventsGrid.setLayout(new GridLayout(0, cols, 20, 20));
                    eventsGrid.revalidate();
                }
            }
        });

        Color accentColor = getManagerColor(managerName);

        // Add events based on manager
        if (managerName.equals("Binary Brains")) {
            eventsGrid.add(new EventCard("Treasure Hunt", "Solve puzzles and find hidden treasures", 100, "2024-01-15", accentColor));
            eventsGrid.add(new EventCard("Active Coding", "Code your way to victory", 150, "2024-01-20", accentColor));
            eventsGrid.add(new EventCard("Battle of Ground VO", "Voice-over battle competition", 80, "2024-01-25", accentColor));
        } else if (managerName.equals("LFA")) {
            eventsGrid.add(new EventCard("Hackathon", "24-hour coding challenge", 200, "2024-02-01", accentColor));
            eventsGrid.add(new EventCard("Scavenger Hunt", "Digital scavenger hunt", 120, "2024-02-05", accentColor));
            eventsGrid.add(new EventCard("Scramble Words", "Word puzzle competition", 90, "2024-02-10", accentColor));
        } else if (managerName.equals("LJSC")) {
            eventsGrid.add(new EventCard("Lumina Dance", "Dance performance competition", 130, "2024-02-15", accentColor));
            eventsGrid.add(new EventCard("Lumina Music", "Musical talent showcase", 140, "2024-02-20", accentColor));
            eventsGrid.add(new EventCard("Lumina Drama", "Dramatic arts competition", 110, "2024-02-25", accentColor));
            eventsGrid.add(new EventCard("Mr & Ms LJ", "Beauty and talent pageant", 160, "2024-03-01", accentColor));
            eventsGrid.add(new EventCard("Carpedium - Cricket", "Cricket tournament", 180, "2024-03-05", accentColor));
            eventsGrid.add(new EventCard("Carpedium - Football", "Football championship", 170, "2024-03-10", accentColor));
        }

        JScrollPane scrollPane = new JScrollPane(eventsGrid);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(titleLabel, BorderLayout.CENTER); // header back button handles navigation
        eventsPanel.add(topPanel, BorderLayout.NORTH);

        eventsPanel.add(scrollPane, BorderLayout.CENTER);

        eventsContentPanel.add(eventsPanel);
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

        JTextField searchField = new JTextField("Search by name or enrollment...");
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setForeground(Color.WHITE);
        searchField.setBackground(new Color(30, 30, 50));
        searchField.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        searchField.setCaretColor(Color.WHITE);

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

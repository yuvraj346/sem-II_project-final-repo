import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

class CareerGuidanceChatBotUI {

    // --- Backend Logic and Data ---
    private final Color PRIMARY_COLOR = new Color(30, 58, 138); // Dark blue
    private final Color SECONDARY_COLOR = new Color(66, 135, 245); // Bright blue
    private final Color BACKGROUND_COLOR = new Color(249, 250, 251); // Light gray
    private final Color ACCENT_COLOR = new Color(236, 72, 153); // Pink accent
    private final Color SUCCESS_COLOR = new Color(34, 197, 94); // Green
    private final Color WARNING_COLOR = new Color(245, 158, 11); // Orange

    private final Map<String, String> careerDescriptions = new LinkedHashMap<>();
    private final Map<String, Integer> jobGrowthData = new LinkedHashMap<>();
    private final Map<String, List<String>> careerSkills = new LinkedHashMap<>();
    private final Map<String, String> careerImages = new LinkedHashMap<>();

    public CareerGuidanceChatBotUI() {
        initializeCareerData();
    }

    private void initializeCareerData() {
        // Enhanced career descriptions with more details
        careerDescriptions.put("Computer Engineering",
                "🚀 **Computer Engineering: The Digital Revolution**\n\n" +
                        "Computer Engineering is the perfect blend of hardware and software expertise, " +
                        "creating the foundation for our digital future. This dynamic field combines " +
                        "electrical engineering principles with computer science to design and develop " +
                        "computer systems, networks, and applications.\n\n" +
                        "**🎯 What You'll Learn:**\n" +
                        "• Digital Logic Design & Computer Architecture\n" +
                        "• Programming Languages (Python, C++, Java, Assembly)\n" +
                        "• Operating Systems & Computer Networks\n" +
                        "• Embedded Systems & IoT Development\n" +
                        "• Artificial Intelligence & Machine Learning\n" +
                        "• Cybersecurity & Digital Forensics\n\n" +
                        "**💼 Top Career Paths:**\n" +
                        "• Software Architect (Avg. Salary: $130,000)\n" +
                        "• Robotics Engineer (Avg. Salary: $110,000)\n" +
                        "• Cybersecurity Specialist (Avg. Salary: $120,000)\n" +
                        "• AI/ML Engineer (Avg. Salary: $140,000)\n" +
                        "• Embedded Systems Engineer (Avg. Salary: $105,000)\n\n" +
                        "**🏢 Top Employers:** Google, Tesla, NASA, Microsoft, Intel, NVIDIA\n" +
                        "**📈 Job Growth:** 22% (Much faster than average)\n" +
                        "**🎓 Our Success:** 95% job placement rate within 6 months");

        careerDescriptions.put("Mechanical Engineering",
                "⚙️ **Mechanical Engineering: Building Tomorrow's World**\n\n" +
                        "Mechanical Engineering is the oldest and most diverse engineering discipline, " +
                        "focusing on designing, analyzing, and manufacturing mechanical systems. " +
                        "From tiny micro-machines to massive industrial plants, mechanical engineers " +
                        "create solutions that power our modern world.\n\n" +
                        "**🎯 What You'll Learn:**\n" +
                        "• Thermodynamics & Heat Transfer\n" +
                        "• Fluid Mechanics & Aerodynamics\n" +
                        "• Materials Science & Manufacturing\n" +
                        "• CAD/CAM & 3D Modeling\n" +
                        "• Robotics & Automation\n" +
                        "• Renewable Energy Systems\n\n" +
                        "**💼 Top Career Paths:**\n" +
                        "• Aerospace Engineer (Avg. Salary: $115,000)\n" +
                        "• Automotive Engineer (Avg. Salary: $95,000)\n" +
                        "• Renewable Energy Engineer (Avg. Salary: $100,000)\n" +
                        "• Biomedical Engineer (Avg. Salary: $105,000)\n" +
                        "• Manufacturing Engineer (Avg. Salary: $90,000)\n\n" +
                        "**🏢 Top Employers:** Boeing, Tesla, General Electric, Lockheed Martin\n" +
                        "**📈 Job Growth:** 9% (Faster than average)\n" +
                        "**🎓 Our Success:** State-of-the-art prototyping lab with 3D printing & CNC machining");

        careerDescriptions.put("Electrical Engineering",
                "⚡ **Electrical Engineering: Powering Innovation**\n\n" +
                        "Electrical Engineering is the backbone of modern technology, focusing on " +
                        "electricity, electronics, and electromagnetism. This field drives innovations " +
                        "in renewable energy, smart grids, electric vehicles, and telecommunications.\n\n" +
                        "**🎯 What You'll Learn:**\n" +
                        "• Circuit Theory & Electronic Design\n" +
                        "• Power Systems & Energy Distribution\n" +
                        "• Control Systems & Automation\n" +
                        "• Signal Processing & Communications\n" +
                        "• Renewable Energy Technologies\n" +
                        "• Electric Vehicle Systems\n\n" +
                        "**💼 Top Career Paths:**\n" +
                        "• Power Systems Engineer (Avg. Salary: $110,000)\n" +
                        "• Electronics Engineer (Avg. Salary: $100,000)\n" +
                        "• Control Systems Engineer (Avg. Salary: $105,000)\n" +
                        "• Telecommunications Engineer (Avg. Salary: $95,000)\n" +
                        "• Renewable Energy Engineer (Avg. Salary: $100,000)\n\n" +
                        "**🏢 Top Employers:** Tesla, Siemens, General Electric, ABB, Schneider Electric\n" +
                        "**📈 Job Growth:** 7% (As fast as average)\n" +
                        "**🎓 Our Success:** 92% job placement rate with local energy company partnerships");

        careerDescriptions.put("Civil Engineering",
                "🏗️ **Civil Engineering: Shaping Our Infrastructure**\n\n" +
                        "Civil Engineering is the art and science of designing and constructing " +
                        "infrastructure that serves society. From bridges and buildings to roads and " +
                        "water systems, civil engineers create the foundation of modern civilization.\n\n" +
                        "**🎯 What You'll Learn:**\n" +
                        "• Structural Analysis & Design\n" +
                        "• Geotechnical Engineering & Soil Mechanics\n" +
                        "• Transportation & Traffic Engineering\n" +
                        "• Environmental Engineering & Sustainability\n" +
                        "• Construction Management & Project Planning\n" +
                        "• Water Resources & Hydraulics\n\n" +
                        "**💼 Top Career Paths:**\n" +
                        "• Structural Engineer (Avg. Salary: $95,000)\n" +
                        "• Transportation Engineer (Avg. Salary: $90,000)\n" +
                        "• Environmental Engineer (Avg. Salary: $88,000)\n" +
                        "• Geotechnical Engineer (Avg. Salary: $92,000)\n" +
                        "• Construction Manager (Avg. Salary: $105,000)\n\n" +
                        "**🏢 Top Employers:** AECOM, Jacobs, Bechtel, WSP, HNTB\n" +
                        "**📈 Job Growth:** 8% (As fast as average)\n" +
                        "**🎓 Our Success:** Graduates worked on Golden Gate Bridge retrofit & Dubai sustainable city");

        // Career images (you can replace with actual image paths)
        careerImages.put("Computer Engineering", "🚀");
        careerImages.put("Mechanical Engineering", "⚙️");
        careerImages.put("Electrical Engineering", "⚡");
        careerImages.put("Civil Engineering", "🏗️");

        jobGrowthData.put("Computer Eng.", 22);
        jobGrowthData.put("Mechanical Eng.", 9);
        jobGrowthData.put("Electrical Eng.", 7);
        jobGrowthData.put("Civil Eng.", 8);

        careerSkills.put("Computer Engineering", Arrays.asList(
                "Programming (Python, C++, Java)",
                "Machine Learning",
                "Embedded Systems",
                "Computer Architecture",
                "Cybersecurity"
        ));

        careerSkills.put("Mechanical Engineering", Arrays.asList(
                "CAD/CAM (SolidWorks, AutoCAD)",
                "Thermodynamics",
                "Fluid Mechanics",
                "Materials Science",
                "Robotics"
        ));

        careerSkills.put("Electrical Engineering", Arrays.asList(
                "Circuit Design",
                "Power Systems",
                "Control Systems",
                "Signal Processing",
                "Renewable Energy"
        ));

        careerSkills.put("Civil Engineering", Arrays.asList(
                "Structural Analysis",
                "Geotechnical Engineering",
                "Construction Management",
                "Environmental Engineering",
                "Urban Planning"
        ));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CareerGuidanceChatBotUI().createUI();
        });
    }

    public void createUI() {
        JFrame frame = new JFrame("Future Engineers Hub - Career Guidance Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setMinimumSize(new Dimension(1000, 700));
        frame.setLocationRelativeTo(null);

        // Create main container with scroll pane
        JPanel mainContainer = new JPanel(new BorderLayout());

        // Add back button at the top
        JButton backButton = new JButton("← Back to Home");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(PRIMARY_COLOR);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        backButton.addActionListener(e -> {
            frame.dispose();
            HomePageSwing.show();
        });

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        topBar.setBackground(PRIMARY_COLOR);
        topBar.add(backButton);
        mainContainer.add(topBar, BorderLayout.NORTH);

        JScrollPane mainScrollPane = new JScrollPane(mainContainer);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        JPanel navPanel = createNavPanel(mainPanel);

        mainPanel.add(createDashboardPanel(), "Dashboard");
        mainPanel.add(createCareerPathsPanel(), "Career Paths");
        mainPanel.add(createGraphsPanel(), "Job Growth");
        mainPanel.add(createChatbotTab(), "AI Chatbot");

        mainContainer.add(navPanel, BorderLayout.WEST);
        mainContainer.add(mainPanel, BorderLayout.CENTER);

        frame.add(mainScrollPane);
        frame.setVisible(true);
    }

    private JPanel createNavPanel(JPanel mainPanel) {
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(PRIMARY_COLOR);
        navPanel.setPreferredSize(new Dimension(220, 0));
        navPanel.setBorder(new EmptyBorder(20, 10, 20, 10));

        JLabel logoLabel = new JLabel("Future Engineers");
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoLabel.setBorder(new EmptyBorder(0, 0, 30, 0));
        navPanel.add(logoLabel);

        String[] navItems = {"Dashboard", "Career Paths", "Job Growth", "AI Chatbot"};
        String[] navIcons = {"📊", "👨‍💻", "📈", "🤖"};

        for (int i = 0; i < navItems.length; i++) {
            String item = navItems[i];
            JButton navButton = new JButton(navIcons[i] + "  " + item);
            navButton.setForeground(Color.WHITE);
            navButton.setBackground(PRIMARY_COLOR);
            navButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
            navButton.setFocusPainted(false);
            navButton.setBorderPainted(false);
            navButton.setContentAreaFilled(false);
            navButton.setOpaque(true);
            navButton.setBorder(new EmptyBorder(15, 20, 15, 20));
            navButton.setHorizontalAlignment(SwingConstants.LEFT);
            navButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            navButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            // Add hover effect
            navButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    navButton.setBackground(SECONDARY_COLOR);
                }
                public void mouseExited(java.awt.event.MouseEvent e) {
                    navButton.setBackground(PRIMARY_COLOR);
                }
            });

            navButton.addActionListener(e -> {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, item);
            });

            navPanel.add(navButton);
            navPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        return navPanel;
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(new EmptyBorder(30, 40, 40, 40));

        // Header Section
        JPanel headerPanel = new JPanel(new BorderLayout(20, 20));
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel welcomeLabel = new JLabel("🚀 Welcome to Future Engineers Hub");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        welcomeLabel.setForeground(PRIMARY_COLOR);
        welcomeLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        JLabel subtitleLabel = new JLabel("Your Gateway to Engineering Excellence");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitleLabel.setForeground(Color.GRAY);
        subtitleLabel.setBorder(new EmptyBorder(0, 0, 30, 0));

        headerPanel.add(welcomeLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);

        // Hero Image Section
        JPanel heroPanel = new JPanel();
        heroPanel.setLayout(new BoxLayout(heroPanel, BoxLayout.Y_AXIS));
        heroPanel.setBackground(Color.WHITE);
        heroPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
                new EmptyBorder(30, 30, 30, 30)
        ));
        heroPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        heroPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

        JLabel heroImageLabel = new JLabel("🏗️", SwingConstants.CENTER);
        heroImageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        heroImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel heroTitleLabel = new JLabel("Engineering the Future", SwingConstants.CENTER);
        heroTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        heroTitleLabel.setForeground(PRIMARY_COLOR);
        heroTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel heroSubtitleLabel = new JLabel("Discover your path in the world of innovation", SwingConstants.CENTER);
        heroSubtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        heroSubtitleLabel.setForeground(Color.GRAY);
        heroSubtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        heroPanel.add(heroImageLabel);
        heroPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        heroPanel.add(heroTitleLabel);
        heroPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        heroPanel.add(heroSubtitleLabel);

        // Features Section
        JPanel featuresPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        featuresPanel.setBackground(BACKGROUND_COLOR);
        featuresPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        featuresPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

        String[] featureTitles = {"🎯 Career Paths", "📊 Job Trends", "🤖 AI Guidance", "💡 Skill Mapping"};
        String[] featureDescriptions = {
                "Explore detailed engineering career paths with comprehensive information",
                "Analyze job market trends and growth projections",
                "Get personalized career advice from our AI chatbot",
                "Understand skill requirements for each engineering field"
        };
        Color[] featureColors = {PRIMARY_COLOR, SECONDARY_COLOR, ACCENT_COLOR, SUCCESS_COLOR};

        for (int i = 0; i < featureTitles.length; i++) {
            JPanel featureCard = createFeatureCard(featureTitles[i], featureDescriptions[i], featureColors[i]);
            featuresPanel.add(featureCard);
        }

        // Info Section
        JPanel infoPanel = new JPanel(new BorderLayout(20, 20));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
                new EmptyBorder(25, 25, 25, 25)
        ));
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel infoTitleLabel = new JLabel("🌟 Why Choose Future Engineers Hub?");
        infoTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        infoTitleLabel.setForeground(PRIMARY_COLOR);
        infoTitleLabel.setBorder(new EmptyBorder(0, 0, 15, 0));

        JTextArea infoArea = new JTextArea(
                "Our comprehensive platform is designed to guide you through every step of your " +
                        "engineering journey. Whether you're a high school student exploring career options " +
                        "or a current engineering student planning your future, we provide:\n\n" +
                        "• 📚 In-depth career information and industry insights\n" +
                        "• 📈 Real-time job market data and growth projections\n" +
                        "• 🤖 AI-powered career guidance and recommendations\n" +
                        "• 🎯 Personalized skill assessment and development plans\n" +
                        "• 🌍 Global industry connections and networking opportunities\n" +
                        "• 💼 Internship and job placement assistance\n\n" +
                        "Start exploring the navigation menu to discover your ideal engineering path!");
        infoArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        infoArea.setWrapStyleWord(true);
        infoArea.setLineWrap(true);
        infoArea.setEditable(false);
        infoArea.setBackground(Color.WHITE);
        infoArea.setBorder(new EmptyBorder(0, 0, 0, 0));

        infoPanel.add(infoTitleLabel, BorderLayout.NORTH);
        infoPanel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        // Add all components to main panel
        panel.add(headerPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(heroPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(featuresPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(infoPanel);

        return panel;
    }

    private JPanel createFeatureCard(String title, String description, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                new EmptyBorder(20, 20, 20, 20)
        ));
        card.setMaximumSize(new Dimension(300, 150));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(color);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descLabel = new JLabel("<html><div style='width:200px'>" + description + "</div></html>");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descLabel.setForeground(Color.GRAY);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(descLabel);

        return card;
    }

    private JPanel createCareerPathsPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Enhanced Header
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setBackground(BACKGROUND_COLOR);

        JLabel headerLabel = new JLabel("🚀 Explore Engineering Career Paths");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerLabel.setForeground(PRIMARY_COLOR);

        JLabel subtitleLabel = new JLabel("Discover your perfect engineering specialization");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.GRAY);

        headerPanel.add(headerLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);
        panel.add(headerPanel, BorderLayout.NORTH);

        // Main Content with Enhanced Split Pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(280);
        splitPane.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235), 1));
        splitPane.setBackground(BACKGROUND_COLOR);

        // Enhanced Left Panel - Career List
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel listHeaderLabel = new JLabel("📚 Available Careers");
        listHeaderLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        listHeaderLabel.setForeground(PRIMARY_COLOR);
        listHeaderLabel.setBorder(new EmptyBorder(0, 0, 15, 0));
        leftPanel.add(listHeaderLabel, BorderLayout.NORTH);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        careerDescriptions.keySet().forEach(listModel::addElement);

        JList<String> careerList = new JList<>(listModel);
        careerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        careerList.setBackground(Color.WHITE);
        careerList.setBorder(new EmptyBorder(10, 10, 10, 10));
        careerList.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        careerList.setSelectionBackground(SECONDARY_COLOR);
        careerList.setSelectionForeground(Color.WHITE);

        JScrollPane listScrollPane = new JScrollPane(careerList);
        listScrollPane.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235), 1));
        leftPanel.add(listScrollPane, BorderLayout.CENTER);

        // Enhanced Right Panel - Career Details
        JPanel detailsPanel = new JPanel(new BorderLayout(15, 15));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel detailsHeaderLabel = new JLabel("💡 Career Information");
        detailsHeaderLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        detailsHeaderLabel.setForeground(PRIMARY_COLOR);
        detailsHeaderLabel.setBorder(new EmptyBorder(0, 0, 15, 0));

        JTextArea descriptionArea = new JTextArea("Select a career path from the list to view comprehensive information including skills, salary ranges, and career prospects.");
        descriptionArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setBorder(new EmptyBorder(0, 0, 0, 0));

        JScrollPane detailsScrollPane = new JScrollPane(descriptionArea);
        detailsScrollPane.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235), 1));

        detailsPanel.add(detailsHeaderLabel, BorderLayout.NORTH);
        detailsPanel.add(detailsScrollPane, BorderLayout.CENTER);

        // Add selection listener
        careerList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedCareer = careerList.getSelectedValue();
                if (selectedCareer != null) {
                    descriptionArea.setText(careerDescriptions.get(selectedCareer));
                    detailsHeaderLabel.setText("💡 " + selectedCareer);
                }
            }
        });

        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(detailsPanel);
        panel.add(splitPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createGraphsPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Enhanced Header
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setBackground(BACKGROUND_COLOR);

        JLabel headerLabel = new JLabel("📊 Engineering Job Growth Analysis (2024-2034)");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerLabel.setForeground(PRIMARY_COLOR);

        JLabel subtitleLabel = new JLabel("Comprehensive analysis of engineering career prospects and market trends");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.GRAY);

        headerPanel.add(headerLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);
        panel.add(headerPanel, BorderLayout.NORTH);

        // Enhanced Description Panel
        JPanel descPanel = new JPanel(new BorderLayout());
        descPanel.setBackground(Color.WHITE);
        descPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel descHeaderLabel = new JLabel("📈 Market Overview");
        descHeaderLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        descHeaderLabel.setForeground(PRIMARY_COLOR);
        descHeaderLabel.setBorder(new EmptyBorder(0, 0, 15, 0));

        JTextArea descArea = new JTextArea(
                "The engineering field is experiencing unprecedented growth across all disciplines. " +
                        "Computer Engineering leads with 22% projected growth, followed by Civil (8%), " +
                        "Mechanical (9%), and Electrical (7%) engineering. These projections are based " +
                        "on Bureau of Labor Statistics data and industry analysis.\n\n" +
                        "💡 **Key Insights:**\n" +
                        "• Computer Engineering: Highest growth due to AI/ML and cybersecurity demand\n" +
                        "• Mechanical Engineering: Steady growth in renewable energy and automation\n" +
                        "• Electrical Engineering: Growing demand in smart grid and EV technologies\n" +
                        "• Civil Engineering: Infrastructure development and sustainability focus"
        );
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setEditable(false);
        descArea.setBackground(Color.WHITE);
        descArea.setBorder(new EmptyBorder(0, 0, 0, 0));

        descPanel.add(descHeaderLabel, BorderLayout.NORTH);
        descPanel.add(new JScrollPane(descArea), BorderLayout.CENTER);
        panel.add(descPanel, BorderLayout.CENTER);

        // Enhanced Charts Panel with Tabs
        JPanel chartsPanel = new JPanel(new BorderLayout());
        chartsPanel.setBackground(BACKGROUND_COLOR);
        chartsPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JTabbedPane chartTabs = new JTabbedPane();
        chartTabs.setFont(new Font("Segoe UI", Font.BOLD, 14));
        chartTabs.setBackground(BACKGROUND_COLOR);

        // Bar Chart Tab
        JPanel barChartPanel = new JPanel(new BorderLayout());
        barChartPanel.setBackground(BACKGROUND_COLOR);
        barChartPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel barChartLabel = new JLabel("📊 Bar Chart: Job Growth Projections");
        barChartLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        barChartLabel.setForeground(PRIMARY_COLOR);
        barChartLabel.setBorder(new EmptyBorder(0, 0, 15, 0));

        AnimatedBarChart barChart = new AnimatedBarChart(jobGrowthData);
        barChartPanel.add(barChartLabel, BorderLayout.NORTH);
        barChartPanel.add(barChart, BorderLayout.CENTER);

        // Pie Chart Tab
        JPanel pieChartPanel = new JPanel(new BorderLayout());
        pieChartPanel.setBackground(BACKGROUND_COLOR);
        pieChartPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel pieChartLabel = new JLabel("🥧 Pie Chart: Growth Distribution");
        pieChartLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        pieChartLabel.setForeground(PRIMARY_COLOR);
        pieChartLabel.setBorder(new EmptyBorder(0, 0, 15, 0));

        AnimatedPieChart pieChart = new AnimatedPieChart(jobGrowthData);
        pieChartPanel.add(pieChartLabel, BorderLayout.NORTH);
        pieChartPanel.add(pieChart, BorderLayout.CENTER);

        chartTabs.addTab("📊 Bar Chart", barChartPanel);
        chartTabs.addTab("🥧 Pie Chart", pieChartPanel);

        chartsPanel.add(chartTabs, BorderLayout.CENTER);
        panel.add(chartsPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createChatbotTab() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setBackground(BACKGROUND_COLOR);

        JLabel headerLabel = new JLabel("🤖 AI Career Guidance Assistant");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(PRIMARY_COLOR);

        JLabel subtitleLabel = new JLabel("Ask me anything about engineering careers, skills, and job prospects!");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.GRAY);

        headerPanel.add(headerLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);
        panel.add(headerPanel, BorderLayout.NORTH);

        // Chat Area Panel
        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.setBackground(Color.WHITE);
        chatPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chatArea.setBackground(Color.WHITE);
        chatArea.setForeground(Color.BLACK); // Ensure text is black and visible
        chatArea.setCaretColor(Color.BLACK); // Ensure caret is visible
        chatArea.setSelectedTextColor(Color.WHITE); // Selected text color
        chatArea.setSelectionColor(SECONDARY_COLOR); // Selection background color
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setRows(15); // Set reasonable number of rows
        chatArea.setPreferredSize(new Dimension(600, 400)); // Set preferred size
        chatArea.append("🤖 CareerBot: Hello! I'm your AI career guidance assistant specializing in engineering fields.\n\n");
        chatArea.append("💡 I can help with career guidance, skills, job trends, and educational advice.\n\n");
        chatArea.append("Ask me anything about your engineering career journey!\n\n");

        // Ensure text is visible and properly configured
        chatArea.setCaretPosition(0);
        chatArea.setEditable(false);
        chatArea.setFocusable(false); // Prevent focus issues

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235), 1));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(600, 400)); // Match text area size
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling

        chatPanel.add(scrollPane, BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout(15, 0));
        inputPanel.setBackground(BACKGROUND_COLOR);
        inputPanel.setBorder(new EmptyBorder(15, 0, 0, 0)); // Reduced top margin

        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
                new EmptyBorder(12, 15, 12, 15)
        ));
        inputField.setBackground(Color.WHITE);

        JButton sendButton = new JButton("Send");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setBackground(SECONDARY_COLOR);
        sendButton.setForeground(Color.WHITE);
        sendButton.setBorderPainted(false);
        sendButton.setFocusPainted(false);
        sendButton.setOpaque(true);
        sendButton.setPreferredSize(new Dimension(80, 45));
        sendButton.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Add event listeners
        sendButton.addActionListener(e -> sendMessage(inputField, chatArea));
        inputField.addActionListener(e -> sendMessage(inputField, chatArea));

        // Add clear chat button
        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        clearButton.setBackground(WARNING_COLOR);
        clearButton.setForeground(Color.WHITE);
        clearButton.setBorderPainted(false);
        clearButton.setFocusPainted(false);
        clearButton.setOpaque(true);
        clearButton.setPreferredSize(new Dimension(60, 35));

        clearButton.addActionListener(e -> {
            // Clear chat and reset to welcome message
            chatArea.setText("🤖 CareerBot: Hello! I'm your AI career guidance assistant specializing in engineering fields.\n\n");
            chatArea.append("💡 I can help with career guidance, skills, job trends, and educational advice.\n\n");
            chatArea.append("Ask me anything about your engineering career journey!\n\n");
            chatArea.setCaretPosition(0);
        });

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.add(clearButton);
        buttonPanel.add(sendButton);

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.EAST);

        // Add components to main panel
        panel.add(chatPanel, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void sendMessage(JTextField inputField, JTextArea chatArea) {
        String userText = inputField.getText().trim();
        if (!userText.isEmpty()) {
            // Ensure text is visible
            chatArea.setForeground(Color.BLACK);

            // Add user message with clear formatting
            chatArea.append("👤 You: " + userText + "\n");
            inputField.setText("");

            // Add typing indicator
            chatArea.append("\n[Typing...]\n");

            // Force repaint to show the typing indicator
            chatArea.repaint();

            // Use the working Gemini API backend
            new Thread(() -> {
                try {
                    String response = sendToGemini(userText);
                    SwingUtilities.invokeLater(() -> {
                        // Remove typing indicator and add response
                        String currentText = chatArea.getText();
                        int typingIndex = currentText.lastIndexOf("[Typing...]\n");
                        if (typingIndex != -1) {
                            chatArea.replaceRange("", typingIndex, typingIndex + "[Typing...]\n".length());
                        }

                        // Add the AI response with clear formatting
                        chatArea.append("🤖 CareerBot: " + response + "\n\n");

                        // Ensure text is visible
                        chatArea.setForeground(Color.BLACK);

                        // Auto-scroll to bottom
                        chatArea.setCaretPosition(chatArea.getDocument().getLength());

                        // Force repaint
                        chatArea.revalidate();
                        chatArea.repaint();

                        // Debug: print to console
                        System.out.println("Response added: " + response);
                    });
                } catch (Exception e) {
                    SwingUtilities.invokeLater(() -> {
                        // Remove typing indicator
                        String currentText = chatArea.getText();
                        String textWithoutTyping = currentText.replace("[Typing...]\n", "");
                        chatArea.setText(textWithoutTyping);

                        // Add error message
                        chatArea.append("🤖 CareerBot: Sorry, I encountered an error. Please try again.\n\n");

                        // Ensure text is visible
                        chatArea.setForeground(Color.BLACK);

                        // Auto-scroll to bottom
                        chatArea.setCaretPosition(chatArea.getDocument().getLength());

                        // Force repaint
                        chatArea.revalidate();
                        chatArea.repaint();

                        // Debug: print error to console
                        System.out.println("Error occurred: " + e.getMessage());
                    });
                }
            }).start();
        }
    }


    // Gemini API Integration (Fixed with Gson)
    private String sendToGemini(String userMessage) {
        try {
            String API_KEY = "AIzaSyC7l3wysjjSzsuSAHnUGyjfX3mEv91Zy8U";// safer than hardcoding
            if (API_KEY == null || API_KEY.isEmpty()) {
                return "⚠️ Gemini API key not set. Please configure GEMINI_API_KEY.";
            }

            String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + API_KEY;

            // Enhanced prompt for career guidance
            String enhancedPrompt = "You are a career guidance expert specializing in engineering fields. " +
                    "Provide helpful, informative, and encouraging advice about engineering careers, " +
                    "skills, job prospects, and educational paths. Keep responses concise but helpful. " +
                    "User question: " + userMessage;

            String jsonInput = "{"
                    + "\"contents\": [{\"parts\": [{\"text\": \"" + escapeJson(enhancedPrompt) + "\"}]}]"
                    + "}";

            // Send request
            URL url = new URI(API_URL).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(15000);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonInput.getBytes("utf-8"));
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getResponseCode() >= 400 ? conn.getErrorStream() : conn.getInputStream(), "utf-8"));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                responseBuilder.append(line.trim());
            }

            String jsonResponse = responseBuilder.toString();
            System.out.println("Gemini raw response: " + jsonResponse);

            // Parse properly using Gson
            com.google.gson.JsonObject root = com.google.gson.JsonParser.parseString(jsonResponse).getAsJsonObject();
            com.google.gson.JsonArray candidates = root.getAsJsonArray("candidates");
            if (candidates != null && candidates.size() > 0) {
                com.google.gson.JsonObject content = candidates.get(0).getAsJsonObject().getAsJsonObject("content");
                com.google.gson.JsonArray parts = content.getAsJsonArray("parts");
                if (parts != null && parts.size() > 0) {
                    return parts.get(0).getAsJsonObject().get("text").getAsString();
                }
            }

            return "⚠️ Gemini did not return a valid answer.";

        } catch (Exception e) {
            e.printStackTrace();
            return "⚠️ Sorry, I couldn’t connect to Gemini. Please try again.";
        }
    }

    private String escapeJson(String str) {
        return str.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    class AnimatedBarChart extends JPanel {
        private final Map<String, Integer> data;
        private final Map<String, Integer> currentBarHeights = new LinkedHashMap<>();
        private javax.swing.Timer timer;
        private boolean animationStarted = false;

        public AnimatedBarChart(Map<String, Integer> data) {
            this.data = data;
            data.keySet().forEach(key -> currentBarHeights.put(key, 0));
            setBackground(BACKGROUND_COLOR);
            setPreferredSize(new Dimension(800, 400));

            // Start animation immediately
            startAnimation();
        }

        private void startAnimation() {
            // Reset heights
            data.keySet().forEach(key -> currentBarHeights.put(key, 0));

            timer = new javax.swing.Timer(100, e -> {
                boolean allBarsMaxed = true;
                for (String key : data.keySet()) {
                    int targetHeight = data.get(key);
                    int currentHeight = currentBarHeights.get(key);
                    if (currentHeight < targetHeight) {
                        allBarsMaxed = false;
                        currentBarHeights.put(key, currentHeight + 1);
                    }
                }
                repaint();
                if (allBarsMaxed) {
                    timer.stop();
                }
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int barWidth = 100;
            int spacing = 80;
            int x = 120;
            int chartHeight = getHeight() - 180;
            int maxValue = data.values().stream().mapToInt(v -> v).max().orElse(1);

            // Draw chart title
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 18));
            g2d.setColor(PRIMARY_COLOR);
            g2d.drawString("Projected Job Growth (%)", 50, 35);

            // Draw grid lines
            g2d.setColor(new Color(229, 231, 235));
            g2d.setStroke(new BasicStroke(1));
            for (int i = 0; i <= 5; i++) {
                int y = getHeight() - 70 - (i * chartHeight / 5);
                g2d.drawLine(100, y, getWidth() - 50, y);

                // Draw grid labels
                g2d.setColor(Color.GRAY);
                g2d.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                int value = (i * maxValue) / 5;
                g2d.drawString(value + "%", 70, y + 4);
                g2d.setColor(new Color(229, 231, 235));
            }

            // Draw bars with enhanced styling
            Color[] barColors = {PRIMARY_COLOR, SECONDARY_COLOR, ACCENT_COLOR, SUCCESS_COLOR};
            int colorIndex = 0;

            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                int barValue = currentBarHeights.get(entry.getKey());
                int barHeight = (int) (((double) barValue / maxValue) * chartHeight);

                // Draw bar with gradient effect
                Color barColor = barColors[colorIndex % barColors.length];
                g2d.setColor(barColor);
                g2d.fillRoundRect(x, getHeight() - barHeight - 70, barWidth, barHeight, 20, 20);

                // Draw bar border
                g2d.setColor(barColor.darker());
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(x, getHeight() - barHeight - 70, barWidth, barHeight, 20, 20);

                // Draw value on top of bar
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Segoe UI", Font.BOLD, 14));
                String valueText = barValue + "%";
                int textWidth = g2d.getFontMetrics().stringWidth(valueText);
                g2d.drawString(valueText, x + (barWidth - textWidth) / 2, getHeight() - barHeight - 75);

                // Draw career name below bar
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Segoe UI", Font.BOLD, 12));
                String careerName = entry.getKey();
                int nameWidth = g2d.getFontMetrics().stringWidth(careerName);
                g2d.drawString(careerName, x + (barWidth - nameWidth) / 2, getHeight() - 50);

                x += barWidth + spacing;
                colorIndex++;
            }
        }
    }

    class AnimatedPieChart extends JPanel {
        private final Map<String, Integer> data;
        private final Map<String, Double> currentAngles = new LinkedHashMap<>();
        private javax.swing.Timer timer;
        private final Color[] colors = {PRIMARY_COLOR, SECONDARY_COLOR, ACCENT_COLOR, SUCCESS_COLOR};

        public AnimatedPieChart(Map<String, Integer> data) {
            this.data = data;
            data.keySet().forEach(key -> currentAngles.put(key, 0.0));
            setBackground(BACKGROUND_COLOR);
            setPreferredSize(new Dimension(600, 500));

            // Start animation immediately
            startAnimation();
        }

        private void startAnimation() {
            // Reset angles
            data.keySet().forEach(key -> currentAngles.put(key, 0.0));

            timer = new javax.swing.Timer(100, e -> {
                boolean allSlicesMaxed = true;
                double totalValue = data.values().stream().mapToDouble(v -> v).sum();

                for (String key : data.keySet()) {
                    double targetAngle = (data.get(key) / totalValue) * 360.0;
                    double currentAngle = currentAngles.get(key);
                    if (currentAngle < targetAngle) {
                        allSlicesMaxed = false;
                        currentAngles.put(key, currentAngle + 1.0);
                    }
                }
                repaint();
                if (allSlicesMaxed) {
                    timer.stop();
                }
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = Math.min(centerX, centerY) - 80;

            g2d.setFont(new Font("Segoe UI", Font.BOLD, 16));
            g2d.setColor(PRIMARY_COLOR);
            g2d.drawString("Job Growth Distribution", centerX - 80, 30);

            double startAngle = 90; // Start from top
            int colorIndex = 0;

            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                double currentAngle = currentAngles.get(entry.getKey());
                double sweepAngle = (entry.getValue() / data.values().stream().mapToInt(v -> v).sum()) * 360.0;

                if (currentAngle > 0) {
                    // Draw pie slice
                    g2d.setColor(colors[colorIndex % colors.length]);
                    g2d.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2,
                            (int) startAngle, (int) currentAngle);

                    // Draw slice border
                    g2d.setColor(Color.WHITE);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawArc(centerX - radius, centerY - radius, radius * 2, radius * 2,
                            (int) startAngle, (int) currentAngle);

                    // Draw label
                    double labelAngle = startAngle + (currentAngle / 2);
                    double labelRadius = radius + 30;
                    int labelX = centerX + (int) (labelRadius * Math.cos(Math.toRadians(labelAngle)));
                    int labelY = centerY - (int) (labelRadius * Math.sin(Math.toRadians(labelAngle)));

                    g2d.setColor(Color.BLACK);
                    g2d.drawString(entry.getKey(), labelX - 20, labelY + 5);
                    g2d.drawString(entry.getValue() + "%", labelX - 15, labelY + 20);

                    startAngle += currentAngle;
                }
                colorIndex++;
            }

            // Draw legend
            drawLegend(g2d, centerX + radius + 20, centerY - 100);
        }

        private void drawLegend(Graphics2D g2d, int x, int y) {
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 14));
            g2d.setColor(PRIMARY_COLOR);
            g2d.drawString("Legend:", x, y);

            int colorIndex = 0;
            for (String key : data.keySet()) {
                y += 25;
                g2d.setColor(colors[colorIndex % colors.length]);
                g2d.fillRect(x, y - 10, 15, 15);
                g2d.setColor(Color.BLACK);
                g2d.drawString(key, x + 20, y + 2);
                colorIndex++;
            }
        }
    }
}


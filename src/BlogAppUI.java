package src;

import java.awt.*;
import java.util.List;
import javax.swing.*;

public class BlogAppUI extends JFrame {

    private JTextField titleField, authorField, deleteField;
    private JComboBox<String> categoryBox;
    private JTextArea contentArea;
    private JTextArea displayArea;

    public BlogAppUI() {
        setTitle("Blogging Platform");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Main input panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add blog panel
        JPanel addPanel = new JPanel(new GridBagLayout());
        addPanel.setBorder(BorderFactory.createTitledBorder("Add New Blog"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        addPanel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        titleField = new JTextField(20);
        addPanel.add(titleField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        addPanel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        authorField = new JTextField(20);
        addPanel.add(authorField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        addPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        categoryBox = new JComboBox<>(new String[]{"Technology", "Travel", "Food", "Lifestyle"});
        addPanel.add(categoryBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        addPanel.add(new JLabel("Content:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1.0; gbc.weighty = 1.0;
        contentArea = new JTextArea(4, 20);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        addPanel.add(new JScrollPane(contentArea), gbc);

        // Button panel for add section
        JPanel addButtonPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Save Blog");
        JButton viewButton = new JButton("View All Blogs");
        addButtonPanel.add(saveButton);
        addButtonPanel.add(viewButton);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weighty = 0;
        addPanel.add(addButtonPanel, gbc);

        // Delete panel
        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deletePanel.setBorder(BorderFactory.createTitledBorder("Delete Blog"));
        deletePanel.add(new JLabel("Enter Title:"));
        deleteField = new JTextField(15);
        deletePanel.add(deleteField);
        JButton deleteButton = new JButton("Delete");
        deletePanel.add(deleteButton);

        // Control panel combining add and delete
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(addPanel, BorderLayout.CENTER);
        controlPanel.add(deletePanel, BorderLayout.SOUTH);

        mainPanel.add(controlPanel, BorderLayout.NORTH);

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        displayArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Blog List"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        saveButton.addActionListener(e -> saveBlog());
        viewButton.addActionListener(e -> showBlogs());
        deleteButton.addActionListener(e -> deleteBlog());
        
        setLocationRelativeTo(null);
    }

    private void saveBlog() {
        String title = titleField.getText();
        String author = authorField.getText();
        String category = (String) categoryBox.getSelectedItem();
        String content = contentArea.getText();

        if (title.isEmpty() || author.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        Blog blog = new Blog(title, author, category, content);
        BlogManager.saveBlog(blog);

        JOptionPane.showMessageDialog(this, "Blog saved successfully!");
        titleField.setText("");
        authorField.setText("");
        contentArea.setText("");
    }

    private void showBlogs() {
        List<Blog> blogs = BlogManager.getAllBlogs();
        displayArea.setText("");
        if (blogs.isEmpty()) {
            displayArea.setText("No blogs found.");
            return;
        }

        for (int i = 0; i < blogs.size(); i++) {
            Blog b = blogs.get(i);
            displayArea.append((i + 1) + ". Title: " + b.getTitle() + "\n");
            displayArea.append("   Author: " + b.getAuthor() + "\n");
            displayArea.append("   Category: " + b.getCategory() + "\n");
            displayArea.append("   Content: " + b.getContent() + "\n");
            displayArea.append("---------------------------------\n");
        }
    }

    private void deleteBlog() {
        String title = deleteField.getText().trim();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter blog title to delete!");
            return;
        }

        BlogManager.deleteBlog(title);
        JOptionPane.showMessageDialog(this, "Blog deleted successfully!");
        deleteField.setText("");
        showBlogs();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BlogAppUI().setVisible(true);
        });
    }
}

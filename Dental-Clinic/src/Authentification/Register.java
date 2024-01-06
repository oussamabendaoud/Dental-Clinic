
package Authentification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField fullNameField;
    private JTextField emailField;

    public Register() {
        // Configuration de la fenêtre
        setTitle("Dental Clinic - Registration");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre

        // Création des composants
        ImageIcon logoIcon = new ImageIcon("dental_logo.png");  // Remplacez "dental_logo.png" par le chemin de votre propre fichier image
        JLabel logoLabel = new JLabel(logoIcon);

        JLabel titleLabel = new JLabel("Registration - Dental Clinic");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204)); // Couleur bleu foncé

        JLabel usernameLabel = createStyledLabel("Username:");
        JLabel passwordLabel = createStyledLabel("Password:");
        JLabel fullNameLabel = createStyledLabel("Full Name:");
        JLabel emailLabel = createStyledLabel("Email:");
        JButton registerButton = new JButton("Register");

        // Ajout d'un lien "Already registered? Login here" en anglais
        JLabel alreadyRegisteredLabel = new JLabel("<html><u>Already registered? Login here</u></html>");
        alreadyRegisteredLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        alreadyRegisteredLabel.setForeground(new Color(50, 100, 255));
        alreadyRegisteredLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        alreadyRegisteredLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openLoginPage();
            }
        });

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        fullNameField = new JTextField();
        emailField = new JTextField();

        // Ajout du panneau principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(255, 255, 255)); // Couleur de fond blanc

        // Ajout des composants au panneau principal
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(logoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 10, 0);
        mainPanel.add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 8, 8);
        mainPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        mainPanel.add(fullNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(fullNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        mainPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        mainPanel.add(registerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 0, 0, 0);
        mainPanel.add(alreadyRegisteredLabel, gbc);

        // Ajout du panneau avec l'ombre
        JPanel shadowPanel = new JPanel();
        shadowPanel.setBackground(new Color(230, 230, 230)); // Couleur de fond gris clair
        shadowPanel.setLayout(new BorderLayout());
        shadowPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 5, true)); // Bordure arrondie
        shadowPanel.add(mainPanel, BorderLayout.CENTER);

        // Ajout du panneau avec l'ombre à la fenêtre
        add(shadowPanel);

        // Ajout d'un gestionnaire d'événements pour le bouton d'inscription
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action à effectuer lors de la tentative d'inscription
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                String fullName = fullNameField.getText();
                String email = emailField.getText();
                // Vous pouvez ajouter ici la logique d'inscription
                // Pour l'exemple, affichons simplement les informations dans la console
                System.out.println("Username: " + username);
                System.out.println("Password: " + new String(password));
                System.out.println("Full Name: " + fullName);
                System.out.println("Email: " + email);
            }
        });

        // Affichage de la fenêtre
        setVisible(true);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16)); // Police régulière, taille 16
        label.setForeground(new Color(70, 70, 70)); // Couleur de police gris foncé
        return label;
    }

    // Méthode pour ouvrir la page de connexion
    private void openLoginPage() {
        // Fermer la fenêtre d'inscription
        dispose();

        // Ouvrir la fenêtre de connexion
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Register();
            }
        });
    }
}

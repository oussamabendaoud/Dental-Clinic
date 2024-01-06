package view;
import javax.swing.table.DefaultTableModel;

import Dao.PatientDao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PatientGUI extends JFrame {
    private final PatientDao patientDao;
    private final JTextField idField;
    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JTextField ageField;
    private final JTextField sexeField;
    private final DefaultTableModel tableModel;
    private final JTable table;

    public PatientGUI() {
        // Initialisation du DAO pour les patients
        patientDao = new PatientDao("MyFileBase/patients.txt");

        // Configuration de la fenêtre principale
        setTitle("Patient Information");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuration du panneau de formulaire avec GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Initialisation des champs de texte
        idField = new JTextField(10);
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        ageField = new JTextField(10);
        sexeField = new JTextField(10);

        // Initialisation des boutons
        JButton addButton = new JButton("Add Patient");
        JButton updateButton = new JButton("Update Patient");
        JButton deleteButton = new JButton("Delete Patient");

        // Ajout des composants au panneau de formulaire avec GridBagLayout
        addToFormPanel(formPanel, new JLabel("ID:"), idField, gbc);
        addToFormPanel(formPanel, new JLabel("First Name:"), firstNameField, gbc);
        addToFormPanel(formPanel, new JLabel("Last Name:"), lastNameField, gbc);
        addToFormPanel(formPanel, new JLabel("Age:"), ageField, gbc);
        addToFormPanel(formPanel, new JLabel("Sexe (M/F):"), sexeField, gbc);

        // Ajout d'un espace flexible pour centrer les composants
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        formPanel.add(Box.createVerticalGlue(), gbc);

        // Ajout des boutons au panneau de formulaire avec GridBagLayout
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        formPanel.add(addButton, gbc);

        gbc.gridx++;
        formPanel.add(updateButton, gbc);

        gbc.gridx++;
        formPanel.add(deleteButton, gbc);

        // Ajout du panneau de formulaire à la partie supérieure de la fenêtre
        add(formPanel, BorderLayout.NORTH);

        // Configuration de la table pour afficher les patients
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Sexe");

        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Ajout de la table à la partie inférieure de la fenêtre
        add(tableScrollPane, BorderLayout.CENTER);

        // Ajout des écouteurs d'événements aux boutons
        addButton.addActionListener(e -> addPatient());
        updateButton.addActionListener(e -> updatePatient());
        deleteButton.addActionListener(e -> deletePatient());

        // Actualisation des données du tableau
        refreshTableData();

        setVisible(true);
    }

    // Méthode utilitaire pour ajouter des composants au panneau de formulaire avec GridBagLayout
    private void addToFormPanel(JPanel panel, JComponent label, JComponent field, GridBagConstraints gbc) {
        panel.add(label, gbc);
        gbc.gridx++;
        panel.add(field, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
    }

    // Méthode pour ajouter un patient
    private void addPatient() {
        String id = idField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String age = ageField.getText();
        String sexe = sexeField.getText();

        String[] patientData = {id, firstName, lastName, age, sexe};
        patientDao.addPatient(patientData);

        // Actualisation des données du tableau
        refreshTableData();

        // Actualisation des données du formulaire
        clearFormFields();
    }



    // Méthode pour mettre à jour un patient
    private void updatePatient() {
        // Logique de mise à jour du patient ici
        // ...

        // Actualisation des données du tableau
        refreshTableData();

        // Actualisation des données du formulaire
        clearFormFields();
    }

    // Méthode pour supprimer un patient
    private void deletePatient() {
        // Vérifiez si le champ ID est vide
        if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un ID à supprimer.");
            return;
        }

        // Obtenez l'ID à supprimer
        int idToDelete;
        try {
            idToDelete = Integer.parseInt(idField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un ID valide.");
            return;
        }

        // Appelez votre DAO pour supprimer le patient
        patientDao.deletePatient(idToDelete);

        // Rafraîchissez les données du tableau
        refreshTableData();
    }



    // Méthode pour effacer les champs du formulaire
    private void clearFormFields() {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        ageField.setText("");
        sexeField.setText("");
    }

    // Méthode pour actualiser les données du tableau
    private void refreshTableData() {
        // Effacer le contenu actuel du tableau
        tableModel.setRowCount(0);

        // Charger les données depuis le DAO
        List<String[]> patients = patientDao.getAllPatients();

        // Remplir le tableau avec les données des patients
        for (String[] patient : patients) {
            tableModel.addRow(patient);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PatientGUI();
        });
    }
}

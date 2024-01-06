package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Dashboard extends JFrame {
    private JList<Patient> patientList;

    // pour afficher la liste des patient
    private List<Patient> patients = new ArrayList<>();

    private JComboBox<Patient> patientsDropdown;
    private JTextArea patientDetailsTextArea;

    private JList<MedicalRecord> medicalRecordsList;
    private JTextField medicalRecordsSearchField;

    private JList<Invoice> invoicesList;
    private JButton generateInvoiceButton;
    private JButton regenerateInvoiceButton;

    private JTextArea medicalCertificatesTextArea;
    private JButton createMedicalCertificateButton;

    public Dashboard() {
        // Configuration de la fenêtre
        setTitle("Dental Clinic - Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre

        // Création des onglets
        JTabbedPane tabbedPane = new JTabbedPane();

        // Onglet "Patients"
        JPanel patientsPanel = createPatientsPanel();
        tabbedPane.addTab("Patients", patientsPanel);

        // Onglet "Medical Records"
        JPanel medicalRecordsPanel = createMedicalRecordsPanel();
        tabbedPane.addTab("Medical Records", medicalRecordsPanel);

        // Onglet "Invoices"
        JPanel invoicesPanel = createInvoicesPanel();
        tabbedPane.addTab("Invoices", invoicesPanel);

        // Onglet "Medical Certificates"
        JPanel medicalCertificatesPanel = createMedicalCertificatesPanel();
        tabbedPane.addTab("Medical Certificates", medicalCertificatesPanel);

        // Ajout des onglets à la fenêtre
        add(tabbedPane);

        // Affichage de la fenêtre
        setVisible(true);
    }
    private JPanel createPatientsPanel() {
        // Mise en place du panneau "Patients"
        JPanel patientsPanel = new JPanel(new BorderLayout());
        patientsDropdown = new JComboBox<>(new Patient[]{
                new Patient(1,"Yassir", "chadmi", "123-456-7890", "123 Main St", "Male", "123456789", "Yassir@example.com", 24),
                new Patient(2,"Oussama", "bendaoud", "987-654-3210", "456 Oak St", "Male", "987654321", "Oussama@example.com", 30),
                // Ajouter plus de patients si nécessaire
        });

        patientsDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePatientDetails((Patient) patientsDropdown.getSelectedItem());
            }
        });

        patientDetailsTextArea = new JTextArea();
        patientDetailsTextArea.setEditable(false);

        JPanel patientsTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        patientsTopPanel.add(new JLabel("Select Patient: "));
        patientsTopPanel.add(patientsDropdown);

        JButton addPatientButton = new JButton("Add Patient");
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });
        patientsTopPanel.add(addPatientButton);

        JButton editPatientButton = new JButton("Edit Patient");
        editPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPatient();
            }
        });
        patientsTopPanel.add(editPatientButton);

        JButton deletePatientButton = new JButton("Delete Patient");
        deletePatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePatient();
            }
        });
        patientsTopPanel.add(deletePatientButton);

        patientsPanel.add(patientsTopPanel, BorderLayout.NORTH);
        patientsPanel.add(new JScrollPane(patientDetailsTextArea), BorderLayout.CENTER);

        return patientsPanel;
    }
    private void addPatient() {
        showAddPatientDialog();

    }

    private void showAddPatientDialog() {
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField phoneNumberField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField cinField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField ageField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneNumberField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderField);
        panel.add(new JLabel("CIN:"));
        panel.add(cinField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Patient",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // L'utilisateur a appuyé sur OK, vous pouvez maintenant récupérer les valeurs et ajouter un nouveau patient
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String address = addressField.getText();
            String gender = genderField.getText();
            String cin = cinField.getText();
            String email = emailField.getText();
            int age = Integer.parseInt(ageField.getText()); // Assume que l'âge est un entier

            // Générez un nouvel ID (à remplacer par votre propre logique de génération d'ID)
            int generatedId = generateUniqueId();

            // Créez un nouvel objet Patient avec les informations saisies et l'ID généré
            Patient newPatient = new Patient(generatedId, firstName, lastName, phoneNumber, address, gender, cin, email, age);

            // Ajoutez le nouveau patient à votre liste de patients
            // Vous pouvez également mettre à jour l'interface graphique en conséquence
            // Assurez-vous d'avoir une liste de patients (peut être un ArrayList, une liste chaînée, etc.)
            // et ajoutez le nouveau patient à cette liste.
            // Exemple avec une liste fictive :
            // patientList.add(newPatient);
        }
    }

    // Méthode de génération d'ID (exemple)
    private int generateUniqueId() {
        // Remplacez cela par votre propre logique de génération d'ID
        // Cela peut être une séquence, une logique basée sur la base de données, etc.
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }




    private void editPatient() {
        // Récupérer le patient sélectionné
        Patient selectedPatient = (Patient) patientsDropdown.getSelectedItem();

        // Vérifier si un patient est sélectionné
        if (selectedPatient != null) {
            // Afficher une boîte de dialogue pour modifier les détails du patient
            String newFirstName = JOptionPane.showInputDialog("Enter new first name:", selectedPatient.getFirstName());
            String newLastName = JOptionPane.showInputDialog("Enter new last name:", selectedPatient.getLastName());
            String newPhoneNumber = JOptionPane.showInputDialog("Enter new phone number:", selectedPatient.getPhoneNumber());
            String newAddress = JOptionPane.showInputDialog("Enter new address:", selectedPatient.getAddress());
            String newGender = JOptionPane.showInputDialog("Enter new gender:", selectedPatient.getGender());
            String newCin = JOptionPane.showInputDialog("Enter new CIN:", selectedPatient.getCin());
            String newEmail = JOptionPane.showInputDialog("Enter new email:", selectedPatient.getEmail());
            String newAgeString = JOptionPane.showInputDialog("Enter new age:", String.valueOf(selectedPatient.getAge()));

            // Vérifier si l'utilisateur a appuyé sur Annuler ou a laissé un champ vide
            if (newFirstName != null && newLastName != null && newPhoneNumber != null &&
                    newAddress != null && newGender != null && newCin != null && newEmail != null &&
                    newAgeString != null && !newFirstName.isEmpty() && !newLastName.isEmpty() &&
                    !newPhoneNumber.isEmpty() && !newAddress.isEmpty() && !newGender.isEmpty() &&
                    !newCin.isEmpty() && !newEmail.isEmpty() && !newAgeString.isEmpty()) {

                // Convertir l'âge en entier
                int newAge = Integer.parseInt(newAgeString);

                // Créer le nouveau patient avec les nouvelles informations et l'ID existant
                Patient updatedPatient = new Patient(selectedPatient.getId(), newFirstName, newLastName, newPhoneNumber,
                        newAddress, newGender, newCin, newEmail, newAge);

                // Mettre à jour la liste des patients
                updatePatientList(selectedPatient, updatedPatient);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a patient to edit.");
        }
    }

    // Méthode pour mettre à jour la liste des patients après l'édition
    private void updatePatientList(Patient oldPatient, Patient newPatient) {
        // Obtain the model of the JComboBox (DefaultComboBoxModel)
        DefaultComboBoxModel<Patient> model = (DefaultComboBoxModel<Patient>) patientsDropdown.getModel();

        // Iterate through the model to find the old patient
        for (int i = 0; i < model.getSize(); i++) {
            if (model.getElementAt(i).equals(oldPatient)) {
                // Replace the old patient with the new one
                model.removeElementAt(i);
                model.addElement(newPatient);

                // Select the new patient in the JComboBox
                patientsDropdown.setSelectedItem(newPatient);
                break;  // Exit the loop once the replacement is done
            }
        }

        // Update the GUI with the new patient details
        updatePatientDetails(newPatient);
    }





    private void deletePatient() {
        Patient selectedPatient = (Patient) patientsDropdown.getSelectedItem();

        if (selectedPatient != null) {
            // Demander une confirmation à l'utilisateur avant de supprimer
            int confirmDialogResult = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete the selected patient?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);

            if (confirmDialogResult == JOptionPane.YES_OPTION) {
                // Obtenez le modèle du JComboBox (DefaultComboBoxModel)
                DefaultComboBoxModel<Patient> model = (DefaultComboBoxModel<Patient>) patientsDropdown.getModel();

                // Supprimez le patient sélectionné du modèle
                model.removeElement(selectedPatient);

                // Mise à jour de l'interface graphique
                updatePatientDetails(null); // Effacez les détails du patient

                // Affichez un message de confirmation
                JOptionPane.showMessageDialog(this, "Patient deleted successfully.");
            }
        } else {
            // Aucun patient sélectionné, affichez un message d'avertissement
            JOptionPane.showMessageDialog(this, "Please select a patient to delete.");
        }
    }


    private JPanel createMedicalRecordsPanel() {
        JPanel medicalRecordsPanel = new JPanel(new BorderLayout());

        // Créer des dossiers médicaux fictifs
        java.util.List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(new MedicalRecord((Patient) patientsDropdown.getItemAt(0), "Dental checkup", "None", new Date()));
        medicalRecords.add(new MedicalRecord((Patient) patientsDropdown.getItemAt(1), "Tooth extraction", "Painkillers", new Date()));

        // Liste de dossiers médicaux
        medicalRecordsList = new JList<>(medicalRecords.toArray(new MedicalRecord[0]));
        medicalRecordsList.setCellRenderer(new MedicalRecordListCellRenderer());

        // Champ de recherche
        medicalRecordsSearchField = new JTextField();
        medicalRecordsSearchField.setColumns(20);

        JPanel medicalRecordsTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        medicalRecordsTopPanel.add(new JLabel("Search Medical Records: "));
        medicalRecordsTopPanel.add(medicalRecordsSearchField);

        medicalRecordsPanel.add(medicalRecordsTopPanel, BorderLayout.NORTH);
        medicalRecordsPanel.add(new JScrollPane(medicalRecordsList), BorderLayout.CENTER);

        return medicalRecordsPanel;
    }


    private JPanel createInvoicesPanel() {
        JPanel invoicesPanel = new JPanel(new BorderLayout());

        // Créer des factures fictives
        java.util.List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice(1, new Date(), (Patient) patientsDropdown.getItemAt(0), 100.0));
        invoices.add(new Invoice(2, new Date(), (Patient) patientsDropdown.getItemAt(1), 150.0));

        // Liste de factures
        invoicesList = new JList<>(invoices.toArray(new Invoice[0]));
        invoicesList.setCellRenderer(new InvoiceListCellRenderer());

        // Bouton de génération de facture
        generateInvoiceButton = new JButton("Generate Invoice");
        generateInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Dashboard.this, "Generating Invoice...");
            }
        });

        // Bouton de régénération de facture
        regenerateInvoiceButton = new JButton("Regenerate Invoice");
        regenerateInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regenerateInvoice();
            }
        });

        JPanel invoicesTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        invoicesTopPanel.add(new JLabel("Invoices"));
        invoicesTopPanel.add(generateInvoiceButton);
        invoicesTopPanel.add(regenerateInvoiceButton);

        invoicesPanel.add(invoicesTopPanel, BorderLayout.NORTH);
        invoicesPanel.add(new JScrollPane(invoicesList), BorderLayout.CENTER);

        return invoicesPanel;
    }

    private JPanel createMedicalCertificatesPanel() {
        JPanel medicalCertificatesPanel = new JPanel(new BorderLayout());

        // Certificats médicaux fictifs
        medicalCertificatesTextArea = new JTextArea();
        medicalCertificatesTextArea.setEditable(false);
        updateMedicalCertificates();

        // Bouton de création de certificat médical
        createMedicalCertificateButton = new JButton("Create Medical Certificate");
        createMedicalCertificateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMedicalCertificate();
            }
        });

        JPanel medicalCertificatesTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        medicalCertificatesTopPanel.add(new JLabel("Medical Certificates"));
        medicalCertificatesTopPanel.add(createMedicalCertificateButton);

        medicalCertificatesPanel.add(medicalCertificatesTopPanel, BorderLayout.NORTH);
        medicalCertificatesPanel.add(new JScrollPane(medicalCertificatesTextArea), BorderLayout.CENTER);

        return medicalCertificatesPanel;
    }

    private class MedicalRecordListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof MedicalRecord) {
                MedicalRecord medicalRecord = (MedicalRecord) value;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String displayText = medicalRecord.getPatient().getFullName() +
                        " - " + medicalRecord.getDiagnosis() +
                        " - " + dateFormat.format(medicalRecord.getDate());
                setText(displayText);
            }

            return this;
        }
    }

    private class InvoiceListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Invoice) {
                Invoice invoice = (Invoice) value;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String displayText = "Invoice #" + invoice.getInvoiceNumber() +
                        " - Date: " + dateFormat.format(invoice.getIssueDate()) +
                        " - Amount: $" + invoice.getTotalAmount();
                setText(displayText);
            }

            return this;
        }
    }

    private void updateMedicalCertificates() {
        // Certificats médicaux fictifs
        StringBuilder medicalCertificatesText = new StringBuilder();
        medicalCertificatesText.append("Medical Certificates:\n\n");
        medicalCertificatesText.append("1. Certificate for John Doe\n");
        medicalCertificatesText.append("   - Date: 2022-01-01\n");
        medicalCertificatesText.append("   - Reason: General Health\n\n");
        medicalCertificatesText.append("2. Certificate for Jane Smith\n");
        medicalCertificatesText.append("   - Date: 2022-02-15\n");
        medicalCertificatesText.append("   - Reason: Dental Procedure\n");

        medicalCertificatesTextArea.setText(medicalCertificatesText.toString());
    }
    private void createMedicalCertificate() {
        // Récupérer le patient sélectionné
        Patient selectedPatient = (Patient) patientsDropdown.getSelectedItem();

        // Vérifier si un patient est sélectionné
        if (selectedPatient != null) {
            // Afficher une boîte de dialogue pour obtenir la raison du certificat médical
            String reason = JOptionPane.showInputDialog("Enter the reason for the medical certificate:");

            // Vérifier si l'utilisateur a appuyé sur Annuler ou a laissé le champ vide
            if (reason != null && !reason.isEmpty()) {
                // Formatage du certificat médical avec les détails du patient et la raison spécifiée
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String newCertificate = "Medical Certificate for " + selectedPatient.getFullName() + "\n" +
                        "   - Date: " + dateFormat.format(new Date()) + "\n" +
                        "   - Reason: " + reason + "\n\n";

                // Ajouter le nouveau certificat médical aux existants
                medicalCertificatesTextArea.append(newCertificate);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a patient to create a medical certificate.");
        }
    }

    private void updatePatientDetails(Patient selectedPatient) {
        // Mise en œuvre de la mise à jour des détails du patient
        if (selectedPatient != null) {
            patientDetailsTextArea.setText("Details for " + selectedPatient.getFullName() +
                    "\nAge: " + selectedPatient.getAge() +
                    "\nPhone Number: " + selectedPatient.getPhoneNumber() +
                    "\nAddress: " + selectedPatient.getAddress() +
                    "\nGender: " + selectedPatient.getGender() +
                    "\nCIN: " + selectedPatient.getCin() +
                    "\nEmail: " + selectedPatient.getEmail());
        }

        // Mise en œuvre de la mise à jour de la liste des dossiers médicaux
        java.util.List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(new MedicalRecord(selectedPatient, "Dental checkup", "None", new Date()));
        medicalRecords.add(new MedicalRecord(selectedPatient, "Tooth extraction", "Painkillers", new Date()));
        medicalRecordsList.setListData(medicalRecords.toArray(new MedicalRecord[0]));

        // Mise en œuvre de la mise à jour de la liste des factures
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice(1, new Date(), selectedPatient, 100.0));
        invoices.add(new Invoice(2, new Date(), selectedPatient, 150.0));
        invoicesList.setListData(invoices.toArray(new Invoice[0]));

        // Mise en œuvre de la mise à jour de la zone de texte des certificats médicaux
        updateMedicalCertificates();
    }

    private void regenerateInvoice() {
        // Ajoutez ici la logique pour régénérer une facture fictive
        // Pour l'exemple, nous allons simplement créer une nouvelle facture avec une date actuelle et un montant aléatoire
        Patient selectedPatient = (Patient) patientsDropdown.getSelectedItem();
        if (selectedPatient != null) {
            int newInvoiceNumber = invoicesList.getModel().getSize() + 1;
            double newTotalAmount = Math.random() * 1000; // Montant aléatoire pour l'exemple

            Invoice newInvoice = new Invoice(newInvoiceNumber, new Date(), selectedPatient, newTotalAmount);

            // Ajoutez la nouvelle facture à la liste existante
            DefaultListModel<Invoice> model = (DefaultListModel<Invoice>) invoicesList.getModel();
            model.addElement(newInvoice);

            JOptionPane.showMessageDialog(this, "Invoice regenerated for " + selectedPatient.getFullName());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a patient first.");
        }
    }

    private void initializePatientList() {
        // Create the model for the patient list
        DefaultListModel<Patient> patientListModel = new DefaultListModel<>();
        patientList = new JList<>(patientListModel);

        // Set up the list selection listener
        patientList.addListSelectionListener(e -> {
            Patient selectedPatient = patientList.getSelectedValue();
            updatePatientDetails(selectedPatient);
        });

        // Add the patient list to your GUI
        JPanel patientsListPanel = new JPanel(new BorderLayout());
        patientsListPanel.add(new JScrollPane(patientList), BorderLayout.CENTER);

        // Add patientsListPanel to your "Patients" tab
        // ...

        // (other parts of the patient list initialization)
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Dashboard();
            }
        });
    }
}

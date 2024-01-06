package Dao;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import view.Patient;

public class PatientDao {

    private List<Patient> patients = new ArrayList<>();

    private String filePath;

    public PatientDao(String filePath) {
        this.filePath = filePath;
        initializeFile();

    }

    public List<String[]> getAllPatients() {
        List<String[]> patients = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] patientData = line.split(",");
                patients.add(patientData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return patients;
    }



    private void initializeFile() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> findAll() {
        List<String[]> patients = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                patients.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return patients;
    }

    public void addPatient(String[] patientData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(String.join(",", patientData));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePatient(List<String[]> patients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] patient : patients) {
                writer.write(String.join(",", patient));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(int id) {
        // Use removeIf with a Predicate to remove the element based on the ID
        patients.removeIf(patient -> patient.getId() == id);
    }

}

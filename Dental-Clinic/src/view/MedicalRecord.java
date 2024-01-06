package view;
import java.util.Date;

public class MedicalRecord {
    private Patient patient;
    private String diagnosis;
    private String prescriptions;
    private Date date;

    public MedicalRecord(Patient patient, String diagnosis, String prescriptions, Date date) {
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.prescriptions = prescriptions;
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public Date getDate() {
        return date;
    }
}

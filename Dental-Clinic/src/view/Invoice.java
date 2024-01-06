package view;
import java.util.Date;

public class Invoice {
    private int invoiceNumber;
    private Date issueDate;
    private Patient patient;
    private double totalAmount;

    public Invoice(int invoiceNumber, Date issueDate, Patient patient, double totalAmount) {
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.patient = patient;
        this.totalAmount = totalAmount;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

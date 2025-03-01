import java.util.HashMap;
import java.util.Map;

public class Invoice {
    private int invoiceID;
    private int clientID;
    private Map<Integer, Integer> services; 
    private double totalAmount;

      
      public Invoice(int invoiceID, int clientID) {
        this.invoiceID = invoiceID;
        this.clientID = clientID;
        this.services = new HashMap<>();
        this.totalAmount = 0.0;
    }

    public int getInvoiceID() { 
        return invoiceID;
    }

    public void addService(int serviceID, int hours, double rate) {
        services.put(serviceID, services.getOrDefault(serviceID, 0) + hours);
        calculateTotal(hours * rate); 
    }

    public void calculateTotal(double amount) {
        this.totalAmount += amount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

  

    public int getClientID() {
        return clientID;
    }

    public Map<Integer, Integer> getServices() {
        return services;
    }
}

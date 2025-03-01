public class InvoiceItem {
    private Service service;
    private int hours;

    public InvoiceItem(Service service, int hours) {
        this.service = service;
        this.hours = hours;
    }

    public double getTotalCost() {
        return service.getRate() * hours;
    }

    public String getServiceName() {
        return service.getName();
    }
}

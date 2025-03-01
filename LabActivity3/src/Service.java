public class Service {
    private int serviceId;
    private String serviceName;
    private double rate;

    public Service(String serviceName, double rate) {
        this.serviceId = -1;  
        this.serviceName = serviceName;
        this.rate = rate;
    }

    public Service(int serviceId, String serviceName, double rate) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.rate = rate;
    }

    public String getName() {
        return serviceName;
    }

    public double getRate() {
        return rate;
    }

    public int getId() {
        return serviceId;
    }

    public void setId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "Service ID: " + serviceId + ", Name: " + serviceName + ", Rate: Php" + rate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Service service = (Service) obj;
        return serviceId == service.serviceId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(serviceId);
    }
}

import java.util.List;

public class Client {
    private int clientID;
    private String name;
    private List<Service> services;

    public Client(int clientID, String name, List<Service> services) {
        this.clientID = clientID; 
        this.name = name;
        this.services = services;
    }

    public int getClientID() {
        return clientID;
    }

    public String getName() {
        return name;
    }

    public List<Service> getServices() {
        return services;
    }
}

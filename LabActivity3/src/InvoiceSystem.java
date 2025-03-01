import java.sql.*;
import java.util.*;

public class InvoiceSystem {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Virtual Assistant Invoice System ===");
            System.out.println("0. Exit");
            System.out.println("1. Create New Invoice");
            System.out.println("2. View Invoices by Client");
            System.out.println("3. View All Invoices");
            System.out.println("4. Delete Invoice");
            System.out.println("5. Add Client");
            System.out.println("6. View Clients");
            System.out.println("7. Update Client");
            System.out.println("8. Delete Client");
            System.out.println("9. Add Service");
            System.out.println("10. View Services");
            System.out.println("11. Update Service");
            System.out.println("12. Delete Service");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0 -> {
                    System.out.println("Exiting system...");
                    System.exit(0);
                }

                case 1 -> createInvoice();
                case 2 -> viewInvoicesByClient();
                case 3 -> viewAllInvoices();
                case 4 -> deleteInvoice();
                case 5 -> addClient();
                case 6 -> viewClients();
                case 7 -> updateClient();
                case 8 -> deleteClient();
                case 9 -> addService();
                case 10 -> viewServices();
                case 11 -> updateService();
                case 12 -> deleteService();

                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addClient() {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();

        String insertSQL = "INSERT INTO clients (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            stmt.setString(1, name);
            stmt.executeUpdate();
            System.out.println("Client added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding client.");
        }
    }

    private static void viewClients() {
        String query = "SELECT * FROM clients";
        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n=== Clients ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("client_id") + " | Name: " + rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving clients.");
        }
    }

    private static void updateClient() {
        System.out.print("Enter client ID to update: ");
        int clientID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new client name: ");
        String newName = scanner.nextLine();

        String updateSQL = "UPDATE clients SET name = ? WHERE client_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

            stmt.setString(1, newName);
            stmt.setInt(2, clientID);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(" Client updated successfully!");
            } else {
                System.out.println(" No client found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error updating client.");
        }
    }

    private static void deleteClient() {
        System.out.print("Enter client ID to delete: ");
        int clientID = scanner.nextInt();
        scanner.nextLine();

        String deleteSQL = "DELETE FROM clients WHERE client_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {

            stmt.setInt(1, clientID);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(" Client deleted successfully!");
            } else {
                System.out.println(" No client found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error deleting client.");
        }
    }

    private static void addService() {
        System.out.print("Enter service name: ");
        String name = scanner.nextLine();
        System.out.print("Enter rate per hour: ");
        double rate = scanner.nextDouble();
        scanner.nextLine();

        String insertSQL = "INSERT INTO services (service_name, rate) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            stmt.setString(1, name);
            stmt.setDouble(2, rate);
            stmt.executeUpdate();

            System.out.println("Service added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding service.");
        }
    }

    private static void viewServices() {
        String query = "SELECT * FROM services";
        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n=== Services ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("service_id") + " | Name: " + rs.getString("service_name")
                        + " | Rate: Php" + rs.getDouble("rate") + "/hr");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving services.");
        }
    }

    private static void updateService() {
        System.out.print("Enter service ID to update: ");
        int serviceID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new service name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter new rate: ");
        double newRate = scanner.nextDouble();
        scanner.nextLine();

        String updateSQL = "UPDATE services SET service_name = ?, rate = ? WHERE service_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

            stmt.setString(1, newName);
            stmt.setDouble(2, newRate);
            stmt.setInt(3, serviceID);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(" Service updated successfully!");
            } else {
                System.out.println(" No service found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error updating service.");
        }
    }

    private static void deleteService() {
        System.out.print("Enter service ID to delete: ");
        int serviceID = scanner.nextInt();
        scanner.nextLine();

        String deleteSQL = "DELETE FROM services WHERE service_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {

            stmt.setInt(1, serviceID);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(" Service deleted successfully!");
            } else {
                System.out.println(" No service found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error deleting service.");
        }
    }

    private static void createInvoice() {
        System.out.println("\nCreating new invoice:");
        viewClients();
    
        System.out.print("Enter client ID: ");
        int clientID = scanner.nextInt();
        scanner.nextLine();
    
        double totalAmount = 0.0; 
    
      
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.out.println(" Database connection failed!");
                return;
            }
    
          
            String insertInvoiceSQL = "INSERT INTO invoices (client_id, invoice_date, total) VALUES (?, CURDATE(), ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertInvoiceSQL, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, clientID);
                stmt.setDouble(2, totalAmount);  
                stmt.executeUpdate();
    
          
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                int generatedInvoiceId = -1;
                if (generatedKeys.next()) {
                    generatedInvoiceId = generatedKeys.getInt(1);
                    System.out.println("Generated Invoice ID: " + generatedInvoiceId);
                } else {
                    System.out.println(" Failed to generate invoice ID.");
                    return;
                }
    
                
                Invoice invoice = new Invoice(generatedInvoiceId, clientID);
    
             
                while (true) {
                    System.out.println("\n1. Add Services\n2. Finish Invoice");
                    System.out.print("Enter choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();
    
                    if (choice == 2) break;
    
                    if (choice == 1) {
                        viewServices();
                        System.out.print("Select service ID: ");
                        int serviceID = scanner.nextInt();
                        System.out.print("Enter hours: ");
                        int hours = scanner.nextInt();
                        scanner.nextLine();
    
                        double rate = getServiceRate(serviceID);
                        invoice.addService(serviceID, hours, rate);
                        totalAmount += rate * hours; 
    
                        System.out.println("Service added to invoice.");
                    } else {
                        System.out.println("Invalid choice! Try again.");
                    }
                }
    
               
                String updateInvoiceSQL = "UPDATE invoices SET total = ? WHERE invoice_id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateInvoiceSQL)) {
                    updateStmt.setDouble(1, totalAmount);
                    updateStmt.setInt(2, generatedInvoiceId);
                    updateStmt.executeUpdate();
                }
    
                
                saveInvoiceToDatabase(invoice);
                System.out.println(" Invoice created successfully!");
    
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error creating invoice.");
        }
    }
    
    private static void viewInvoicesByClient() {
        System.out.print("Enter client ID: ");
        int clientID = scanner.nextInt();
        scanner.nextLine();

        String query = "SELECT i.invoice_id, i.total FROM invoices i WHERE i.client_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, clientID);
            ResultSet rs = stmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No invoices found for this client.");
                return;
            }

            System.out.println("\n=== Invoices for Client ID: " + clientID + " ===");
            while (rs.next()) {
                System.out
                        .println("Invoice ID: " + rs.getString("invoice_id") + " | Total: Php" + rs.getDouble("total"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error retrieving invoices.");
        }
    }

    private static void deleteInvoice() {
        System.out.print("Enter invoice ID to delete: ");
        String invoiceID = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            String deleteItemsSQL = "DELETE FROM invoice_items WHERE invoice_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteItemsSQL)) {
                stmt.setString(1, invoiceID);
                stmt.executeUpdate();
            }

            String deleteInvoiceSQL = "DELETE FROM invoices WHERE invoice_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteInvoiceSQL)) {
                stmt.setString(1, invoiceID);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    conn.commit();
                    System.out.println("Invoice deleted successfully!");
                } else {
                    System.out.println("No invoice found with that ID.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error deleting invoice.");
        }
    }

    private static void saveInvoiceToDatabase(Invoice invoice) {
    String insertServiceSQL = "INSERT INTO invoice_items (invoice_id, service_id, quantity, price) VALUES (?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement serviceStmt = conn.prepareStatement(insertServiceSQL)) {

        conn.setAutoCommit(false); 

        System.out.println("Saving services for Invoice ID: " + invoice.getInvoiceID());

        for (Map.Entry<Integer, Integer> entry : invoice.getServices().entrySet()) {
            System.out.println("Adding service: " + entry.getKey() + " with hours: " + entry.getValue());

            serviceStmt.setInt(1, invoice.getInvoiceID());
            serviceStmt.setInt(2, entry.getKey()); 
            serviceStmt.setInt(3, entry.getValue());
            serviceStmt.setDouble(4, getServiceRate(entry.getKey()) * entry.getValue()); 
            serviceStmt.executeUpdate();
        }

        conn.commit();
        System.out.println(" Invoice services saved successfully!");

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println(" Failed to save invoice services.");
    }
}


    private static double getServiceRate(int serviceID) {
        String query = "SELECT rate FROM services WHERE service_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, serviceID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("rate");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private static void viewAllInvoices() {
        String query = "SELECT i.invoice_id, c.name, i.total " +
                "FROM invoices i JOIN clients c ON i.client_id = c.client_id";

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No invoices found.");
                return;
            }

            while (rs.next()) {
                System.out.println("\nInvoice ID: " + rs.getString("invoice_id"));
                System.out.println("Client: " + rs.getString("name"));
                System.out.println("Total Amount: Php" + rs.getDouble("total"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving invoices.");
        }
    }
}

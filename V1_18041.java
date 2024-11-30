import java.util.Scanner;

class IceCream {
    String type;
    int price;
    int stock;

    IceCream(String type, int price, int stock) {
        this.type = type;
        this.price = price;
        this.stock = stock;
    }

    public void updateStock(int quantity) {
        stock -= quantity;
    }

    public void updatePrice(int newPrice) {
        this.price = newPrice;
    }
}

class Customer {
    String name;
    String order;
    int quantity;

    Customer(String name, String order, int quantity) {
        this.name = name;
        this.order = order;
        this.quantity = quantity;
    }
}

public class V1_18041 {
    
    IceCream[] iceCreams = {
        new IceCream("Vanilla", 50, 100),
        new IceCream("Chocolate", 60, 80),
        new IceCream("Strawberry", 70, 60)
    };

    Customer[] customers = new Customer[100];
    int totalCustomers = 0;
    int totalAmount = 0;
    Scanner sc = new Scanner(System.in);
    final String ADMIN_PASSWORD = "siws"; 

    
    public void displayMenu() {
        System.out.println("********** Welcome to Ice Cream Palace **********");
        System.out.println("==================================================");
        for (int i = 0; i < iceCreams.length; i++) {
            System.out.println((i + 1) + ". " + iceCreams[i].type + " - Rs " + iceCreams[i].price + " (Stock: " + iceCreams[i].stock + ")");
        }
        System.out.println("4. Exit");
        System.out.println("==================================================");
        System.out.println("What would you like to order?");
    }

    
    public void placeOrder() {
        if (totalCustomers >= 100) {
            System.out.println("Sorry, we can't accept more customers at the moment.");
            return;
        }

        sc.nextLine(); 
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            if (choice == 4) {
                generateBill();
                break;
            }

            if (choice < 1 || choice > 3) {
                System.out.println("Invalid choice. Please select a valid ice cream.");
                continue;
            }

            IceCream selectedIceCream = iceCreams[choice - 1];
            System.out.println("You have selected " + selectedIceCream.type);
            System.out.print("Enter the quantity: ");
            int quantity = sc.nextInt();

            if (selectedIceCream.stock < quantity) {
                System.out.println("Sorry, we only have " + selectedIceCream.stock + " left in stock.");
                continue;
            }

            selectedIceCream.updateStock(quantity);
            totalAmount += selectedIceCream.price * quantity;

            
            customers[totalCustomers] = new Customer(name, selectedIceCream.type, quantity);
            totalCustomers++;

            System.out.print("Do you want to order more ice creams? (Y/N): ");
            String moreOrder = sc.next();
            if (moreOrder.equalsIgnoreCase("N")) {
                generateBill();
                break;
            }
        }
    }

    
    public void adminMode() {
        System.out.print("Enter admin password: ");
        String enteredPassword = sc.next();

        if (!enteredPassword.equals(ADMIN_PASSWORD)) {
            System.out.println("Incorrect password. Access denied.");
            return;
        }

        System.out.println("Access granted to Admin Mode.");
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. View Customers and Orders");
            System.out.println("2. Update Prices");
            System.out.println("3. Update Stock");
            System.out.println("4. View Stock and Prices");
            System.out.println("5. Exit Admin Mode");
            System.out.print("Enter your choice: ");
            int adminChoice = sc.nextInt();

            switch (adminChoice) {
                case 1:
                    viewCustomersAndOrders();
                    break;
                case 2:
                    updatePrices();
                    break;
                case 3:
                    updateStock();
                    break;
                case 4:
                    viewStockAndPrices();
                    break;
                case 5:
                    System.out.println("Exiting Admin Mode...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    
    public void viewCustomersAndOrders() {
        System.out.println("********** Customer Orders **********");
        for (int i = 0; i < totalCustomers; i++) {
            System.out.println("Customer: " + customers[i].name + " | Ordered: " + customers[i].order + " | Quantity: " + customers[i].quantity);
        }
        if (totalCustomers == 0) {
            System.out.println("No customers yet.");
        }
    }

    
    public void updatePrices() {
        System.out.println("Select the ice cream to update price:");
        for (int i = 0; i < iceCreams.length; i++) {
            System.out.println((i + 1) + ". " + iceCreams[i].type + " - Rs " + iceCreams[i].price);
        }
        int choice = sc.nextInt();
        if (choice >= 1 && choice <= iceCreams.length) {
            System.out.print("Enter new price for " + iceCreams[choice - 1].type + ": ");
            int newPrice = sc.nextInt();
            iceCreams[choice - 1].updatePrice(newPrice);
            System.out.println("Price updated successfully!");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    
    public void updateStock() {
        System.out.println("Select the ice cream to update stock:");
        for (int i = 0; i < iceCreams.length; i++) {
            System.out.println((i + 1) + ". " + iceCreams[i].type + " - Stock: " + iceCreams[i].stock);
        }
        int choice = sc.nextInt();
        if (choice >= 1 && choice <= iceCreams.length) {
            System.out.print("Enter new stock for " + iceCreams[choice - 1].type + ": ");
            int newStock = sc.nextInt();
            iceCreams[choice - 1].stock = newStock;
            System.out.println("Stock updated successfully!");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    
    public void viewStockAndPrices() {
        System.out.println("Current Stock and Prices:");
        for (IceCream iceCream : iceCreams) {
            System.out.println(iceCream.type + " - Price: Rs " + iceCream.price + " | Stock: " + iceCream.stock);
        }
    }

    
    public void generateBill() {
        System.out.println();
        System.out.println("********** Thank you for ordering from Ice Cream Palace **********");
        System.out.println("Your total bill is: Rs " + totalAmount);
    }

    
    public static void main(String[] args) {
        V1_18041 system = new V1_18041();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Select Mode: ");
            System.out.println("1. Customer Mode");
            System.out.println("2. Admin Mode");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mode = sc.nextInt();

            switch (mode) {
                case 1:
                    system.placeOrder();
                    break;
                case 2:
                    system.adminMode();
                    break;
                case 3:
                    System.out.println("Exiting the system...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection. Please choose a valid mode.");
            }
        }
    }
}

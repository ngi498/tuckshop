package app;

import model.Product;
import service.InventoryService;
import service.SalesServices;
import service.ReportService;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        InventoryService inventory = new InventoryService();
        SalesServices sales = new SalesServices();
        ReportService report = new ReportService(sales);

        int choice;

        do {
            System.out.println("\n===== TUCKSHOP SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Sell Product");
            System.out.println("4. Restock Product");
            System.out.println("5. Sales Report");
            System.out.println("6. Exit");

            choice = scanner.nextInt();

            switch(choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();

                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();

                    inventory.addProduct(new Product(id, name, price, qty));
                    break;

                case 2:
                    inventory.viewProducts();
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    int saleId = scanner.nextInt();

                    Product p = inventory.findProduct(saleId);

                    if (p != null) {
                        System.out.print("Enter Quantity: ");
                        int amount = scanner.nextInt();
                        sales.makeSale(p, amount);
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Product ID: ");
                    int restockId = scanner.nextInt();

                    System.out.print("Enter Quantity: ");
                    int restockQty = scanner.nextInt();

                    inventory.restockProduct(restockId, restockQty);
                    break;

                case 5:
                    report.generateReport();
                    break;
            }

        } while(choice != 6);

        System.out.println("Goodbye!");

      scanner.close();  
    }

    
}
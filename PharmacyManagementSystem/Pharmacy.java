import java.io.*;
import java.util.*;

public class Pharmacy {
    ArrayList<Medicine> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    Pharmacy() {
        loadFromFile();
    }

    void addMedicine() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        list.add(new Medicine(id, name, price, qty));
        saveToFile();
        System.out.println("Medicine Added!");
    }

    void viewMedicine() {
        System.out.println("ID | Name | Price | Quantity");
        for (Medicine m : list) {
            m.display();
            if (m.quantity < 10) {
                System.out.println("⚠ Low Stock!");
            }
        }
    }

    void searchMedicine() {
        sc.nextLine();
        System.out.print("Enter Medicine Name: ");
        String name = sc.nextLine();

        for (Medicine m : list) {
            if (m.name.equalsIgnoreCase(name)) {
                m.display();
                return;
            }
        }
        System.out.println("Not Found!");
    }

    void deleteMedicine() {
        if (!Login.currentUser.equals("admin")) {
            System.out.println("Access Denied! Only admin can delete.");
            return;
        }

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Iterator<Medicine> it = list.iterator();
        while (it.hasNext()) {
            Medicine m = it.next();
            if (m.id == id) {
                it.remove();
                saveToFile();
                System.out.println("Deleted!");
                return;
            }
        }
        System.out.println("Not Found!");
    }

    void billing() {
        sc.nextLine();
        System.out.print("Enter Medicine Name: ");
        String name = sc.nextLine();

        for (Medicine m : list) {
            if (m.name.equalsIgnoreCase(name)) {
                System.out.print("Enter Quantity: ");
                int qty = sc.nextInt();

                if (qty <= m.quantity) {
                    double total = qty * m.price;
                    double gst = total * 0.05;
                    double finalAmount = total + gst;

                    m.quantity -= qty;
                    saveToFile();

                    System.out.println("Bill: " + total);
                    System.out.println("GST (5%): " + gst);
                    System.out.println("Final Amount: " + finalAmount);
                } else {
                    System.out.println("Insufficient Stock!");
                }
                return;
            }
        }
        System.out.println("Not Found!");
    }

    void saveToFile() {
        try {
            FileWriter fw = new FileWriter("data.txt");
            for (Medicine m : list) {
                fw.write(m.id + "," + m.name + "," + m.price + "," + m.quantity + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving!");
        }
    }

    void loadFromFile() {
        try {
            File file = new File("data.txt");
            if (!file.exists()) return;

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                list.add(new Medicine(
                        Integer.parseInt(data[0]),
                        data[1],
                        Double.parseDouble(data[2]),
                        Integer.parseInt(data[3])
                ));
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error loading file!");
        }
    }
}
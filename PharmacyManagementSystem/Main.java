import java.util.*;

public class Main {
    public static void main(String[] args) {

        Login login = new Login();

        if (!login.authenticate()) {
            System.exit(0);
        }

        Pharmacy p = new Pharmacy();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Pharmacy Management System ---");
            System.out.println("1. Add Medicine");
            System.out.println("2. View Medicines");
            System.out.println("3. Search Medicine");
            System.out.println("4. Delete Medicine (Admin Only)");
            System.out.println("5. Billing");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1: p.addMedicine(); break;
                case 2: p.viewMedicine(); break;
                case 3: p.searchMedicine(); break;
                case 4: p.deleteMedicine(); break;
                case 5: p.billing(); break;
                case 6: System.exit(0);
                default: System.out.println("Invalid Choice!");
            }
        }
    }
}
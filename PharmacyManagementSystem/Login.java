import java.util.*;

public class Login {
    HashMap<String, String> users = new HashMap<>();
    public static String currentUser = "";

    Login() {
        users.put("admin", "1234");
        users.put("staff", "1111");
    }

    boolean authenticate() {
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter Username: ");
            String user = sc.nextLine();

            System.out.print("Enter Password: ");
            String pass = sc.nextLine();

            if (users.containsKey(user) && users.get(user).equals(pass)) {
                System.out.println("Login Successful!\n");
                currentUser = user;
                return true;
            } else {
                System.out.println("Invalid Credentials! Attempt " + i + "/3\n");
            }
        }

        System.out.println("Too many failed attempts!");
        return false;
    }
}
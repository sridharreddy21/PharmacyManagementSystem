public class Medicine {
    int id;
    String name;
    double price;
    int quantity;

    Medicine(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    void display() {
        System.out.println(id + " | " + name + " | " + price + " | " + quantity);
    }
}
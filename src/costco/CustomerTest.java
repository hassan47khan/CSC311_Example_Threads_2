package costco;

public class CustomerTest {
    public static void main(String[] args) throws InterruptedException {

        CheckoutQueue queue = new CheckoutQueue(5);

        Thread c1 = new Thread(new Customer(1, queue));
        Thread c2 = new Thread(new Customer(2, queue));
        Thread c3 = new Thread(new Customer(3, queue));

        c1.start();
        c2.start();
        c3.start();

        c1.join();
        c2.join();
        c3.join();

        System.out.println("\nAll customers finished shopping.");
        System.out.println("Customers waiting: " + queue.size());
    }
}

package costco;

import java.security.SecureRandom;

public class Customer implements Runnable {

    private static final SecureRandom generator = new SecureRandom();

    private final int customerId;
    private final CheckoutQueue checkoutQueue;
    private final int minShoppingTime;
    private final int maxShoppingTime;

    // Constructor with default shopping time
    public Customer(int customerId, CheckoutQueue checkoutQueue) {
        this(customerId, checkoutQueue, 1000, 3000);
    }

    // Constructor with customizable shopping time
    public Customer(int customerId, CheckoutQueue checkoutQueue,
                    int minShoppingTime, int maxShoppingTime) {

        if (minShoppingTime < 0 || maxShoppingTime < minShoppingTime) {
            throw new IllegalArgumentException("Invalid shopping time range");
        }

        this.customerId = customerId;
        this.checkoutQueue = checkoutQueue;
        this.minShoppingTime = minShoppingTime;
        this.maxShoppingTime = maxShoppingTime;
    }

    @Override
    public void run() {

        try {
            // Customer starts shopping
            int shoppingTime = minShoppingTime
                    + generator.nextInt(maxShoppingTime - minShoppingTime + 1);

            System.out.printf("%-12s Customer %d started shopping%n",
                    Thread.currentThread().getName(), customerId);

            // Simulate shopping
            Thread.sleep(shoppingTime);

            System.out.printf("%-12s Customer %d finished shopping%n",
                    Thread.currentThread().getName(), customerId);

            // Customer joins the checkout line
            checkoutQueue.joinQueue(customerId);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            System.out.printf("Customer %d was interrupted%n", customerId);
        }
    }
}


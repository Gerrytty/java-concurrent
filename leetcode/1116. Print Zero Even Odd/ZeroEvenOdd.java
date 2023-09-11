import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;

    private int current = 1;
    private boolean end = false;

    private boolean zeroNow = true;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {

        while (current <= n) {

            synchronized (this) {

                while (!zeroNow) {

                    if (end) {
                        break;
                    }

                    wait();
                }

                zeroNow = false;

                if (end) {
                    break;
                }

                if (current <= n) {
                    printNumber.accept(0);
                }
                else {
                    end = true;
                }
                notifyAll();

            }

        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {

        while (current <= n || end) {

            synchronized (this) {

                while (zeroNow || current % 2 != 0) {

                    if (end) {
                        break;
                    }

                    wait();
                }

                if (end) {
                    break;
                }

                if (current <= n) {
                    printNumber.accept(current);
                    current += 1;
                    zeroNow = true;
                }
                else {
                    end = true;
                }
                notifyAll();

            }

        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {

        while (current <= n || end) {

            synchronized (this) {

                while (zeroNow || current % 2 == 0) {

                    if (end) {
                        break;
                    }
                    wait();
                }

                if (end) {
                    break;
                }

                if (current <= n) {

                    printNumber.accept(current);

                    current += 1;
                    zeroNow = true;
                }
                else {
                    end = true;
                }
                notifyAll();

            }

        }

    }
}
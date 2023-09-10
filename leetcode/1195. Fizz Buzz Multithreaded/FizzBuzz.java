import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private int current = 1;

    private boolean end = false;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {

        synchronized (this) {
            while (current <= n && !end) {

                while (current % 3 != 0 || current % 5 == 0) {

                    if (current == 0) {
                        end = true;
                        break;
                    }

                    wait();
                }

                if (current <= n && current != 0) {
                    printFizz.run();
                    current += 1;
                }
                else {
                    current = 0;
                }

                notifyAll();

            }
        }

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {

        synchronized (this) {
            while (current <= n && !end) {

                while (current % 5 != 0 || current % 3 == 0) {

                    if (current == 0) {
                        end = true;
                        break;
                    }

                    wait();
                }

                if (current <= n && current != 0) {
                    printBuzz.run();
                    current += 1;
                }
                else {
                    current = 0;
                    end = true;
                }

                notifyAll();

            }
        }

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {

        synchronized (this) {
            while (current <= n && !end) {

                while (current % 15 != 0) {
                    wait();
                }

                if (current <= n && current != 0) {
                    printFizzBuzz.run();
                    current += 1;
                }
                else {
                    current = 0;
                    end = true;
                }

                notifyAll();

            }
        }

    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {

        synchronized (this) {
            while (current <= n && !end) {

                while (current % 5 == 0 || current % 3 == 0) {

                    if (current == 0) {
                        end = true;
                        break;
                    }
                    wait();
                }

                if (current == n) {
                    printNumber.accept(current);
                    current = 0;
                    notifyAll();
                    break;
                }

                if (current != 0 && current <= n) {
                    printNumber.accept(current);
                    current += 1;
                }
                else {
                    current = 0;
                }

                notifyAll();

            }
        }

    }
}

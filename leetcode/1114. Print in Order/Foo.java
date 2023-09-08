class Foo {

    private int number = 1;

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        synchronized (this) {

            try {
                while (number != 1) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();

            number = 2;
            notifyAll();
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {

        synchronized (this) {

            try {
                while (number != 2) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();

            number = 3;
            notifyAll();
        }

    }

    public void third(Runnable printThird) throws InterruptedException {

        synchronized (this) {

            try {
                while (number != 3) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();

            number = 1;
            notifyAll();
        }
    }
}
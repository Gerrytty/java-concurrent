class FooBar {
    private int n;

    private int queue = 0;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (this) {

                while (queue != 0) {
                    wait();
                }

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                queue = 1;
                notify();

            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (this) {

                while (queue != 1) {
                    wait();
                }

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                queue = 0;
                notify();

            }

        }
    }
}
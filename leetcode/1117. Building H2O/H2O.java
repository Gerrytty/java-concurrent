class H2O {

    private boolean currO = false;
    private boolean currH = true;

    private volatile int countH = 0;
    private volatile int countO = 0;

    private boolean ok = false;


    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        synchronized (this) {

            while (countH == 2) {
                wait();
            }

            countH++;

            releaseHydrogen.run();
            notifyAll();
        }

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        synchronized (this) {

            while (countH < 2) {
                wait();
            }

            countH = 0;

//            System.out.println("O");

            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            notifyAll();
        }

    }
}
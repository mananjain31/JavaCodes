class Q {
    int n;
    boolean valueSet = false;
    synchronized void put (int n) {
        while (valueSet)
            try { wait();} catch(Exception e){}
        System.out.println("Put :" + n);
        this.n = n;
        valueSet = true;
        notify();
    }
    synchronized void get () {
        while (!valueSet)
            try { wait(); } catch(Exception e){}
        System.out.println("Get = " + n);
        valueSet = false;
        notify();
    }
//    synchronized public void print()
//    {
//        while (valueSet)
//            try { wait();} catch(Exception e){}
//        System.out.println("Intermediate");
//        valueSet = true;
//        notify();
//    }
}

class Producer implements Runnable {
    Q q;
    public Producer(Q q) {
        this.q = q;
        new Thread (this, "Producer").start();
    }
    public void run() {
        int i = 0;
        while (true)
        {
            q.put (i++);
            try {Thread.sleep(1000);} catch (Exception e) {}
        }
    }
}
class Consumer implements Runnable {
    Q q;
    public Consumer(Q q) {
        this.q = q;
        new Thread (this, "Consumer").start();
    }
    public void run() {
        while (true)
        {
            q.get ();
            try {Thread.sleep(1000);} catch (Exception e) {}
        }
    }
}

//class Intermediate implements Runnable {
//    Q q;
//    public Intermediate (Q q)
//    {
//        this.q = q;
//        new Thread (this, "intermediate").start();
//    }
//    public void run() {
//        while(true) {
//            q.print();
//            try {Thread.sleep(1000);} catch (Exception e) {}
//        }
//    }
//}

class Thread3 {
    public static void main (String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
//        new Intermediate(q);
    }
}
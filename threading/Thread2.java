class A
{
    int count;
    synchronized void increment(int a)
    {
        count++;
        System.out.print(a + " ");
    }
}
class Thread2
{
    public static void main(String[] args) throws Exception {
        A a = new A();

        Thread t1 = new Thread(
                () -> { for(int i=0; i<1000; i++) a.increment(1); }
        );
        Thread t2 = new Thread(
                () -> {  for(int i=0; i<1000; i++) a.increment(100000); }
        );

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(a.count);
    }
}
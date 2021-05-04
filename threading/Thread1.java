class Thread1
{
    public static void main(String[] args) throws Exception {
        Thread a = new Thread ( () ->
        {
            for (int i = 0; i < 5; i++) {
                System.out.println("hello");
                try {Thread.sleep(500);} catch (Exception e) {}
            }
        });
        Thread b = new Thread ( () ->
        {
            for (int i = 0; i < 5; i++) {
                System.out.println("world");
                try {Thread.sleep(500);} catch (Exception e) {}
            }
        });

        a.setName("hello thred");
        b.setName("world thred");
        System.out.println(a.getName());
        System.out.println(b.getName());

        a.start();
        try {Thread.sleep(10); } catch (Exception e) {}
        b.start();


        System.out.println(a.isAlive());
        System.out.println(b.isAlive());

        a.join();
        b.join();

        System.out.println("Done");

    }
}
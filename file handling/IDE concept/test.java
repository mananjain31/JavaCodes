public class test
{
 public static void main(String[] args)
 {
  Thread t = new Thread(new T(), "Manan");
  t.start();
  Thread t2 = new Thread(new T(), "Jain");
  t2.start();
 }
}
class T implements Runnable
{
 public void run()
 {
  System.out.println(Thread.currentThread());
 }
}

import java.io.*;
import java.util.*;
class FileRead
{
    public static void main(String...args) {
        try{
            
            File file = new File("newfile.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine())
            {
                System.out.println(sc.nextLine());
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
import java.io.*;
import java.util.*;
class FileWriteUserInput
{
    public static void main(String...args) {
        try
        {
            FileWriter fw = new FileWriter("newfile.txt");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Text to write in files (End the last line with \"END\" without quotes)");
            String data = "";
            while(!data.equals("END\n"))
            {
                fw.write(data);
                data = sc.nextLine()+'\n';
            }
            fw.close();
            System.out.println("File Writing Ended");
            System.out.println("Printing data :");
            FileRead.main();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
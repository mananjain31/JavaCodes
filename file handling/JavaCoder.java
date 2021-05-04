import java.util.*;
import java.io.*;
class JavaCoder
{
    public static String getFile() throws Exception
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Java File Name (Don't write the .java extension) :");
        
        String filename = sc.nextLine();
        File file = new File(filename+".java");

        if (file.createNewFile()) {
            return filename;
        }
        return filename;
    }

    public static void main(String...args) {
        try
        {
            String filename = getFile();

            System.out.println("Opened File : "+filename+".java");
            Thread.sleep(1000);
            System.out.println("#############################");
            System.out.println("Enter Java code for the file below..");
            System.out.println("when you are done,\n Enter -> \"END\" without quotes");
            System.out.println(" in a new line");
            System.out.println("#############################");
             
            FileWriter fw = new FileWriter(filename+".java");
            Scanner sc = new Scanner(System.in);
            String data = "";
            
            while(!data.trim().equals("END"))
            {
                fw.write(data);
                data = sc.nextLine()+'\n';
            }
            fw.close();
            showOutput(filename);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    public static void showOutput(String filename) throws Exception
    {
        
        Process compiling = Runtime.getRuntime().exec("javac "+filename+".java");
        compiling.waitFor();
        Process p = Runtime.getRuntime().exec("java "+filename);
        System.out.println("\n:::::::::: OUTPUT :::::::::::");
        printLines(p.getInputStream());

        System.out.println("\n:::::::::: ERRORS :::::::::::");        
        printLines(p.getErrorStream());

        p.waitFor();    
        System.out.println("\n:::::::::: EXIT VALUE :::::::::::");        
        System.out.println(p.exitValue());

    }

    public static void printLines(InputStream is) throws Exception
    {
        String line = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        while ((line = in.readLine()) != null)
           System.out.println(line);
        Thread.sleep(1000);
    }
}
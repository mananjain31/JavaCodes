//THIS OPENS NOTEPAD EDITOR TO EDIT THIS FILE AND THEN EXCUTES IT
import java.util.*;
import java.io.*;
class JavaCoder3
{
    public static String getFile() throws Exception
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Java File Name (Don't write the .java extension) :");
        
        String filename = sc.nextLine();
        File file = new File(filename+".java");

        if (!file.exists()) {
            file.createNewFile();
        }
        return filename;
    }

    public static void main(String...args) {
        try
        {
            String filename = getFile();
            File file = new File(filename+".java");
            System.out.println("Opened File : "+filename+".java");
            Thread.sleep(1000);
            Process p = Runtime.getRuntime().exec("notepad.exe "+file.getAbsolutePath());
            p.waitFor();
            showOutput(filename);
            System.out.println("######################################");
            System.out.println("Do you wish to continue ??");
            System.out.print("Enter yes or no :" );
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            if(s.trim().equalsIgnoreCase("yes"))
                main();
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
        Scanner sc = new Scanner(is);
        while (sc.hasNextLine())
           System.out.println(sc.nextLine());
    }
}
//THIS OPENS NOTEPAD EDITOR TO EDIT THIS FILE AND THEN EXCUTES IT
import java.util.*;
import java.io.*;
class IDEcpp
{
    public static String getFile() throws Exception
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter C++ File Name (Don't write the .cpp extension) :");
        
        String filename = sc.nextLine();
        File file = new File(filename+".cpp");

        if (!file.exists()) {
            file.createNewFile();
        }
        return filename;
    }

    public static void main(String...args) {
        try
        {
            String filename = getFile();
            File file = new File(filename+".cpp");
            System.out.println("Opened File : "+filename+".cpp");
            Thread.sleep(1000);
            System.out.println("Close the File opened in notepad to get the output");
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
        
        Process compiling = Runtime.getRuntime().exec("g++ "+filename+".cpp");
        System.out.println("\n_______________COMPILING_____________");
        System.out.println("\n:::::::::: OUTPUT :::::::::::");
        printLines(compiling.getInputStream());
        compiling.waitFor();    

        System.out.println("\n:::::::::: ERRORS :::::::::::");         
        printLines(compiling.getErrorStream());
        compiling.waitFor();    

        System.out.println("\n:::::::::: EXIT VALUE :::::::::::");        
        System.out.println(compiling.exitValue());
        if(compiling.exitValue() != 0) return;
        
        Process p = Runtime.getRuntime().exec("a.exe");
        System.out.println("\n_______________EXECUTING____________");
        System.out.println("\n:::::::::: OUTPUT :::::::::::");
        printLines(p.getInputStream());
        p.waitFor();    

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
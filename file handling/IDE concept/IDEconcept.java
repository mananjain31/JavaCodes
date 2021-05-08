//THIS OPENS NOTEPAD EDITOR TO EDIT THIS FILE AND THEN EXCUTES IT
import java.util.*;
import java.io.*;
class IDEconcept
{
    public static String getFile() throws Exception
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Java File Name (Don't write the .java extension) :");
        
        String filename = sc.nextLine().trim();
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
            System.out.println("Save and Close the File opened in notepad to proceed further");
            Thread.sleep(1000);
            Process p = Runtime.getRuntime().exec("notepad.exe "+file.getAbsolutePath());
            p.waitFor();
            System.out.println("\nOpening file to Specify the Input :");
            Thread.sleep(1000);
            System.out.println("Save and Close the File opened in notepad to get the output");
            Thread.sleep(1000);
            specifyInput(filename);
            showOutput(filename);
            removeExtraLines(filename);
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
    
    public static void specifyInput(String filename) throws Exception
    {
        Process p = Runtime.getRuntime().exec("notepad.exe "+filename+"INPUT.txt");
        p.waitFor();
        FileWriter fw = new FileWriter(filename+".java", true);
        fw.write("\nclass "+filename+"helperClass"+ 
            "{"+
                "public static void main(String[] args)"+
                "{"+
                    "try{System.setIn(new java.io.FileInputStream("+"\""+filename+"INPUT.txt\"));}"+
                    "catch(Exception e){System.out.println(e);}\n"+
                    filename+".main(\"\");"+
                "}"+
            "}");
        fw.close();
    }
    
    public static void showOutput(String filename) throws Exception
    {
        Process compiling = Runtime.getRuntime().exec("javac "+filename+".java");
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
        
        Process p = Runtime.getRuntime().exec("java "+filename+"helperClass");
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

    public static void removeExtraLines(String filename) throws Exception
    {
        ArrayList<String> lines = new ArrayList<>();
        Scanner sc = new Scanner(new File(filename+".java"));
        while(sc.hasNextLine()) 
            lines.add(sc.nextLine());
        int noOfLines = lines.size();
        lines.remove(noOfLines-1);
        lines.remove(noOfLines-2);
        FileWriter fw = new FileWriter(filename+".java");
        for(String line : lines)
            fw.write(line+"\n");
        fw.close();
    }
}
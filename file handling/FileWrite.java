import java.io.*;
class FileWrite
{
    public static void main(String[] args) {
        try {
            FileWriter fw = new FileWriter("newfile.txt");
            fw.write("HeLLo WoRlD123 !i");
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
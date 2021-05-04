import java.io.*;
class FileCreate {
    public static void main(String[] args) throws IOException {
        File file = new File("newfile.txt");
        if (file.createNewFile()) {
            System.out.println("file created succesfully");
        }
        else {
            System.out.println("file not created");
        }
    }
}
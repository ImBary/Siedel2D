import java.io.IOException;

public class Main {
     public static void main(String[] args) throws IOException {
        String filename = "input.txt";
        
        boolean res = isUnbounded.isStrict(filename);
        System.out.println("\nNIEOGRANICZONY " + res);
    }
}

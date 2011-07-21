
import java.util.Scanner;

public class locs {

    public static void main(String[] args) {
        String list = "";
        Scanner s = new Scanner(System.in);
        System.out.println("Listeyi girin : ");
        while (s.hasNext()) {
            String oku = s.next();
            
            System.out.print("['" + oku + "'],");
        }
        System.out.println("Okeyto!");

    }
}

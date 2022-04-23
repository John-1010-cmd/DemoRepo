import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str="";
        while(!str.equals("quit"))
            if(scan.hasNext()) {
                str = scan.next();
                System.out.println(str);
            }
    }
}

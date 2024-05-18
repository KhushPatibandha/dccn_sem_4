import java.util.Scanner;

public class CRC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter data bits: ");
        String data = sc.nextLine();
        
        StringBuilder sb = new StringBuilder(data);

        System.out.print("Enter divisor bits: ");
        String divisor = sc.nextLine();

        for(int i = 0; i < divisor.length() - 1; i++) {
            sb.append("0");
        }

        String result = helper(sb, divisor);
        System.out.println("Result: " + result);

        sc.close();
    }

    public static String helper(StringBuilder data, String divisor) {
        
        while(data.length() >= divisor.length()) {
            String temp = "";
            for(int i = 0; i < divisor.length(); i++) {
                temp += data.charAt(i) ^ divisor.charAt(i);
            }
            data.delete(0, divisor.length());
            data.insert(0, temp);
            while(data.charAt(0) == '0' && data.length() > 1) {
                data.deleteCharAt(0);
            }
        }
        return data.toString();
    }
}

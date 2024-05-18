import java.util.ArrayList;
import java.util.Scanner;

public class CheckSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the data bits: ");
        String data = sc.nextLine();
        ArrayList<String> dataBits = new ArrayList<>();

        for(int i = 0; i < data.length(); i += 8) {
            String temp = data.substring(i, i + 8);
            dataBits.add(temp);
        }

        StringBuilder sum = new StringBuilder(calcSum(dataBits));
        StringBuilder wrapSum = new StringBuilder(wrapSum(sum));
        
        for(int i = 0; i < wrapSum.length(); i++) {
            if(wrapSum.charAt(i) == '0') {
                wrapSum.setCharAt(i, '1');
            } else {
                wrapSum.setCharAt(i, '0');
            }
        }
        
        System.out.println(wrapSum);
        
        sc.close();
    }

    public static String wrapSum(StringBuilder sum) {
        if(sum.length() <= 8) {
            return sum.toString();
        } else {
            int diff = sum.length() - 8;
            String wrap = sum.substring(0, diff);
            sum.delete(0, diff);
        
            int carry = 0;
            int sumPointer = sum.length() - 1;
            for(int i = wrap.length() - 1; i >= 0; i--) {
                int addition = 0;
                addition = Character.getNumericValue(wrap.charAt(i)) + Character.getNumericValue(sum.charAt(sumPointer)) + carry;
                carry = addition / 2;
                addition = addition % 2;
                sum.setCharAt(sumPointer, (char)(addition + '0'));
                sumPointer--;
            }
            sum.setCharAt(sumPointer, (char)(carry + '0'));
            sumPointer--;
        }
        return sum.toString();
    }

    public static String calcSum(ArrayList<String> dataBits) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
    
        int pointer = dataBits.get(0).length() - 1;
        while (pointer >= 0) {
            int sum = 0;
            for (String data: dataBits) {
                sum += Character.getNumericValue(data.charAt(pointer));
            }
            sum += carry;
    
            carry = sum / 2;
            sum = sum % 2;
    
            result.insert(0, sum);
            pointer--;
        }
    
        while (carry > 0) {
            result.insert(0, carry % 2);
            carry /= 2;
        }
    
        return result.toString();
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Checksum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter some data to send: ");
        String data = sc.nextLine();

        ArrayList<Integer> ascii = ascii(data);
        ArrayList<String> binary = binary(ascii);

        System.out.println(ascii);
        System.out.println(binary);

        String checksum = checksum(binary);
        binary.add(0, checksum);

        System.out.println(checksum);

        System.out.println("Reciver side");
        String result = checksum(binary);
        System.out.println(result);

        if(result.equals("00000000")) {
            System.out.println("Data is correct");
        } else {
            System.out.println("Data is incorrect");
        }
    }

    public static ArrayList<Integer> ascii(String data) {
        ArrayList<Integer> ascii = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            ascii.add((int) data.charAt(i));
        }
        return ascii;
    }

    public static ArrayList<String> binary(ArrayList<Integer> ascii) {
        ArrayList<String> binary = new ArrayList<>();
        for (int i = 0; i < ascii.size(); i++) {
            binary.add(String.format("%8s", Integer.toBinaryString(ascii.get(i))).replace(" ", "0"));
        }
        return binary;
    }

    public static String checksum(ArrayList<String> binary) {
        int sum = 0;
        for (String s : binary) {
            sum += Integer.parseInt(s, 2);
        }
        while (sum > 0b11111111) {
            sum = (sum & 0b11111111) + 1;
        }
        return String.format("%8s", Integer.toBinaryString(sum ^ 0b11111111)).replace(" ", "0");
    }
}

package ch5;

public class StringArray {
    public static void main(String[] args) {
        String[] names = {"Kim", "Park", "Yi"};

        for (int i=0; i < names.length; i++)
            System.out.println("names["+ i + "] :" + names[i]);

        String tmp = names[2];
        System.out.println("tmp :" + tmp);
        names[0] = "Yu";

        for (String str : names) // 향상된 for문
            System.out.println(str);
    }
}

class Ex2{ // 16진수를 2진수로 변환
    public static void main(String[] args) {
        char[] hex = {'C','A','F','E'};

        String[] binary = { "0000", "0001", "0010", "0011"
                           , "0100" , "0101", "0110", "0111"
                           , "1000" , "1001", "1010", "1011"
                           , "1100" , "1101", "1110", "1111" }; // 16진수로 0~F

        String result = "";

        for (int i=0; i < hex.length; i++){
            if(hex[i] >= '0' && hex[i] <='9') { // 0~ 9
                result += binary[hex[i]-'0']; // 'n' - '0' = n
            } else {  // A~F
                result += binary[hex[i]-'A'+10];
                // i =0 인경우
                // 'C' - 'A' + 10 은 67-65 = 2
                // binary[12]는 "1100"
            }
        }

        System.out.println("hex:" + new String(hex));
        System.out.println("binary:" + result);
    }
}

class Ex3{
    public static void main(String[] args) {
        String src = "ABCDE";

        for(int i=0; i < src.length() ; i++) {
            char ch = src.charAt(i);
            System.out.println("src.charAt(" + i + "):"+ ch);
        }

        char[] chArr = src.toCharArray(); // String -> char

        System.out.println(chArr); // char배열 출력하면 붙어서 나옴
    }
}

class Ex4{
    public static void main(String[] args) { //String을 모스부호로
        String source = "SOSHELP";
        String[] morse = {".-", "-...", "-.-.","-..","."
                         , "..-.", "--.", "....", "..", ".---"
                        , "-.-", ".-..","--","-.","---"
                        , ".--.","--.-", ".-.","...", "-"
                        , "..-","...-",".--","-..-","-.--"
                        , "--.." };
        String result = "";

        for (int i = 0; i < source.length(); i++) {
            result += morse[source.charAt(i)-'A']; // 'S' - 'A'는 83-65 = 18
        }
        System.out.println("source:" + source);
        System.out.println("morse" + result);
    }
}


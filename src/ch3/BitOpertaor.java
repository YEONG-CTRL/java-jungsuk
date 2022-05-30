package ch3;

import static java.lang.Integer.toBinaryString;

public class BitOpertaor {
    public static void main(String[] args) {
        int x = 0xAB, y = 0xF;

        System.out.printf("x = %#X \t\t%s%n", x, toBinaryString(x));
        System.out.printf("y = %#X \t\t%s%n", y, toBinaryString(y));
        System.out.printf("%#X | %#X = %#X \t%s%n", x, y, x|y, toBinaryString(x|y));
        System.out.printf("%#X & %#X = %#X \t%s%n", x, y, x^y, toBinaryString(x&y));
        System.out.printf("%#X ^ %#X = %#X \t%s%n", x, y, x^y, toBinaryString(x^y));
        System.out.printf("%#X ^ %#X ^ %#X = %#X %s%n", x, y, y,x^y^y, toBinaryString(x^y^y));
    }

    static String toBinaryString(int x) { //10진 정수를 2진수로 변환
        String zero = "00000000000000000000000000000000";
        String tmp = zero + Integer.toBinaryString(x);
        return tmp.substring(tmp.length()-32);
    }
}

class BitEx2 {
    public static void main(String[] args) {
        byte p = 10;
        byte n = -10;

        System.out.printf(" p =%d \t%s%n", p, toBinaryString(p)); // 10
        System.out.printf(" p =%d \t%s%n", ~p, toBinaryString(~p)); // -11
        System.out.printf(" p =%d \t%s%n", ~p + 1, toBinaryString(~p + 1)); // -10
        System.out.printf(" p =%d \t%s%n", ~~p, toBinaryString(~~p));
        System.out.println();
        System.out.printf(" n =%d%n", n);
        System.out.printf("~(n-1)=%d%n", ~(n - 1)); // 10
    }

        static String toBinaryString(int x) { //10진 정수를 2진수로 변환
            String zero = "00000000000000000000000000000000";
            String tmp = zero + Integer.toBinaryString(x);
            return tmp.substring(tmp.length()-32);
    }
}

class BitEx3 {
    public static void main(String[] args) {
        int dec = 1234;
        int hex = 0xABCD; // 1010 1011 1100 1101
        int mask = 0xF; // 0000 0000 0000 1111

        System.out.printf("hex=%X%n", hex);
        System.out.printf("%X%n", hex & mask); // 0000 0000 0000 1101

        hex = hex >> 4; //  0x0ABC /  0000 1010 1011 1100
        System.out.printf("%X%n", hex & mask); // 0000 0000 0000 1100 -> C

        hex = hex >> 4; // 0x00AB / 0000 0000 1010 1011
        System.out.printf("%X%n", hex & mask); // 0000 0000 0000 1011 -> B

        hex = hex >> 4; // 0x000B / 0000 0000 0000 1010
        System.out.printf("%X%n", hex & mask); // 0000 0000 0000 1010 -> B
    }
}

class ConditionalOperator {
    public static void main(String[] args) {
        int x, y, z;
        int absX, absY, absZ;
        char signX, signY, signZ;

        x = 10;
        y = -5;
        z = 0;

        absX = x >= 0 ? x : -x;
        absY = y >= 0 ? y : -y;
        absZ = z >= 0 ? z : -z;

        signX = x > 0 ? '+' : (x==0 ? ' ' : '-');
        signY = y > 0 ? '+' : (y==0 ? ' ' : '-');
        signZ = z > 0 ? '+' : (z==0 ? ' ' : '-');

        System.out.printf("x=%c%d%n", signX, absX); // + 10
        System.out.printf("y=%c%d%n", signY, absY); // -  5
        System.out.printf("z=%c%d%n", signZ, absZ); //    0
    }
}
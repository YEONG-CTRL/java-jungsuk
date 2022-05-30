package ch3;

public class ComparisonOpertaor {
    public static void main(String[] args) {
        System.out.printf("10 == 10.0f \t %b%n", 10 == 10.0f); // float으로 형변환 이후 비교
        System.out.printf("'0' == 0 \t %b%n", '0' == 0);
        System.out.printf("'A' == 65 \t %b%n", 'A' == 65);
        System.out.printf("'A' > 'B' \t %b%n", 'A' > 'B');
        System.out.printf("'A'+1 != 'B' \t %b%n", 'A'+1 != 'B');
    }
}

class ComparisonOpertaor2 {
    public static void main(String[] args) {
        float f = 0.1f;
        double d = 0.1;
        double d2 = (double)f;

        System.out.printf("10.0 == 10.0f %b%n ", 10.0 == 10.0f);
        System.out.printf("0.1 == 0.1f %b%n ", 0.1 == 0.1f); // 0.1f 2진수로 변환하는 과정에서 오차 발생
        System.out.printf("f == %19.17f%n ", f);
        System.out.printf("d == %19.17f%n ", d);
        System.out.printf("d2 == %19.17f%n ", d2);
    }
}


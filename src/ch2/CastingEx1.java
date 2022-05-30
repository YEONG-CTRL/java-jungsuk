package ch2;

public class CastingEx1 {
    public static void main(String[] args) {
        double d = 85.4;
        int score = (int)d;

        System.out.println("score = " + score);
        System.out.println("d = " + d);
    }
}

class CastingEx2 {
    public static void main(String[] args) {
        int i = 10;
        byte b = (byte)i;
        System.out.printf("[int -> byte] i=%d -> b=%d%n", i, b);

        i = 300;
        b = (byte)i;
        System.out.printf("[int -> byte] i=%d -> b=%d%n", i, b);
        // 4byte를 1byte로 바꾸는 과정에서 값손실 발생

        b = 10;
        i = (int)b;
        System.out.printf("[byte -> int] b=%d -> i=%d%n", b, i);

        b = -2;
        i = (int)b;
        System.out.printf("[byte -> int] b=%d -> i=%d%n", b, i);

        System.out.println("i=" + Integer.toBinaryString(i)); // i의 값 2진수로(빈자리 모두 1로채움 ->2의 보수법)
    }
}

/*
결과
[int -> byte] i=10 -> b=10
[int -> byte] i=300 -> b=44
[byte -> int] b=10 -> i=10
[byte -> int] b=-2 -> i=-2
i=11111111111111111111111111111110
 */

class CastingEx3 {
    public static void main(String[] args) {
        float f = 9.1234567f; // 같은 값이더라도 d와 f의 정밀도 차이로 서로 다른값 저장됨
        double d = 9.1234567;
        double d2 = (double)f; // 저장할때 값이 달라졌기에, 형변환 하더라도 값이 같아지지 않음

        System.out.printf("f = %20.18f\n", f);
        System.out.printf("d = %20.18f\n", d);
        System.out.printf("d2 = %20.18f\n", d2);
    }
}
/*
f = 9.123456954956055000
d = 9.123456700000000000
d2 = 9.123456954956055000
 */

class CastingEx4 {
    public static void main(String[] args) {
        int i = 91234567;
        float f = (float)i;
        int i2 = (int)f;

        double d = (double)i;
        int i3 = (int)d;
        float f2 = 1.666f;
        int i4 = (int)f2;

        System.out.printf("i=%d\n", i);
        System.out.printf("f=%f i2=%d\n", f, i2); // float의 정밀도(7자리)에 따른 값 변화
        System.out.printf("d=%f i3=%d\n", d, i3);
        System.out.printf("(int)%f=%d\n", f2, i4); // 소수점 아래 버림
    }
}
/*
i=91234567
f=91234568.000000 i2=91234568
d=91234567.000000 i3=91234567
(int)1.666000=1
 */
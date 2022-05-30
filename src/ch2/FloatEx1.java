package ch2;

public class FloatEx1 {
    public static void main(String[] args) {
        float f = 9.1234567890123456789f;
        float f2 = 1.12345678901234567890f;
        double d = 9.12345678901234567890d;

        System.out.printf("       12345678901234567890%n");
        System.out.printf("f    :   %f%n", f); // 7자리에서 반올림(정밀도 7자리)
        System.out.printf("f    : %24.20f%n", f); // 24.20 : 전체 24자리중 20자리는 소수점 이하의 수 출력
        System.out.printf("f2   : %24.20f%n", f2); 
        System.out.printf("d    : %24.20f%n", d);
    }
}

/*
결과
       12345678901234567890
f    :   9.123457
f    :   9.12345695495605500000 저장공간의 한계로 오차 발생(앞의 7자리는 일치)
f2   :   1.12345683574676510000 저장공간의 한계로 오차 발생(앞의 7자리는 일치)
d    :   9.12345678901234600000 정밀도 15자리이기에 12345'6'에서 반올림
 */
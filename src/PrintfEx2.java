class PrintfEx2 {
    public static void main(String[] args) {
        String url = "www.codechobo.com";

        float f1 = .10f; // 0.10 , 1.0e-1
        float f2 = 1e1f; // 10.0, 1.0e1, 1.0e+1
        float f3 = 3.14e3f;
        double d = 1.23456789;

        System.out.printf("f1=%f, %e, %g%n", f1, f1, f1); // %f부동소수점, %e 지수형태, %g 값을 간략하게 표현할때
        System.out.printf("f2=%f, %e, %g%n", f2, f2, f2);
        System.out.printf("f3=%f, %e, %g%n", f3, f3, f3);


        System.out.printf("d=%f%n", d);  // %f 는 소수점 아래 6자리까지만 출력, 소수점 7자리에서 반올림
        System.out.printf("d=%14.10f%n", d); // 총 14자리중, 10자리는 소수점 아래(2345678900)
        System.out.printf("d=%014.10f%n", d); // 위와 비슷하지만, 빈자리를 0으로 채움

        System.out.printf("[12345678901234567890]%n");
        System.out.printf("[%s]%n", url); 
        System.out.printf("[%20s]%n", url); // 20글자 출력공간 확보(우측정렬)
        System.out.printf("[%-20s]%n", url); // 이번엔 좌측정렬
        System.out.printf("[%.4s]%n", url); // 왼쪽에서 4글자만 출력(문자열의 일부만)
    }
}

/*
결과
f1=0.100000, 1.000000e-01, 0.100000
f2=10.000000, 1.000000e+01, 10.0000
f3=3140.000000, 3.140000e+03, 3140.00
d=1.234568
d=  1.2345678900
d=001.2345678900
[12345678901234567890]
[www.codechobo.com]
[   www.codechobo.com]
[www.codechobo.com   ]
[www.]
 */
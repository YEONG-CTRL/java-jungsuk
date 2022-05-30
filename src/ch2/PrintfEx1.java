package ch2;

class PrintfEx1 {
    public static void main(String[] args) {
        byte b = 1;
        short s = 2;
        char c = 'A';

        int finger = 10;
        long big = 100_000_000_000L;
        long hex = 0xFFFF_FFFF_FFFF_FFFFL;

        int octNum = 010;
        int hexNum = 0x10;
        int binNum = 0b10;

        System.out.printf("b=%d%n", b);
        System.out.printf("s=%d%n", s);
        System.out.printf("c=%c, %d%n", c, (int)c); // A의 유니코드 값 65가 두번째로 출력 // char타입의 값을 지시자 %d로 출력하기 위해 형변환
        System.out.printf("finger=[%5d]%n", finger); // [   10] // 출력될 값이 차지할 공간 숫자로 지정
        System.out.printf("finger=[%-5d]%n", finger); // [10   ]
        System.out.printf("finger=[%05d]%n", finger); // [00010]
        System.out.printf("big=%d%n", big);
        System.out.printf("hex=%#X%n", hex); // #은 접두사(16진수는 0x가 붙고, 8진수는 0) / 대문자%X는 FFFFFF
        System.out.printf("hex=%#x%n", hex); // 소문자 %x는 fffff
        System.out.printf("octNum=%#o, %d%n", octNum, octNum); // 8진수 10, 10진수 8
        System.out.printf("hexNum=%x, %d%n", hexNum, hexNum); // 16진수 10, 10진수 16
        System.out.printf("binNum=%s, %d%n", Integer.toBinaryString(binNum),binNum); // 2진수 10,  10진수 2
        // 10진수를 2진수로 출력해주는 지시자는 없다. 정수를 2진 문자열로 변환해주는 Integer.toBinaryString(int i)
    }
}

/*
결과
b=1
s=2
c=A, 65
finger=[   10]
finger=[10   ]
finger=[00010]
big=100000000000
hex=0XFFFFFFFFFFFFFFFF
hex=0xffffffffffffffff
octNum=010, 8
hexNum=10, 16
binNum=10, 2
 */
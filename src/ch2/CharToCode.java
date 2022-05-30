package ch2;

class CharToCode {
    public static void main(String[] args) {
        char ch = 'A';
        int code = (int)ch;

        System.out.printf("%c=%d(%#x)%n", ch , code, code); // A의 유니코드 65(16진수로 0x41)

        char hch = '가';
        System.out.printf("%c=%d(%#X)%n", hch, (int)hch, (int)hch); // 가 의 유니코드 44032(16진수로 OxAC00)
    }
}

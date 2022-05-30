package ch3;

public class UnaryOperator {
    public static void main(String[] args) {
        int i = 5, j = 6;
        j = i++; // i가 참조하여 j에 대입된 후, 증가됨
        System.out.println("j=i++; 실행 후, i= " + i + ", j= " + j);

        i = 5;
        j = 0;

        j = ++i;
        System.out.println("j=i++; 실행 후, i= " + i + ", j= " + j);
    }
}

class UnaryEx2 {
    public static void main(String[] args) {
        int i = 5, j = 5;
        System.out.println(i++); //5
        System.out.println(++j); //6
        System.out.println("i = " + i + ", j = " + j); //6,6
    }
}


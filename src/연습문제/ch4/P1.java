package 연습문제.ch4;

/* 4_1
1. x > 10 && x < 20
2. ch != " " && ch != "\t"
3. ch == 'x' || ch == 'X'
4. ch >= '0' || ch <= '9'
5. (ch >= 'a' || ch <= 'z') || (ch >= 'A' || ch <= 'Z')
6. (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)
7. powerOn == false
8. str == "yes" 가 아닌, 참조변수이기에 내용을 비교해줘야함 str.equals("yes")
 */

public class P1 { // 4_2
    public static void main(String[] args) {
        int sum = 0;

        for (int i=1; i<21; i++) {
            if (i % 2 != 0 && i % 3 != 0)
                sum += i;
        }

        System.out.println(sum);
    }
}

class p2 { // 4_3
    public static void main(String[] args) {
        int sum = 0;

        for (int i=1; i<11; i++) {
            int a = madeSum(i);
            sum += a;
        }

        System.out.println(sum);
    }

    static int madeSum(int j) {
        int tmp = 0;

        for (int i=1; i <=j; i++)
            tmp += i;

        return tmp;
    }
}

class p3 { // 4_4
    public static void main(String[] args) {
        int num   = 0;
        int s     = 1;
        int sum   = 0 ;

        for (int i = 1; sum < 100; i++, s = -s) {
            num = i * s;
            sum += num;
        }
        System.out.println("num= " + num);
        System.out.println(sum);
    }
}

class p4 {
    public static void main(String[] args) {
        int i = 0;
        while (i<=10) {
            int j = 0;
            while (j <= i) {
                System.out.print("*");
                j++;
            }
            System.out.println();
            i++;
        }
    } // end of main
} // end of class


class p5 { // 4_6
    public static void main(String[] args) {
        for (int i=1; i<=6; i++){
            for (int j=1; j<=6; j++){
                if (i+j == 6) {
                    System.out.println(i + "+" + j + "=" + (i+j));
                }
            }
        }
    }

}

class p6 { // 4_7
    public static void main(String[] args) {
        int value = (int)(Math.random() * 6) + 1;
        System.out.println("value:"+value);
    }
}
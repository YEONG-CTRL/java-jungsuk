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

class p8 {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            for (int j=0; j<=10; j++) {
                if (2*i + 4 * j == 10){
                    System.out.println("x="+i + ", y="+ j);
                }
            }
        }
    }
}

class p9 {
    public static void main(String[] args) {
        String str = "12345";
        int sum = 0;
        for(int i=0; i < str.length(); i++) {
            sum += str.charAt(i)- '0';
        }
        System.out.println("sum="+sum);
    }
}

class p10 {
    public static void main(String[] args) {
// Fibonnaci 수열의 시작의 첫 두 숫자를 1, 1로 한다.
        int num1 = 1;
        int num2 = 1;
        int num3 = 0; // 세번째 값
        System.out.print(num1+","+num2);
        for (int i = 0 ; i < 8 ; i++ ) {
            num3 = num1 + num2;
            System.out.print("," + num3);
            num1 = num2;
            num2 = num3;
        }
    } // end of main
} // end of class

class p11 {
    public static void main(String[] args) {
        for (int i=1; i<=9; i++) {
            for (int j=1; j<=3; j++) {
                int x = (j+1) +(i-1)/3*3;   // 앞자리 단을 의미하는 변수 x
                int y = i % 3==0 ? 3 : i%3; // 뒷자리 1~3 숫자를 의미하는 변수 y

                if (x>9)
                    break;

                System.out.print(x+"*"+y+"="+ x*y+"\t");
            }
            System.out.println();
            if(i%3 ==0) System.out.println();
        }
    }
}

class p12 {
    public static void main(String[] args)
    {
        String value = "12o34";
        char ch = ' ';
        boolean isNumber = true;
// 반복문과 charAt(int i)를 이용해서 문자열의 문자를
// 하나씩 읽어서 검사한다.
        for(int i=0; i < value.length() ;i++) {
            if (48 >= value.charAt(i) || value.charAt(i) >= 57) {
                isNumber = false;
                break;
            }
        }
        if (isNumber) {
            System.out.println(value+"는 숫자입니다.");
        } else {
            System.out.println(value+"는 숫자가 아닙니다.");
        }
    } // end of main
} // end of class

class p13
{
    public static void main(String[] args)
    {
// 1~100사이의 임의의 값을 얻어서 answer에 저장한다.
        int answer = (int)(Math.random() * 100) + 1;
        int input = 0; // 사용자입력을 저장할 공간
        int count = 0; // 시도횟수를 세기위한 변수
// 화면으로 부터 사용자입력을 받기 위해서 Scanner클래스 사용
        java.util.Scanner s = new java.util.Scanner(System.in);
        do {
            count++;
            System.out.print("1과 100사이의 값을 입력하세요 :");
            input = s.nextInt(); // 입력받은 값을 변수 input에 저장한다.

            if (input == answer) {
                System.out.println("맞췄습니다");
                System.out.printf("시도횟수는 %d 번입니다", count);
                break;
            } else if (input < answer) {
                System.out.println("더 큰값을 입력하세요");
            }
            else {
                System.out.println("더 작은 값을 입력하세요");
            }
        } while(true); // 무한반복문
    } // end of main
} // end of class HighLow

class p14
{
    public static void main(String[] args)
    {
        int number = 12321;
        int tmp = number;
        int result =0; // 변수 number를 거꾸로 변환해서 담을 변수
        while(tmp !=0) {
            result  = result * 10 + tmp % 10;
            tmp /= 10;
        }
        if(number == result)
            System.out.println( number + "는 회문수 입니다.");
        else
            System.out.println( number + "는 회문수가 아닙니다.");
    } // main
}
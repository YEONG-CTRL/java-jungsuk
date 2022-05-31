package ch4;

import java.util.Scanner;

public class Loop {
    public static void main(String[] args) {
//        for (int i=1, j= 10; i<=10; i++,j--)
//            System.out.printf("%d \t %d%n",i,j);
        // 변수 하나로 더 간단하게 구현
        for (int i=1 ; i<=10; i++)
            System.out.printf("%d \t %d%n",i , 11-i);
    }
}

class ForEx {
    public static void main(String[] args) {
        for (int i=1; i<=5; i++) {
            for (int j=1 ; j<=10;j++) {
                System.out.print("*"); // 가로로 출력하기 위해서는 println대신 print 메서드 사용
            }
            System.out.println();
        }
    }
}

class ForEx2 { // 삼각형모양 별
    public static void main(String[] args) {
        int num = 0;

        System.out.print("*를 출력할 라인의 수를 입력");

        Scanner scanner = new Scanner(System.in);
        String tmp = scanner.nextLine();
        num = Integer.parseInt(tmp); // 입력받은 문자열을 숫자로 변환

        for (int i=0; i<num; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

class ForEx3 { // 구구단
    public static void main(String[] args) {
        for (int i=2; i<=9; i++) // 안쪽 for문 전체가 하나의 문장이므로 { } 생략 가능하다
            for (int j=1; j<=9; j++)
                System.out.printf("%d x %d = %d%n", i,j, i*j);
    }
}

class ForEx4 {
    public static void main(String[] args) { // 3*3*3실행횟수
        for (int i=1; i<=3; i++)
            for (int j=1; j<=3; j++)
                for (int k=1; k<=3; k++)
                    System.out.println(""+i+j+k);
    }
}

class ForEx5 {
    public static void main(String[] args) {
        for (int i=1; i<=5; i++) {
            for (int j=1; j<=5; j++) {
                if (i == j) {
                    System.out.printf("[%d,%d]", i, j);
                } else {
                    System.out.printf("%5c", ' ');
                }
            }
            System.out.println();
        }
    }
}

class EnhancedForEx {
    public static void main(String[] args) {
        int[] arr = {10,20,30,40,50};
        int sum = 0;

        for (int i=0; i<arr.length; i++)
            System.out.printf("%d ", arr[i]);
        System.out.println();

        for (int tmp : arr) {
            System.out.printf("%d ", tmp);
            sum += tmp;
        }

        System.out.println();
        System.out.println("sum="+sum);
    }
}

class WhileEx {
    public static void main(String[] args) {
        int i = 5;

        while (i--!=0) { // --가 뒤에 붙었으므로, 조건식이 평가된 이후에 i의 값 감소
            System.out.println(i + " - I can do it.");
        }
//        while (--i!=0) {
//            System.out.println(i + " - I can do it.");
//        }
        // 이 경우 i가 감소된 이후 평가되기때문에, 0까지 가지 않는다
    }
}

class WhileEx2 {
    public static void main(String[] args) {
        int i = 11;

        System.out.println("카운트 다운 시작");

        while (i--!=0) {
            System.out.println(i);

            for (int j =0; j<2_000_000_000; j++) ;
            // 아무런 내용 없는 빈 문장, 그냥 2,000,000,000 반복하면서 시간을 보낼뿐임 -> 시간지연

        }
        System.out.println("Game Over");
    }
}

class WhileEx3 {
    public static void main(String[] args) {
        int num = 0, sum = 0;
        System.out.print("숫자를 입력하세요. (예:12345)>");

        Scanner scanner = new Scanner(System.in);
        String tmp = scanner.nextLine();
        num = Integer.parseInt(tmp);

        while(num!=0) {

            sum += num%10;
            System.out.printf("sum=%3d num=%d%n", sum, num);

            num /= 10;
        }

        System.out.println("각 자릿수의 합 : " +sum);
    }
}

class WhileEx4 {
    public static void main(String[] args) {
        int sum = 0;
        int i   = 0;

        while ((sum += ++i) <= 100) {
            System.out.printf("%d - %d%n", i, sum);
        }
    }
}

class WhileEx5 {
    public static void main(String[] args) {
        int num;
        int sum = 0;
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("합계를 구할 숫자를 입력하세요.(끝내려면 0 입력");

        while (flag) { //flag의 값이 true이므로 조건식은 참이 된다.
            System.out.print(">>");

            String tmp = scanner.nextLine();
            num = Integer.parseInt(tmp);

            if (num!=0) {
                sum += num;
            } else {
                flag = false; // 조건문 false돼서 반복 멈춤
            }
        }
        System.out.println("합계: "+sum);
    }
}

class WhileEx6 {
    public static void main(String[] args) {
        int input = 0, answer = 0;
        
        answer = (int) (Math.random() * 100) +1;
        Scanner scanner = new Scanner(System.in);
        
        do {
            System.out.println("1부터 100까지의 정수입력");
            input = scanner.nextInt();

            if (input > answer) {
                System.out.println("더 작은 수로 다시 시도하세요");
            } else if (input < answer) {
                System.out.println("더 큰 수로 다시 시도하세요");
            }
        } while (input != answer) ;

        System.out.println("정답입니다");
    } 
}

class WhileEx7 {
    public static void main(String[] args) {
        for (int i=1; i <= 100; i++) {
            System.out.printf("i=%d", i);

            int tmp = i; // for문 제어에 사용되는 i 말고 다른 변수 사용해서 각 자리숫자 확인

            do {
                if (tmp%10%3==0 && tmp % 10 != 0) // tmp가 3의 배수인지 확인(0제외)
                    System.out.print("짝"); // 둘 중 한 자리가 3의 배수면 짝
            } while ((tmp/=10) != 0);

            System.out.println();
        }
    }
}

class BreakEx {
    public static void main(String[] args) {
        int sum = 0;
        int i   = 0;

        while (true) {
            if(sum > 100) {
                System.out.println(sum);
                break;
            }
            ++i;
            sum += i;
        }

        System.out.println("i=" + i);
        System.out.println("sum=" + sum);
    }
}

class ContinueEx {
    public static void main(String[] args) {
        for (int i=0; i <= 10; i++) {
            if (i%3 == 0)
                continue;
            System.out.println(i);
        }
    }
}

class ContinueEx2 {
    public static void main(String[] args) {
        int menu = 0;
        int num  = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("(1) square");
            System.out.println("(2) square root");
            System.out.println("(3) log");
            System.out.print("원하는 메뉴(1~3)을 선택하세요. (종료0)>");

            String tmp = scanner.nextLine();
            menu = Integer.parseInt(tmp);

            if(menu==0) {
                System.out.println("프로그램을 종료합니다");
                break;
            } else if (!(1 <= menu && menu <= 3)) {
                System.out.println("메뉴를 잘못 선택하셨습니다.(종료는 0)");
                continue;
            }

            System.out.println("선택하신 메뉴는 " + menu + "번입니다");
        }
    }
}

class NamedLoop {
    public static void main(String[] args) {
        Loop1 : for(int i=2; i <= 9; i++) {
            for(int j=1; j <= 9; j++) {
                if(j==5)
//                    break Loop1; // 곱하기 loop를 벗어나기에 2*4까지만 수행
//                    break; // 뒤에 곱하는 반복문을 벗어나는 것이기에 1~9의 4까지의 곱셉만 수행
//                    continue; // 구구단에서 *5 만 수행되지 안ㅎ음
                System.out.println(i + "*" + j + "=" + i * j);
            }
            System.out.println();
        }
    }
}

class AdvancedConitnueEx2 {
    public static void main(String[] args) {
        int menu = 0;
        int num = 0;

        Scanner scanner = new Scanner(System.in);

        outer:
        while (true) {
            System.out.println("(1) square");
            System.out.println("(2) square root");
            System.out.println("(3) log");
            System.out.print("원하는 메뉴(1~3)을 선택하세요. (종료0)>");

            String tmp = scanner.nextLine();
            menu = Integer.parseInt(tmp);

            if (menu == 0) {
                System.out.println("프로그램을 종료합니다");
                break;
            } else if (!(1 <= menu && menu <= 3)) {
                System.out.println("메뉴를 잘못 선택하셨습니다.(종료는 0)");
                continue;
            }

            for (; ; ) {
                System.out.print("계산할 값을 입력하세요. (계산 종료:0, 전체 종료:99)>");
                tmp = scanner.nextLine();
                num = Integer.parseInt(tmp);

                if (num == 0)
                    break;
                if (num == 99)
                    break outer;

                switch (menu) {
                    case 1:
                        System.out.println("result = " + num * num);
                        break;
                    case 2:
                        System.out.println("result = " + Math.sqrt(num)); // 메뉴가 1이면 입력한 수의 제곱근 구하기
                        break;
                    case 3:
                        System.out.println("result = " + Math.log(num)); // 메뉴가 2면 입력한 수의 자연로그 구하기(밑이e)
                        break;
                }
            } // for(;;)
        } // while의 끝
    }
}
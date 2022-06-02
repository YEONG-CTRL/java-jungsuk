package ch5;

import java.util.Scanner;

public class DimensionArray {
    public static void main(String[] args) {
        int[][] score = {
                {100,100,100}
              , {20,20,20}
              , {30,30,30}
              , {40,40,40} };

        int sum = 0;

        for (int i=0; i< score.length; i++) {
            for (int j=0; j< score[i].length; j++)
                System.out.printf("score[%d][%d]=%d%n",i,j,score[i][j]);
        }

        for (int[] tmp : score) {
            for (int i : tmp) {
                sum += i;
            }
        }
        System.out.println("sum="+sum);
    }
}

class DimensionEx2 {
    public static void main(String[] args) {
        int[][] score = {
                            {100,100,100}
                            , {20,20,20}
                            , {30,30,30}
                            , {40,40,40}
                            , {50,50,50} };

        int korTotal = 0, engTotal = 0, mathTotal = 0;

        System.out.println("번호  국어  영어  수학  총점  평균");
        System.out.println("=============================");

        for (int i =0; i< score.length; i++) {
            int sum = 0;
            float avg = 0.0f;

            korTotal += score[i][0];
            engTotal += score[i][1];
            mathTotal += score[i][2];
            System.out.printf("%3d", i + 1);

            for (int j=0; j < score[i].length; j++) {
                sum += score[i][j];
                System.out.printf("%5d", score[i][j]);
            }

            avg = sum/(float)score[i].length;
            System.out.printf("%5d %5.1f%n", sum , avg);
        }

        System.out.println("=============================");
        System.out.printf("총점:%3d %4d %4d%n", korTotal, engTotal, mathTotal);
    }
}

class DimensionEx3 {  // 입력한 좌표의 위치에 x를 표시
    public static void main(String[] args) {
        final int SIZE = 10;
        int x = 0, y = 0;

        char[][] board = new char[SIZE][SIZE]; // 입력한 좌표를 표시. 행번호와 열번호 포함
        byte[][] shipBoard = {                 // 상대방의 배의 위치를 저장
          //     1 2 3 4 5 6 7 8 9
                {0,0,0,0,0,0,1,0,0},
                {1,1,1,1,0,0,1,0,0},
                {0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,0,0},
                {1,1,0,1,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,1,1,0},
        };
        for(int i=1; i<SIZE;i++)
            board[0][i] = board[i][0] = (char) (i+'0'); // 첫행, 첫열은 행번호,열번호로 / '0'을 더해주는 이유는 char배열이기 때문에 문자로 변환해야하기 때문임

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.printf("좌표를 입력하세요. (종료는 00)>");
            String input = scanner.nextLine();// 좌표입력받음

            if (input.length() == 2) {
                x = input.charAt(0) - '0';  // 첫번째 x 좌표값
                y = input.charAt(1) - '0';  // 첫번째 y 좌표값

                if (x==0 && y==0) // 00 이면 종료
                    break;
            }

            if (input.length() !=2 || x<=0 || x>=SIZE || y<=0 || y>=SIZE) { // 범위를 벗어나거나, 두개의 좌표가 오지 않은 경우
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                continue;
            }

            board[x][y] = shipBoard[x-1][y-1] == 1? 'O':'X';  // shipboard가 board보다 한칸씩 작다, shipboard가 1일경우면 O

            for (int i=0; i<SIZE; i++)
                System.out.println(board[i]); // char배열이라 한줄로출력됨
            System.out.println();
        }
    }
}

class DimensionEx4 { // 빙고판을 만들고 입력받은 숫자를 빙고판에서 지운다
    public static void main(String[] args) {
        final int SIZE = 5;
        int x = 0, y = 0, num = 0;

        int [][] bingo = new int[SIZE][SIZE];
        Scanner scanner = new Scanner(System.in);

        for (int i=0; i<SIZE; i++)
            for (int j=0; j<SIZE; j++)
                bingo[i][j] = i * SIZE + j + 1; // 배열의 모든 요소 1~25까지로 초기화

        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                x = (int) (Math.random() * SIZE);
                y = (int) (Math.random() * SIZE);

                int tmp = bingo[i][j]; 
                bingo[i][j] = bingo[x][y];
                bingo[x][y] = tmp; // 배열에 저장된 값들 뒤섞음
            }
        }

        do {
            for (int i=0; i<SIZE; i++) {
                for (int j=0; j<SIZE; j++) {
                    System.out.printf("%2d ", bingo[i][j]);
                }
                System.out.println();
            } // 빙고판 출력

            System.out.printf("1~%d 사이의 숫자를 입력하세요. (종료:0)>", SIZE*SIZE);
            String tmp = scanner.nextLine();
            num = Integer.parseInt(tmp); // 입력받은 값을 숫자로 변환

            outer: 
            for (int i= 0; i < SIZE; i++) { 
                for (int j = 0; j < SIZE; j++) {
                    if(bingo[i][j] == num) {
                        bingo[i][j] = 0; // 입력받은 값에 해당하는 값 있으면 0 만들고, 반복문(outer)탈출
                        break outer;
                    }
                }
            }
        } while (num!=0); // 0 입력하면 나가는 것
    }
}

class DimensionEx5 {
    public static void main(String[] args) {
        int[][] m1 = {
                {1,2,3},
                {4,5,6}
        };

        int[][] m2 = {
                {1,2},
                {3,4},
                {5,6}
        };

        final int ROW = m1.length; // 2
        final int COL = m2[0].length; // 2
        final int M2_ROW = m2.length; // 3

        int[][] m3 = new int[ROW][COL]; // 2행 2열의 m3

        for(int i=0; i<ROW; i++) // 0~1
            for (int j=0; j<COL;j++) // 0~1
                for(int k=0; k<M2_ROW; k++) // 3
                    m3[i][j] += m1[i][k] * m2[k][j];

        for(int i=0; i<ROW;i++) {
            for (int j=0; j<COL; j++) {
                System.out.printf("%3d ", m3[i][j]);
            }
            System.out.println();
        }
    }
}


class DimensionEx6 {
    public static void main(String[] args) {
        String[][] words = {
                {"chair","의자"},
                {"computer","컴퓨터"},
                {"integer","정수"}
        };

        Scanner scanner = new Scanner(System.in);

        for (int i=0; i<words.length; i++) {
            System.out.printf("Q%d. %s의 뜻은?", i + 1, words[i][0]);

            String tmp = scanner.nextLine();

            if (tmp.equals(words[i][1])) {
                System.out.printf("정답입니다. %n%n");
            } else {
                System.out.printf("틀렸습니다, 정답은 %s입니다. %n%n", words[i][1]);
            }
        }
    }
}
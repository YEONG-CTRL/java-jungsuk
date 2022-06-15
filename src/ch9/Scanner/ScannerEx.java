package ch9.Scanner;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class ScannerEx {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] argArr = null;

        while(true) {
            String prompt = ">>";
            System.out.println(prompt);

            String input = s.nextLine();
            input = input.trim(); // 입력받은 값에서 앞뒤 공백 제거한다
            argArr = input.split(" +"); // 입력받은 내용의 공백을 구분자로 자른다(정규식의 의미는 "하나이상의 공백")

            String command = argArr[0].trim();

            if("".equals(command)) continue;

            command = command.toLowerCase();

            if (command.equals("q")) { // 끝내고 싶으면 q
                System.exit(0);
            } else {
                for (int i=0; i < argArr.length; i++)
                    System.out.println(argArr[i]);
            }
        }
    }
}

class ScannerEx2 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:\\Users\\PARK\\IdeaProjects\\java-jungsuk\\src\\ch9\\Scanner\\data2.txt"));
        int sum = 0;
        int cnt = 0;

        while (sc.hasNextInt()) {
            sum += sc.nextInt();
            cnt ++;
        }

        System.out.println("sum ="+sum);
        System.out.println("average ="+ (double)sum/cnt);
    }
}

class ScannerEx3 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:\\Users\\PARK\\IdeaProjects\\java-jungsuk\\src\\ch9\\Scanner\\data3.txt"));
        int cnt = 0;
        int totalSum = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine(); // 라인별로 읽는다
            Scanner sc2 = new Scanner(line).useDelimiter(","); // , 를 구분자로 하는 scanner
            int sum = 0;

            while(sc2.hasNextInt()) {
                sum += sc2.nextInt();
            }

            System.out.println(line + ", sum = " + sum);
            totalSum += sum;
            cnt ++;
        }
        System.out.println("Line: " + cnt + ", Total :" + totalSum);
    }
}
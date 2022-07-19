package ch15;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class StandardIOEx {
    public static void main(String[] args) {
        try {
            int input = 0;

            while((input = System.in.read()) != -1) {
                System.out.println("input :"+ input + ", (char)input :" + (char)input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class StandardIOEx2 {
    public static void main(String[] args) {
        System.out.println("out : Hello World!");
        System.err.println("out : Hello World!");
    }
}

class StandardIOEx3 {
    public static void main(String[] args) {
        PrintStream ps = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream("C:\\Users\\PARK\\IdeaProjects\\java-jungsuk\\src\\ch15\\test.txt");
            ps  = new PrintStream(fos);
            System.setOut(ps); // System.out의 출력소스를 test.txt파일로 변경
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }

        System.out.println("Hello by System.out"); // test.txt파일에 저장
        System.err.println("Hello by System.err");
    }
}
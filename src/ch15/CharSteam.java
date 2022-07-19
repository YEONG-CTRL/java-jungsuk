package ch15;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class FilereaderEx1 {
    public static void main(String[] args) {
        try {
            String fileName = "C:\\Users\\PARK\\IdeaProjects\\java-jungsuk\\src\\ch15\\test.txt";
            FileInputStream fis = new FileInputStream(fileName);
            FileReader fr = new FileReader(fileName);

            int data = 0;
            while ((data = fis.read()) != -1) {
                System.out.print((char) data); // 한글이깨져서 출력된다
            }
            System.out.println();
            fis.close();

            while ((data = fr.read()) != -1)
                System.out.print((char) data); // reader가 파일의 인코딩을 읽어서 유니코드로 변환해주어서 한글이깨지지 않는다
            System.out.println();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


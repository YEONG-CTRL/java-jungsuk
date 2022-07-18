package ch15;

import java.io.FileInputStream;
import java.io.IOException;

public class FileViewer {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]); //
        int data = 0;

        while ((data = fis.read()) != -1) { // 입력받은 파일의 내용을 읽어서 화면에 출력
            // read()의 반환값이 4byte(int)이지만, -1을 제외하고는 0~255사이의(1byte) 정수값이기에 char로 변환해도 손실되는 값 없다
            char c = (char)data;
            System.out.print(c);
        }
    }
}


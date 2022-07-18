package ch15;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            FileOutputStream fos = new FileOutputStream(args[1]);

            int data = 0;
            while((data=fis.read()) != -1) { // args[0]파일의 내용을 read로 읽어서
                fos.write(data); // FileCopy.bak에  그대로 출력
            }

            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

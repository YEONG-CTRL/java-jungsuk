package 연습문제.ch15;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class HexaViewer {
    public static void main(String[] args) throws IOException {
        if(args.length !=1) {
            System.out.println("USAGE  : java HexaViewer FILENAME");
            System.exit(0);
        }

        String inputFile = args[0];

        try {
            FileInputStream input = new FileInputStream(inputFile);
            PrintStream output = new PrintStream(System.out);
            // System.out이 PrintStream이기 때문에 굳이 PrintStream 따로 생성할 필요 없으나, 출력대상이 화면이 아니라 파일로 바뀐다면 System.out 만 고쳐주면 된다는 장점있다.

            int data = 0;
            int i = 0;

            while((data = input.read()) != -1) {
                output.printf("%02X\t", data); // 빈자리를 0으로 채우는 2자리 16진수
                if(++i%16==0) // 16자리마다 줄바꿈
                    output.println();
            }

            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

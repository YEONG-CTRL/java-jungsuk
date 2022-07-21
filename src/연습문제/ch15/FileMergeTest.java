package 연습문제.ch15;

import java.io.*;

public class FileMergeTest {
    public static void main(String[] args) {
        if (args.length <= 1) {
            System.out.println("USAGE: java FileMergeTest MERGE_FILENAME FILENAME1 FILENAME2 ...");
            System.exit(0);
        }

        String MergeFilename = args[0];

        try {
            File newFile = new File(MergeFilename);
            newFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(newFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            BufferedInputStream bis = null;

            for (int i = 1; i < args.length; i++) {
                File f = new File(args[i]); // 새로운 파일 생성x, createNewFile() 없이는 기존 파일 참조하게 됨

                if (f.exists()) {

                    f.setReadOnly(); // 작업중에 파일의 내용이 변경되지 않도록 한다

                    bis = new BufferedInputStream(new FileInputStream(f));

                    int data = 0;
                    while ((data = bis.read()) != -1) {
                        bos.write(data);
                    }
                    bos.write("\r\n".getBytes()); // FileOutputStream에 값을 쓸때에는 byte배열로 써야하기 때문에
                    // String을 byte배열로 바꿔주는 getBytes()사용
                    bis.close();
                }
            }
            bos.flush(); // flush로 버퍼에있는 모든 내용출력(close()를 써줘도 자동으로 flush()함)

            }catch(IOException e){
        }
    }
}




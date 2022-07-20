package ch15;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileEx {
    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("test.dat", "rw");
            System.out.println("파일 포인터의 위치:" + raf.getFilePointer()); //0
            raf.writeInt(100); // 4byte
            System.out.println("파일 포인터의 위치:" + raf.getFilePointer());// 4
            raf.writeLong(100L); // 8byte
            System.out.println("파일 포인터의 위치:" + raf.getFilePointer()); // 12
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class RandomAccessFileEx2 {
    public static void main(String[] args) {

        int[] score = {1, 100, 90, 90,
                       2, 70, 90, 100,
                       3, 100, 100, 100,
                       4, 70, 60, 80,
                       5, 70, 90, 100};

        try {
            RandomAccessFile raf = new RandomAccessFile("score2.dat", "rw");
            for (int i =0; i< score.length; i++) {
                raf.writeInt(score[i]);
            } // writeInt를 수행하면서 파일포인터의 위치가 파일 마지막으로 이동되어서 readInt()는 파일의 마지막부분부터 읽음(아무것도 못읽는다)
            raf.seek(0); // 따라서 seek(0)을 통해 포인터의 위치를 처음으로 이동

            while (true) {
                System.out.println(raf.readInt());
            }
        } catch (EOFException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class RandomAccessFileEx3 {
    public static void main(String[] args) {
        int sum = 0;

        try {
            RandomAccessFile raf = new RandomAccessFile("score2.dat", "r");
            int i = 4;

            while (true) {
                raf.seek(i); // 4byte(첫 국어)
                sum += raf.readInt();
                i += 16; // 다음 국어로 넘어감
            }
        } catch (EOFException e) {
            System.out.println("sum : " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package ch15;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

class IOEx1 {
    public static void main(String[] args) {
        byte[] inSrc = {0,1,2,3,4,5,6,7,8,9}; // 바이트 배열은 사용하는 자원이 메모리밖에 없으므로 가바지컬렉터가 자동으로 자원을 반환, close()로 스트림 닫을 필요 x
        byte[] outSrc = null;

        ByteArrayInputStream input = null;
        ByteArrayOutputStream output = null;

        input  = new ByteArrayInputStream(inSrc);
        output = new ByteArrayOutputStream();

        int data = 0;

        while ((data = input.read()) != -1) { // read()를 호출한 반환값을 data에 저장한다,더이상 읽어올 값이없을때까지 읽어온다.(-1)
            output.write(data); // 읽어온 값을 출력소스에 쓴다
        }

        outSrc = output.toByteArray();

        System.out.println("Input Source :" + Arrays.toString(inSrc));
        System.out.println("Output Source :" + Arrays.toString(outSrc));
    }
}

class IOEx2 {
    public static void main(String[] args) {
        byte[] inSrc  = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc = null;
        byte[] temp   = new byte[10];

        ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        input.read(temp,0, temp.length); // 읽어온 데이터를 temp에 담는다
        output.write(temp, 5, 5); // temp[5]부터 5개의 데이터를 write한다

        outSrc = output.toByteArray();

        System.out.println("Input Source :" + Arrays.toString(inSrc));
        System.out.println("temp :" + Arrays.toString(temp));
        System.out.println("Output Source :" + Arrays.toString(outSrc));
    }
}

class IOEx3 {
    public static void main(String[] args) {
        byte[] inSrc  = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc = null;
        byte[] temp   = new byte[4];

        ByteArrayInputStream  input = new ByteArrayInputStream(inSrc);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        System.out.println("Input Source :" + Arrays.toString(inSrc));

        try {
            while(input.available() > 0) { // 스트림으로부터 읽어올 수 있는 데이터가 있는동안
                input.read(temp);   // temp배열의 크기만큼 읽어서 temp를 채운다 , 이때 성능을 위해서 temp를 지우고 쓰는게 아니라, 기존의 내용 위에 덮어쓴다.
                output.write(temp); // temp에 저장된 모든 내용을 출력소스에 쓴다

                outSrc = output.toByteArray();
                printArrays(temp, outSrc);
            }
        } catch (IOException e) {} // read()나 write()가 IOException을 일으킬 수 있어서 예외처리 해줘야함
    }

    static void printArrays(byte[] temp, byte[] outSrc) {
        System.out.println("temp :" + Arrays.toString(temp));
        System.out.println("Output Source :" + Arrays.toString(outSrc));
    }
}

class IOEx4 {
    public static void main(String[] args) {
        byte[] inSrc  = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc = null;
        byte[] temp   = new byte[4];

        ByteArrayInputStream  input = new ByteArrayInputStream(inSrc);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            while (input.available() > 0) {
                int len = input.read(temp);
                output.write(temp, 0, len); // 출력할때 읽어온 데이터의 개수만큼만 쓰도록하여서 위 예제의 문제 해결
            }
        } catch(IOException e) {}

        outSrc = output.toByteArray();

        System.out.println("Input Source :" + Arrays.toString(inSrc));
        System.out.println("temp :" + Arrays.toString(temp));
        System.out.println("Output Source :" + Arrays.toString(outSrc));
    }
}


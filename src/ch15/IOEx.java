package ch15;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

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

class BufferedOutputSteamEx1 {
    public static void main(String[] args) {
        try {
            FileOutputStream fos     = new FileOutputStream("123.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos, 5);

            for (int i ='1'; i <= '9'; i++) {
                bos.write(i);
            }

//            fos.close(); // 이것만 가지고는 bos버퍼에 남아있는 6 7 8 9 가 출력되지 않는다
            bos.close();   // BufferedOutputStream을 닫아줌으로써 버퍼에 남아있는 모든 내용을 flush, 이때 FileOuputStream도 자동으로 닫힌다
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DataOutputStreamEx2 {
    public static void main(String[] args) {
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;

        byte[] result = null;

        try {
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            dos.writeInt(10);
            dos.writeFloat(20.0f);
            dos.writeBoolean(true);

            result = bos.toByteArray();

            String[] hex = new String[result.length];

            for(int i=0; i< result.length; i++) {
                if (result[i] <0 ) {
                    hex[i] = String.format("%02x", result[i] + 256);
                } else  {
                    hex[i] = String.format("%02x", result[i]);
                }
            }

            System.out.println("10진수 :" + Arrays.toString(result));
    //[0, 0, 0, 10, 65, -96, 0, 0, 1] : 0,0,0,10 은 WriteInt(10) , 두번째 4byte는 writeFloat(20.0f)에 의해 , 마지막 1은 writeBoolean(true)에 의해 출력
            System.out.println("16진수 :" + Arrays.toString(hex));

            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class DataOutputStreamEx1 {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            fos = new FileOutputStream("sample.dat");
            dos = new DataOutputStream(fos);
            dos.writeInt(10);
            dos.writeFloat(20.0f);
            dos.writeBoolean(true);
            // 출력한 값들은 이진 데이터로 저장되기에 sample.dat 열어봐도 알수없음

            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DataInputStreamEx1 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("sample.dat");
            DataInputStream dis = new DataInputStream(fis);

            System.out.println(dis.readInt()); // 쓰인 순서대로 읽어야 함
            System.out.println(dis.readFloat()); // 데이터를 변환할 필요없이 그냥 데이터 타입에 맞는 메서드만 사용하면돼서 편리
            System.out.println(dis.readBoolean());
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DataOutputStreamEx3 {
    public static void main(String[] args) {
        int[] score = {100,90,95,85,50};

        try {
            FileOutputStream fos = new FileOutputStream("score.dat");
            DataOutputStream dos = new DataOutputStream(fos);

            for(int i=0; i< score.length; i++) {
                dos.writeInt(score[i]);
            }

            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // type 명령어로 확인해보면 dZ_U2로 나타나지만, 이는 type 명령이 파일의 내용을 문자로 변환해서 보여주기 때문이다.
    // 실제 저장된 내용은 00 00 00 64(100)  00 00 00 5A(90) 00 00 00 55(85) 00 00 00 32(50) 이다.
    // int형 값을 16진수로 표현하여 저장한 것. , int하나가 4byte
}

class DataInputStreamEx2 {
    public static void main(String[] args) {
        int sum   = 0;
        int score = 0;

        FileInputStream fis = null;
        DataInputStream dis = null;

        try {
            fis = new FileInputStream("score.dat");
            dis = new DataInputStream(fis);

            while (true) { // while문 통해서 값 계속 읽다가
                score = dis.readInt();
                System.out.println(score);
                sum += score;
            }
        } catch (EOFException e) { // 더이상 읽을 데이터 없으면 EOFException 발생
            System.out.println("점수의 총합은 " + sum + "입니다.");
        } catch (IOException ie) {
            ie.printStackTrace();
        } finally {
            try {
                if(dis!=null) // dis가 null일때 close()를 호출하면 NullPointerException이 발생.
                    dis.close(); // while문에서 스트림을 닫을 수 없으니 finally에서 스트림을 닫게 처리함
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }
}

//위의 예제를 try-with-resource로 변환해보자!
class DataInputStreamEx3 {
    public static void main(String[] args) {
        int sum = 0;
        int score = 0;

        try (FileInputStream fis = new FileInputStream("score.dat"); DataInputStream dis = new DataInputStream(fis)) {
            // try()안에 복수의 자원객체를 전달한다. 이 자원들은 try블록이 끝나면 자동으로 종료되기에, 따로 finally 블록으로 종료처리 해줄 필요가 없다.
            while (true) {
                score = dis.readInt();
                System.out.println(score);
                sum += score;
            }
        } catch (EOFException e) {
            System.out.println("점수의 총합은 " + sum + "입니다.");
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}

class SequenceInputStreamEx {
    public static void main(String[] args) {
        byte[] arr1 = {0, 1, 2};
        byte[] arr2 = {3, 4, 5};
        byte[] arr3 = {6, 7, 8};
        byte[] outSrc = null;

        Vector v = new Vector();
        v.add(new ByteArrayInputStream(arr1));
        v.add(new ByteArrayInputStream(arr2));
        v.add(new ByteArrayInputStream(arr3));

        SequenceInputStream input    = new SequenceInputStream(v.elements()); // vector의 enumeration elements()
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        int data = 0;

        try {
            while ((data = input.read()) != -1) { // 3개의 ByteArrayInputStream을 하나의 입력스트림처럼 다룸
                output.write(data); // 읽어온 값 output에 쓴다
            }
        } catch (IOException e) {}

        outSrc = output.toByteArray();

        System.out.println("Input Source1 :" + Arrays.toString(arr1));
        System.out.println("Input Source2 :" + Arrays.toString(arr2));
        System.out.println("Input Source3 :" + Arrays.toString(arr3));
        System.out.println("Output Source :" + Arrays.toString(outSrc));
    }
}

class PrintStreamEx1 {
    public static void main(String[] args) {
        int i = 65;
        float f = 1234.56789f;

        Date d = new Date();

        System.out.printf("문자 %c의 코드는 %d%n", i, i); // %c에서는 65 유니코드에 해당하는 A가 출력
        System.out.printf("%d는 8진수로 %o, 16 진수로 %x%n",i, i, i);
        System.out.printf("%3d%3d%3d\n", 100, 90, 80);
        System.out.println();

        System.out.printf("123456789012345678901234567890%n");
        System.out.printf("%s%-5s%5s%n" ,"123", "123","123"); // -5s : 5자리 문자열, 왼쪽정렬
        System.out.println();

        System.out.printf("%-8.1f%8.1f %e%n",f,f,f); // %e : 지수형태표현
        System.out.println();
        System.out.printf("오늘은 %tY년 %tm월 %td일입니다. %n", d,d,d );
        System.out.printf("지금은 %tH시 %tM분 %tS초입니다. %n", d,d,d );
        System.out.printf("지금은 %1$tH시 %1$tM분 %1$tS초입니다. %n", d ); // 숫자$ 를 옵션 앞에 붙여주어서 출력될 매개변수 지정 가능,
        //1$ 는 첫번째 매개변수 의미
    }
}

class StringReaderWriterEx {
    public static void main(String[] args) {
        String inputData = "ABCD";
        StringReader input  = new StringReader(inputData);
        StringWriter output = new StringWriter();

        int data = 0;

        try {
            while ((data = input.read()) != -1) {
                output.write(data);
            }
        } catch (IOException e) {}

        System.out.println("Input Data : " + inputData);
        System.out.println("Output Data : " + output.toString()); // 내부 StringBuffer에 저장된것을 출력
//        System.out.println("Output Data : " + output.getBuffer().toString());
    }
}

class InputStreamReaderEx {
    public static void main(String[] args) {
        String line = "";

        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            System.out.println("사용중인 OS의 인코딩 :" + isr.getEncoding());

            do {
                System.out.print("문장을 입력하세요. 마치시려면 q를 입력하세요.>");
                line = br.readLine();
                System.out.println("입력하신 문장 : "+ line);
            } while(!line.equalsIgnoreCase("q"));

//            br.close();  System.in과 같은표준 입출력은 닫지 않아도 된다

            System.out.println("프로그램을 종료합니다");
        } catch (IOException e) {}
    }
}
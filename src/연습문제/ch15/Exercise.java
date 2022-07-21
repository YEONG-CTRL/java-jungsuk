package 연습문제.ch15;

import java.io.*;
import java.util.Scanner;


// FileHead.txt를 읽어보면, 중간중간 OD OA 가 반복된다. 이는 10진수로 13, 10 인데 이들은 아스키코드표에서 각각 CR과 LF이다
// 윈도우는 엔터를 CR + LF로 처리한다.(\r\n)
// \r은 캐리지리턴: 커서를 현재라인의 첫번재 컬럼으로 이동시킴
// \n 은 라인피드 : 커서를 다음줄로 이동시킴(new line)
class FileHead {
    public static void main(String[] args) {
        try {
            int lineNum = Integer.parseInt(args[0]);
            String fileName = args[1];

            File f = new File(fileName); // 파일 객체 먼저만들고

            if( f.exists() && !f.isDirectory()) { // 존재하는지, 디렉토리는 아닌지 확인
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);

                String line = "";
                int i = 1;

                while((line = br.readLine()) != null && i <= lineNum) { // br.readline의 유무, lineNum과 i의 비교로 반복횟수 정함
                    System.out.println(i + " : " + line);
                    i++;
                }
            } else { // 파일없거나 디렉토리면
                throw new FileNotFoundException(fileName +"은/는 디렉토리이거나,  존재하지 않는 파일입니다. ");
            }
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException e) {
            System.out.println("USAGE : java FileHead 10 FILENAME");
        }
    }
}

class Exercise15_5
{
    public static void main(String[] args)
    {
        if(args.length != 2) {
            System.out.println("USAGE: java Exercise15_5 TAGET_FILE RESULT_FILE");
                    System.exit(0);
        }
        String inputFile = args[0];
        String outputFile = args[1];
        try {
            BufferedReader input = new BufferedReader(new FileReader(inputFile));
            HtmlTagFilterWriter output = new HtmlTagFilterWriter(new FileWriter(outputFile));

            int ch = 0;
            while((ch=input.read())!=-1) {
                output.write(ch);
            }
            input.close();
            output.close();
        } catch(IOException e) {}
    }
}
class HtmlTagFilterWriter extends FilterWriter {
    StringWriter tmp = new StringWriter();
    boolean inTag = false;
    HtmlTagFilterWriter(Writer out) {
        super(out);
    }
    public void write(int c) throws IOException {
        if (c == '<') {
            inTag = true;
        } else if (c == '>' && inTag) {
            inTag = false;
            tmp = new StringWriter();
        }

        if (inTag) {
            tmp.write(c); // 태그가 시작되면 inTag true로 바꾸고 새로운 StringWriter생성해 tmp에 할당
            // 이를통해 기존의 태그는 없어진다
        } else if (!inTag) {
            out.write(c); // 태그가 아니니까 기반스트림에 출력
        }
    }
    public void close() throws IOException {
        out.write(tmp.toString()); // StringWriter의 내용을 출력하고(내용이 남아있을수 있기에)
        super.close(); // 조상의 close()를 호출해서 기반 스트림을 닫는다.
    }
}


class Exercise15_6 {
    static String[] argArr; // 입력한 매개변수를 담기위한 문자열배열
    static File curDir; // 현재 디렉토리
    static {
        try {
            curDir = new File(System.getProperty("user.dir"));
        } catch(Exception e) {}
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            try {
                String prompt = curDir.getCanonicalPath() + ">>";
                System.out.print(prompt);
// 화면으로부터 라인단위로 입력받는다.
                String input = s.nextLine();
                input = input.trim(); // 입력받은 값에서 불필요한 앞뒤 공백을 제거한다.
                argArr = input.split(" +");
                String command = argArr[0].trim();
                if("".equals(command)) continue;
                command = command.toLowerCase(); // 명령어를 소문자로 바꾼다.
                if(command.equals("q")) { // q 또는 Q를 입력하면 실행종료한다.
                    System.exit(0);
                } else if(command.equals("cd")) {
                    cd();
                } else {
                    for(int i=0; i < argArr.length;i++) {
                        System.out.println(argArr[i]);
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
                System.out.println("입력오류입니다.");
            }
        } // while(true)
    } // main
    public static void cd() {
        if(argArr.length==1) {
            System.out.println(curDir);
            return;
        } else if(argArr.length > 2) {
            System.out.println("USAGE : cd directory");
            return;
        }
        String subDir = argArr[1];

        if ("..".equals(subDir)) {
            File newDir = curDir.getParentFile();

            if(newDir==null) {
                System.out.println("유효하지 않은 디렉토리입니다");
            } else {
                curDir = newDir;
            }
        } else if (".".equals(subDir)) {
            System.out.println(curDir);
        } else {
            File newDir = new File(curDir, subDir);

            if (newDir.exists() && newDir.isDirectory()) {
                curDir = newDir;
            } else{
                System.out.println("유효하지 않은 디렉토리입니다");
            }
        }
    } // cd()
}
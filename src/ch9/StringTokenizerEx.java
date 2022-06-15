package ch9;


import java.util.*;

public class StringTokenizerEx {
    public static void main(String[] args) {
        String source = "100,200,300,400";
        StringTokenizer st = new StringTokenizer(source, ",");

        while(st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }
}

class StringTokenizerEx2 {
    public static void main(String[] args) {
        String expression = "x=100*(200+300)/2";
        StringTokenizer st = new StringTokenizer(expression, "+-*/=()", true); // 각각의 delim이 하나의 구분자

        while(st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }
}

class StringTokenizerEx3 {
    public static void main(String[] args) {
        String source = "1,김천재,100,100,100|2,박수재,95,80,90|3,이자바,80,90,90";
        StringTokenizer st = new StringTokenizer(source, "|"); // 한학생의 정보 | 로 구분

        while(st.hasMoreTokens()) {
            String token = st.nextToken();

            StringTokenizer st2 = new StringTokenizer(token, ","); // 그 안에서 쉼표로 구분
            while(st2.hasMoreTokens())
                System.out.println(st2.nextToken());

            System.out.println("----");
        }
    }
}

class StringTokenizerEx4 {
    public static void main(String[] args) {
        String input = "삼십만삼천오백십오";
        System.out.println(input);
        System.out.println(hangulToNum(input));
    }

    public static long hangulToNum(String input) {
        long result = 0; // 최종 변환결과 저장
        long tmpResult = 0; // 십,백,천 단위의 값을 저장하기 위한 임시변수
        long num = 0;

        final String NUMBER = "영일이삼사오육칠팔구";
        final String UNIT = "십백천만억조";
        final long[] UNIT_NUM = {10,100,1000,10000,(long)1e8,(long)1e12}; ;// 십백천만억조

        StringTokenizer st = new StringTokenizer(input, UNIT, true); //구분자단위로 자른다

        while(st.hasMoreTokens()) { // 토큰이 숫자면 num에 저장, 단위면 num*단위 곱해서 tmpResult에 저장
            String token = st.nextToken();
            int check = NUMBER.indexOf(token);  // 숫자에 속하는지(아니면 단위) 확인
            if(check ==-1) { // 단위인 경우
                if ("만억조".indexOf(token) == -1) {  //
                    tmpResult += (num != 0 ? num : 1) * UNIT_NUM[UNIT.indexOf(token)];
                    // 단위로 바로 시작하는 경우 num값 0이기에 삼항 연산자로 num값 1로 변경
                } else { // token이 만 억 조(큰 단위)인 경우
                    tmpResult += num; //tmpResult에 저장된 값에
                    result += (tmpResult != 0 ? tmpResult : 1) * UNIT_NUM[UNIT.indexOf(token)]; /// 큰 단위 값 곱해 result에 저장
                    tmpResult = 0;
                }
                num = 0;
            } else { // 숫자인 경우
                num = check; // num에 저장
            }
        }
        return result+tmpResult+num;
    }
}

class StringTokenizerEx5 {
    public static void main(String[] args) {
        String data = "100,,,200,300";

        String[] result = data.split(",");
        StringTokenizer st = new StringTokenizer(data, ",");

        for (int i=0; i< result.length; i++)
            System.out.print(result[i]+"|");

        System.out.println("개수:" + result.length); // split()은 빈 문자여로 토큰으로 인식

        int i=0;
        for(;st.hasMoreTokens(); i++)
            System.out.print(st.nextToken() +"|"); // 빈문자열을 토큰으로 인식하지 않음
        System.out.println("개수" +i);
    }
}
package 연습문제.ch9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Exercise {
    public static void main(String[] args) {
        SutdaCard c1 = new SutdaCard(3,true);
        SutdaCard c2 = new SutdaCard(3,true);
        System.out.println("c1="+c1);
        System.out.println("c2="+c2);
        System.out.println("c1.equals(c2):"+c1.equals(c2));
    }
}
class SutdaCard {
    int num;
    boolean isKwang;
    SutdaCard() {
        this(1, true);
    }
    SutdaCard(int num, boolean isKwang) {
        this.num = num;
        this.isKwang = isKwang;
    }
    public boolean equals(Object obj) {
        if(obj instanceof SutdaCard)
            return (num == ((SutdaCard) obj).num) && (isKwang == ((SutdaCard) obj).isKwang);
        else
            return false;
    }
    public String toString() {
        return num + ( isKwang ? "K":"");
    }
}

class Exercise2 {
    public static void main(String[] args) {
        Point3D p1 = new Point3D(1,2,3);
        Point3D p2 = new Point3D(1,2,3);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println("p1==p2?"+(p1==p2));
        System.out.println("p1.equals(p2)?"+(p1.equals(p2)));
    }
}
class Point3D {
    int x,y,z;
    Point3D(int x, int y, int z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }
    Point3D() {
        this(0,0,0);
    }
    public boolean equals(Object obj) {
        if (obj instanceof Point3D) {
            return ((Point3D) obj).x == x && ((Point3D) obj).y == y && ((Point3D) obj).z == z;
        } else {
            return false;
        }
    }
    public String toString() {
        return "[" + x + "," + y + "," + z + "]";
    }
}

class Exercise9_3 {
    public static void main(String[] args) {
        String fullPath = "c:\\jdk1.8\\work\\PathSeparateTest.java";
        String path = "";
        String fileName = "";

        if(fullPath.lastIndexOf("\\") != -1) {
            path += fullPath.substring(0, fullPath.lastIndexOf("\\"));
            fileName += fullPath.substring(fullPath.lastIndexOf("\\") + 1);
        }

        System.out.println("fullPath:"+fullPath);
        System.out.println("path:"+path);
        System.out.println("fileName:"+fileName);
    }
}

class Exercise4 {
    static void printGraph(int[] dataArr, char ch) {
        for (int i=0; i < dataArr.length; i++) {
            for (int j=0; j<dataArr[i];j++)
                System.out.print(ch);
            System.out.println(dataArr[i]);
        }

    }
    public static void main(String[] args) {
        printGraph(new int[]{3,7,1,4},'*');
    }
}

class Exercise5 {
    public static int count(String src, String target) {
        int count = 0; // 찾은 횟수
        int pos = 0; // 찾기 시작할 위치

        while (true) {
            pos = src.indexOf(target, pos);
            if (pos == -1) {
                return count;
            } else {
                count += 1;
                pos   += target.length();
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(count("12345AB12AB345AB","AB"));
        System.out.println(count("12345","AB"));
    }
}

class Exercise6 {
    public static String fillZero(String src, int length) {
        if (src == null || src.length() == length) return src;
        if (length <= 0) return "";

        if (src.length() > length) return src.substring(0,length);

        char[] tmp = new char[length];
        for (int i=0; i< tmp.length ; i++) {
            tmp[i] = '0';
        }

        char[] sch = src.toCharArray();

        System.arraycopy(sch,0,tmp,tmp.length-sch.length, sch.length);
        return new String(tmp);
    }
    public static void main(String[] args) {
        String src = "12345";
        System.out.println(fillZero(src,10));
        System.out.println(fillZero(src,-1));
        System.out.println(fillZero(src,3));
    }
}

class Exercise7 {
    public static boolean contains(String src, String target) {
        int ans = src.indexOf(target);
        return ans != -1;
    }
    public static void main(String[] args) {
        System.out.println(contains("12345","23"));
        System.out.println(contains("12345","67"));
    }
}

class Exercise8 {
    public static double round(double d, int n) {
        d = Math.round(d*Math.pow(10,n)) / Math.pow(10,n);
        return d;
    }
    public static void main(String[] args) {
        System.out.println(round(3.1415,1));
        System.out.println(round(3.1415,2));
        System.out.println(round(3.1415,3));
        System.out.println(round(3.1415,4));
        System.out.println(round(3.1415,5));
    }
}

class Exercise9_9 {
    public static String delChar(String src, String delCh) {
        StringBuffer sb = new StringBuffer(src.length());
        for (int i = 0; i < src.length(); i++) {
            char ch = src.charAt(i);

            if (delCh.indexOf(ch) == -1)
                sb.append(ch);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println("(1!2@3^4~5)"+" -> "
                + delChar("(1!2@3^4~5)","~!@#$%^&*()"));
        System.out.println("(1 2 3 4\t5)"+" -> "
                + delChar("(1 2 3 4\t5)"," \t"));
    }
}


class Exercise9_10
{
    public static String format(String str, int length, int alignment) {
        if (str.length() > length) {
            return str.substring(0,length);
        }

        char[] str2 = str.toCharArray();
        char[] tmp = new char[length];
        Arrays.fill(tmp,' ');

        switch (alignment) {
            case 0:
            default:
                System.arraycopy(str2, 0, tmp, 0, str.length());
                break;
            case 1:
                System.arraycopy(str2, 0, tmp, (tmp.length - str.length()) / 2, str.length());
                break;
            case 2:
                System.arraycopy(str2, 0, tmp, tmp.length - str.length(), str.length());
                break;
        }

        return String.valueOf(tmp);
    }

    public static void main(String[] args) {
        String str = "가나다";
        System.out.println(format(str,7,0)); // 왼쪽 정렬
        System.out.println(format(str,7,1)); // 가운데 정렬
        System.out.println(format(str,7,2)); // 오른쪽 정렬
    }
}

class Exercise11 {
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        String in = s.nextLine();
        String[] arr = in.split(" ");
        int input = Integer.parseInt(arr[0]);
        int input2 = Integer.parseInt(arr[1]);


        for (int j = input; j<= input2; j++ ) {
            for (int i = 1; i < 10; i++) {
                System.out.println(j + "*" + i + "=" + i * j);
            }
            System.out.println();
        }
    }
}

class Exercise12
{
    public static int getRand(int from, int to) {
        return  (int) (Math.random()* Math.abs(to-from)+1) + Math.min(from, to); // (int) (Math.random() * (최댓값 - 최솟값+1)) + 최솟값
        // 최댓값 - 최솟값 + 1 은 범위에 포함된 정수의 개수를 계산하는 과정, 문제 조건에서 from이 to보다 클 수도 있다했으니깐 이를 처리하기 위해 abs사용
    }

    public static void main(String[] args)
    {
        for(int i=0; i< 20; i++)
            System.out.print(getRand(1,-3)+",");
    }
}

class Exercise13 {
    public static void main(String[] args) {
        String src = "aabbccAABBCCaa";
        System.out.println(src);
        System.out.println("aa를 " + stringCount(src, "aa") +"개 찾았습니다.");
    }
    static int stringCount(String src, String key) {
        return stringCount(src, key, 0);
    }
    static int stringCount(String src, String key, int pos) {
        int count = 0;
        int index = 0;
        if (key == null || key.length() == 0)
            return 0;

        while (true){
            pos = src.indexOf(key, pos);
            if (pos == -1) break;
            count++;
            pos += key.length();

        }
        return count;
    }
}

class Exercise14 {
    public static void main(String[] args) {
        String[] phoneNumArr = {
                "012-3456-7890",
                "099-2456-7980",
                "088-2346-9870",
                "013-3456-7890"
        };

        ArrayList list = new ArrayList();
        Scanner s = new Scanner(System.in);
        while(true) {
            System.out.print(">>");
            String input = s.nextLine().trim();
            if(input.equals("")) {
                continue;
            } else if(input.equalsIgnoreCase("Q")) {
                System.exit(0);
            }

            Pattern p = Pattern.compile(".*" + input + ".*"); // input을 포함하는 모든 문자열
            for (int i=0; i< phoneNumArr.length ; i++) {
                Matcher m = p.matcher(phoneNumArr[i].replace("-",""));
                if(m.find()) // matches() : 대상 문자열 전체가 해당패턴과 일치하면 true를 리턴(문자열의 처음부터 끝까지 정규식 만족 )
                    // find(): 대상 문자열에서 해당 패턴을 검색하여 일치하는 패턴이 일부라도 존재하면 true 리턴
                    list.add(phoneNumArr[i]);
            }


            if(list.size()>0) {
                System.out.println(list);
                list.clear();
            } else {
                System.out.println("일치하는 번호가 없습니다.");
            }
        }
    } // main
}
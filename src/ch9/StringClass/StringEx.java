package ch9.StringClass;

import java.io.UnsupportedEncodingException;
import java.util.StringJoiner;

public class StringEx {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";
        System.out.println("String str1 = \"abc\";");
        System.out.println("String str2 = \"abc\";");

        System.out.println("str1 == str2 ? " + (str1 == str2)); // 둘이 같은 주소를 가리키고 있음
        System.out.println("str1.equals(str2) ? " + str1.equals(str2));
        System.out.println();

        String str3 = new String("\"abc\"");
        String str4 = new String("\"abc\"");

        System.out.println("String str3 = \"abc\";");
        System.out.println("String str4 = \"abc\";");
        System.out.println("str3 == str4 ? " + (str3 == str4)); // 둘이 같은 주소를 가리키고 있지 않음
        System.out.println("str1.equals(str3) ? " + str3.equals(str4)); // 값은 같음
    }
}

class StringEx3 {
    public static void main(String[] args) {
        char[] cArr = new char[0];
        String s = new String(cArr);
        System.out.println("cArr.length = " + cArr.length);
        System.out.println("@@@" + s+ "@@@");
    }
}

class StringEx4 {
    public static void main(String[] args) {
        String animals = "dog,cat,bear";
        String[] arr = animals.split(",");

        System.out.println(String.join(("-"), arr));

        StringJoiner sj = new StringJoiner("-", "[", "]");
        for (String s : arr)
            sj.add(s);

        System.out.println(sj.toString());
    }
}

class StringEx5 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "가";

        byte[] bArr = str.getBytes("UTF-8"); // 문자열을 UTF-8로 변환
        byte[] bArr2 = str.getBytes("CP949");

        System.out.println("UTF-8:" + joinByteArr(bArr));
        System.out.println("CP949:" + joinByteArr(bArr2));

        System.out.println("UTF-8:" + new String(bArr, "UTF-8"));
        System.out.println("CP949:" + new String(bArr2, "CP949"));
    }

    static String joinByteArr(byte[] bArr) {
        StringJoiner sj = new StringJoiner(":", "[", "]");

        for (byte b : bArr)
            sj.add(String.format("%02X",b));
        return sj.toString();
    }
}

class StringEx6 {
    public static void main(String[] args) {
        int iVal = 100;
        String strVal = String.valueOf(iVal); // int를 String으로 변환

        double dVal = 200.0;
        String strVal2 = dVal + ""; // String으로 변환하는 또 다른 방법

        double sum = Integer.parseInt("+" + strVal) + Double.parseDouble(strVal2); // 문자형을 Int와 Double로 바꾸어 더해줌
        double sum2 = Integer.valueOf(strVal) + Double.parseDouble(strVal2); // 마찬가지, 다른 방법

        System.out.println(String.join("", strVal, "+", strVal2, "=") + sum);
        System.out.println(strVal+"+"+strVal2+"="+sum2);
    }
}

class StringEx7 {
    public static void main(String[] args) {
        String fullName = "Hello.java";

        int index = fullName.indexOf('.');
        String fileName = fullName.substring(0, index); // substring으로 한 문자열에서 내용의 일부를 추출 end 위치 문자는 포함x
        String ext = fullName.substring(index + 1);

        System.out.println(fullName + "의 확장자를 제외한 이름은" + fileName);
        System.out.println(fullName + "의 확장자는" + ext);
    }
}

class StringBufferEx1 {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("abc");
        StringBuffer sb2 = new StringBuffer("abc");

        System.out.println("sb == sb2 ? " + (sb == sb2)); // 인스턴스 같은지 비교
        System.out.println("sb.equals(sb2) ? " + sb.equals(sb2)); // 인스턴스 같은지 비교 

        String s = sb.toString();
        String s2 = sb2.toString();

        System.out.println("s.equals(s2) ? " + s.equals(s2)); // 문자열 추출해서 그 둘이 같은지 비교
    }
}

class StringBufferEx2 {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("01");
        StringBuffer sb2 = sb.append(23); // 하나의 객체에
        sb.append('4').append(56);        // 계속 append하고 있음

        StringBuffer sb3 = sb.append(78);
        sb.append(9.0);

        System.out.println("sb =" + sb);
        System.out.println("sb2 =" + sb2);
        System.out.println("sb3 =" + sb3);

        System.out.println("sb =" + sb.deleteCharAt(10));
        System.out.println("sb =" + sb.delete(3,6));
        System.out.println("sb =" + sb.insert(3,"abc"));
        System.out.println("sb =" + sb.replace(6, sb.length(), "END"));

        System.out.println("capacity =" + sb.capacity());
        System.out.println("length =" + sb.length());
    }
}

class WrapperEx1 {
    public static void main(String[] args) {
        Integer i = new Integer(100); // 래퍼클래스로 개게로 만듬
        Integer i2 = new Integer(100);

        System.out.println("i==i2 ?" + (i==i2)); // 객체가 다름
        System.out.println("i.eqauls(i2) ?" + i.equals(i2)); // 오버라이딩돼서 값 가지고비교
        System.out.println("i.compareTo(i2) ?" + i.compareTo(i2));
        System.out.println("i.toString() ?" + i.toString());

        System.out.println("MAX_VALUE=" + Integer.MAX_VALUE);
        System.out.println("MIN_VALUE=" + Integer.MIN_VALUE);
        System.out.println("SIZE=" + Integer.SIZE +" bits"); //32bit
        System.out.println("SIZE=" + Integer.BYTES +" bytes"); // 4byte
        System.out.println("TYPE=" + Integer.TYPE); //int 타입
    }
}


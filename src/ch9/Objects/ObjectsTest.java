package ch9.Objects;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static java.util.Objects.compare;
import static java.util.Objects.isNull;

public class ObjectsTest {
    public static void main(String[] args) {
        String[][] str2D = new String[][]{{"aaa","bbb"}, {"AAA","BBB"}};
        String[][] str2D_2 = new String[][]{{"aaa","bbb"}, {"AAA","BBB"}};

        System.out.println("Str2D ={");
        for(String[] tmp : str2D)
            System.out.println(Arrays.toString(tmp));
        System.out.println("}");

        System.out.println("Str2D_2 ={");
        for(String[] tmp : str2D_2)
            System.out.println(Arrays.toString(tmp));
        System.out.println("}");

        System.out.println("equals(str2D,str2D_2)=" + Objects.equals(str2D,str2D_2));
        System.out.println("deepEquals(str2D,str2D_2)=" + Objects.deepEquals(str2D,str2D_2)); // 2차원 배열비교

        System.out.println("isNull(null)="+ isNull(null));
        System.out.println("hashCode(null)="+ Objects.hashCode(null)); // NULL이면 0 반환
        System.out.println("toString(null)="+ Objects.toString(null));
        System.out.println("toString(nulL, \"\") : "+ Objects.toString(null, ""));

        Comparator c = String.CASE_INSENSITIVE_ORDER; // 대소문자 구분안하는 비교

        System.out.println("compare(\"aa\",\"bb\") = " + compare("aa","bb",c));
        System.out.println("compare(\"aa\",\"bb\") = " + compare("bb","aa",c)); // bb가 aa보다 크다
        System.out.println("compare(\"aa\",\"bb\") = " + compare("ab","AB",c));
    }
}

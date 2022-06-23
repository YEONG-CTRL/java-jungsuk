package ch11;

import java.util.Arrays;
import java.util.Comparator;

public class comparatorEx {
    public static void main(String[] args) {
        String[] strArr = {"cat", "dog", "lion", "tiger"};

        Arrays.sort(strArr); // String의 Comparable은 문자열이 사전 순으로 정렬되도록한다(유니코드값)
        System.out.println("strArr = " + Arrays.toString(strArr));

        Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER); // 대소문자 구분하지 않는 Comparator사용
        System.out.println("strArr = " + Arrays.toString(strArr));

        Arrays.sort(strArr, new Descending());
        System.out.println("strArr = " + Arrays.toString(strArr));
    }
}

class Descending implements Comparator {
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Comparable && o2 instanceof Comparable) {
            Comparable c1 = (Comparable) o1;
            Comparable c2 = (Comparable) o2;
            return c1.compareTo(c2) * -1;
        }
        return -1;
    }
}

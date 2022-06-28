package ch11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import static java.util.Collections.*;

public class CollectionsEx {
    public static void main(String[] args) {
        List list = new ArrayList();
        System.out.println(list);

        addAll(list, 1, 2, 3, 4, 5);
        System.out.println(list);

        rotate(list, 2); // 오른쪽으로 두칸씩 이동 4 5 1 2 3
        System.out.println(list);

        swap(list, 0, 2); // 0번째와 2번째 인덱스 값을 교환 1 5 4 2 3
        System.out.println(list);

        shuffle(list);  // 저장된 요소의 위치 임의로 변경
        System.out.println(list);

        sort(list, reverseOrder()); // 역순으로 정렬 5 4 3 2 1
        System.out.println(list);

        sort(list); // 오름차순 정렬
        System.out.println(list);

        int idx = binarySearch(list, 3);
        System.out.println("index of 3 = " + idx);

        System.out.println("max = " + max(list));
        System.out.println("min = " + min(list));
        System.out.println("min = " + max(list, reverseOrder())); // 역순 정렬의 가장 큰 값-> 최솟값

        fill(list, 9);
        System.out.println("list=" + list);

        List newList = nCopies(list.size(), 2); // list와 같은 크기의 리스트를 생성하고 2로채운다
        System.out.println("newList=" + newList);

        System.out.println(disjoint(list, newList)); // 공통요소가 없다면 true

        copy(list, newList); // newlist를 list에 복사한다
        System.out.println("newList=" + newList);
        System.out.println("list=" + list);

        replaceAll(list, 2, 1); // 2를 1로 모두 교체
        System.out.println("list=" + list);

        Enumeration e = enumeration(list);
        ArrayList list2 = list(e);
        System.out.println("list2 =" + list2);
    }
}

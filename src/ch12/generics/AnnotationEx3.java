package ch12.generics;

import java.util.ArrayList;


public class AnnotationEx3 {
    @SuppressWarnings("deprecation") // deprecation 경고 억제
    public static void main(String[] args) {
        NewClass nc = new NewClass();

        nc.oldField = 10;
        System.out.println(nc.getOldField());

        @SuppressWarnings("unchecked")  // 지네릭스 관련 경고 억제
        ArrayList<NewClass> list = new ArrayList();
        list.add(nc);
    }
}

package ch12.generics;

import java.util.ArrayList;

class NewClass{
    int newField;

    int getNewField() { return newField; }

    @Deprecated
    int oldField;

    @Deprecated
    int getOldField() { return oldField; }
}

public class AnnotationEx { // java로 실행시 패키지 이름이 모두 포함된 full class name으로 .class파일 찾기때문에 상위 디렉터리에서 패키지명.클래스파일 명으로 실행해줘야 함
    public static void main(String[] args) {
        NewClass nc = new NewClass();

        nc.oldField = 10;
        System.out.println(nc.getOldField());
    }
}


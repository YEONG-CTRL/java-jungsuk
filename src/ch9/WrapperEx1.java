package ch9;

class WrapperEx1 {
    public static void main(String[] args) {
        Integer i = new Integer(100); // 래퍼클래스로 객체로 만듬
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

class WrapperEx2 {
    public static void main(String[] args) {
        int  i = new Integer("100").intValue();
        int i2 = Integer.parseInt("100"); // 기본형으로
        Integer i3 = Integer.valueOf("100"); // 래퍼클래스로

        int i4 =Integer.parseInt("100",2); // 2진수
        int i5 =Integer.parseInt("100",8);
        int i6 =Integer.parseInt("100",16);
        int i7 =Integer.parseInt("FF",16);
//        int i8 =Integer.parseInt("FF"); // 2진수로 바꾸려하면 NumberFormatException

        Integer i9 = Integer.valueOf("100",2);
        Integer i10 = Integer.valueOf("100",8);
        Integer i11 = Integer.valueOf("100",16);
        Integer i12 = Integer.valueOf("FF",16);

        System.out.println(i);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println("100(2) :" +i4);
        System.out.println("100(8) :" +i5);
        System.out.println("100(16) :" +i6);
        System.out.println("FF(16) :" +i7);

        System.out.println("100(2) :" +i9);
        System.out.println("100(8) :" +i10);
        System.out.println("100(16) :" +i11);
        System.out.println("FF(16) :" +i12);
    }
}

class WrapperEx3 {
    public static void main(String[] args) {
        int i = 10;

        // 기본형을 참조형으로 변환 (형변환 생략가능)
        Integer intg = (Integer)i;
        Object obj = (Object)i;

        Long lng = 100L;

        int i2 = intg + 10; // 참조형과 기본형과의 덧셈
        long l = intg + lng; // 참조형간의 덧셈

        Integer intg2 = new Integer(20);
        int i3 = (int)intg2; // 참조형을 기본형으로 형변환(형변환 생략가능)

        Integer intg3 = intg2 + i3;

        System.out.println("i       =" + i);
        System.out.println("intg    =" + intg);
        System.out.println("obj     =" + obj);
        System.out.println("lng     =" + lng);
        System.out.println("intg + 10    =" + i2);
        System.out.println("intg + lng   =" + l);
        System.out.println("intg2        =" + intg2);
        System.out.println("i3           =" + i3);
        System.out.println("intg2 + i3   =" + intg3);
    }
}
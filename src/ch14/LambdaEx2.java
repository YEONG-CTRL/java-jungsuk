package ch14;

@FunctionalInterface
interface MyFunction2 {
    public abstract void MyMethod();
}

class LambdaEx2 {
    public static void main(String[] args) {
        MyFunction f = () -> {}; // MyFunction f = (MyFunction) (()->{}); -> 함수형 인터페이스로의 형변환은 생략 가능
        Object obj = (MyFunction)(()->{}); // Object타입으로 형변환이 생략됨
        String str = ((Object)(MyFunction)(()->{})).toString();

        System.out.println(f);
        System.out.println(obj);
        System.out.println(str);
        System.out.println((MyFunction)(()->{}));
        System.out.println(((Object)(MyFunction)(()->{})).toString());
    }
}

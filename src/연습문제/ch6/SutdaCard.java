package 연습문제.ch6;

public class SutdaCard { // 1,2번
        int num;
        boolean isKwang;

        SutdaCard() {
            this(1,true);
        }

        SutdaCard(int num, boolean isKwang) {
            this.num     = num;
            this.isKwang = isKwang;
        }

        String info() {
            return num + (isKwang ? "K" : "");
    }
}
class Exercise6_2 {
    public static void main(String args[]) {
        SutdaCard card1 = new SutdaCard(3, false);
        SutdaCard card2 = new SutdaCard();
        System.out.println(card1.info());
        System.out.println(card2.info());
    }
}


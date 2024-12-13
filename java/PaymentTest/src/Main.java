import entity.BankTransferPayment;
import entity.CardPayment;

public class Main {
    public static void main(String[] args) {
        // CardPayment 객체 생성 및 테스트
        CardPayment c1 = new CardPayment("신용카드");
        c1.processPayment(5000);

        // BankTransferPayment 객체 생성 및 테스트
        BankTransferPayment b1 = new BankTransferPayment("국민은행");
        // 각 객체의 processPayment() 메서드 호출
        b1.processPayment(100000);
    }
}

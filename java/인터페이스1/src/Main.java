import entity.CreditCardPayment;
import entity.KakaoPayPayment;

public class Main {
    public static void main(String[] args) {
        // CreditCardPayment 객체 생성 (카드 번호: "1234-5678-9012-3456", 소유자 이름: "홍길동")
        CreditCardPayment c1 = new CreditCardPayment("1234-5678-9012-3456", "홍길동");

        // KakaoPayPayment 객체 생성 (전화번호: "010-1234-5678")
        KakaoPayPayment k1 = new KakaoPayPayment("010-1234-5678");

        // 각 결제 방식에 대해 반복:
        //   1. 결제 방식 이름 출력 (getPaymentMethod() 사용)
        System.out.println( c1.getPaymentMethod() );
        //   2. 50000원 결제 시도 및 결과 출력
        c1.processPayment(50000);
        //   3. 30000원 환불 처리
        c1.refund(50000);
        //   4. 빈 줄 출력
        System.out.println();


        System.out.println( k1.getPaymentMethod() );

        k1.processPayment(10000);

        k1.refund(10000);

    }
}

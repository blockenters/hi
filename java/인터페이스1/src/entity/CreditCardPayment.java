package entity;

public class CreditCardPayment implements PaymentProcessor{

    private String cardNumber;
    private String cardHolderName;

    // 생성자
    public CreditCardPayment() {

    }

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        // cardNumber와 cardHolderName을 초기화하세요.
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }


    @Override
    public boolean processPayment(double amount) {
        // 1. "[cardHolderName]의 카드 [cardNumber]로 [amount]원 결제 시도" 메시지 출력
        System.out.println( cardHolderName+"의 카드 "+cardNumber+"로 "+amount+"원 결제 시도");
        // 2. true를 리턴하시오.
        return true;
    }

    @Override
    public void refund(double amount) {
        // "[cardHolderName]의 카드 [cardNumber]로 [amount]원 환불 처리" 메시지 출력
        System.out.println(cardHolderName+"의 카드 "+cardNumber+"로 "+amount+"원 환불 처리");
    }

    @Override
    public String getPaymentMethod() {
        // "Credit Card" 문자열 반환
        return "Credit Card";
    }
}

package entity;

public class KakaoPayPayment implements PaymentProcessor{

    private String phoneNumber;

    // 생성자
    public KakaoPayPayment() {

    }

    public KakaoPayPayment(String phoneNumber) {
        // phoneNumber를 초기화하세요.
        this.phoneNumber = phoneNumber;
    }


    @Override
    public boolean processPayment(double amount) {
        // 1. "[phoneNumber]로 카카오페이 [amount]원 결제 시도" 메시지 출력
        System.out.println(phoneNumber+"로 카카오페이 "+amount+"원 결제 시도");
        // 2. true 를 리턴하시오.
        return true;
    }

    @Override
    public void refund(double amount) {
        // "[phoneNumber]로 카카오페이 [amount]원 환불 처리" 메시지 출력
        System.out.println(phoneNumber+"로 카카오페이 "+amount+"원 환불 처리");
    }

    @Override
    public String getPaymentMethod() {
        // "KakaoPay" 문자열 반환
        return "KakaoPay";
    }
}

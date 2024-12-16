package entity;

public interface PaymentProcessor {

    // 결제 처리하는 함수
    boolean processPayment(double amount);
    // 환불하는 함수
    void refund(double amount);
    // 결제 방법 가져오는 함수 (“카드결제”, “카카오페이결제”)
    String getPaymentMethod();

}

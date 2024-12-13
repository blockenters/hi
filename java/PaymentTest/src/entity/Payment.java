package entity;

public class Payment {
    // "카드" "계좌이체"
    protected String paymentMethod;

    public Payment(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void processPayment(double amount) {
        // 결제 방식과 금액 출력
        System.out.println( "결제 방식 : " + paymentMethod +  "결제 금액 : " + amount);
    }
}

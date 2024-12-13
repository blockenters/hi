package entity;

public class CardPayment extends Payment{
    // 신용카드, 체크카드
    private String cardType;

    public CardPayment(String cardType) {
        super("카드");
        this.cardType = cardType;
    }

    @Override
    public void processPayment(double amount) {
        // 예: "결제 방식: 카드, 카드 유형: [cardType], 결제 금액: [amount]원"
        System.out.println("결제 방식: "+paymentMethod+", 카드 유형: "+cardType+", 결제 금액: +"+amount+"+원");
    }
}

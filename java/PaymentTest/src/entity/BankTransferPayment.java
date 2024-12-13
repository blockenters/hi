package entity;

public class BankTransferPayment extends Payment {

    private String bankName;

    public BankTransferPayment(String bankName) {
        // 부모 생성자 호출 및 bankName 초기화
        super("계좌이체");
        this.bankName = bankName;
    }

    @Override
    public void processPayment(double amount) {
        // 예: "결제 방식: 계좌이체, 은행 이름: [bankName], 결제 금액: [amount]원"
        System.out.println( "결제 방식: "+paymentMethod+", 은행 이름: "+bankName+", 결제 금액: "+amount+"원" );
    }
}

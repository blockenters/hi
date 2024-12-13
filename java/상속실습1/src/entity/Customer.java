package entity;

public class Customer extends User{

    private int loyaltyPoints;

    public Customer(String username, String email) {
        super(username, email);
        loyaltyPoints = 0;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("적립 포인트 : " +loyaltyPoints);
    }

    public void addPoints(int points) {
        // loyaltyPoints에 points를 더함
        loyaltyPoints = loyaltyPoints + points;
        // 적립 완료 메시지 출력
        System.out.println("적립 완료 되었습니다.");
    }

}

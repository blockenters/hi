import entity.Admin;
import entity.Customer;

public class Main {

    public static void main(String[] args) {
        // Customer 객체 생성
        Customer c1 = new Customer("홍길동", "abc@gamil.com");
        // Customer의 displayInfo() 메서드 호출
        c1.displayInfo();
        // Customer의 addPoints() 메서드 호출
        c1.addPoints(500);
        // Customer의 displayInfo() 메서드 다시 호출하여 포인트 증가 확인
        c1.displayInfo();

        // Admin 객체 생성
        Admin a1 = new Admin("김나나", "aaa@naver.com");
        // Admin의 displayInfo() 메서드 호출
        a1.displayInfo();
        // Admin의 manageService() 메서드 호출
        a1.manageService();

    }

}

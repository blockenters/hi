import entity.Child;
import entity.Parent;

public class ExtendTest {

    public static void main(String[] args) {

        // Parent 클래스 객체 생성. 홍길동씨.
        Parent p1 = new Parent();
        p1.setName("홍길동");
        p1.setAge(50);
        p1.setMoney(1000000000);

        p1.print();

        // Child 클래스 객체 생성. 홍나나씨.
        Child c1 = new Child();
        c1.setName("홍나나");
        c1.setAge(24);
        c1.setMoney(100000);
        c1.setHobby("게임");

        c1.print();

    }




}

import entity.Person;

public class Test {

    public static void main(String[] args) {
        // 나이40, 이름 James, 결혼했고, 자식이 3명
        // 이 데이터를 저장하고 출력!

        Person p1 = new Person("James", 40, true, 3);

        p1.print();

    }

}

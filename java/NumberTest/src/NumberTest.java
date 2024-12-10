public class NumberTest {

    public static void main(String[] args) {
        // 두개 숫자를 저장하는 Number 클래스를 이용해서
        // 객체 생성해주세요.
        // 4, 7 을 저장해주세요.
        Number n1 = new Number();
        n1.num1 = 4;
        n1.num2 = 7;
        // 두 숫자를 더해주세요.
        // 더한 값을 화면에 출력해주세요.
        int sum = n1.sum();
        System.out.println( sum );
        double avg = n1.avg();
        System.out.println(avg);

        n1.print();
    }
}

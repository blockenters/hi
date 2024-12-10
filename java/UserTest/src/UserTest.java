public class UserTest {
    public static void main(String[] args) {
        // 유저 정보를 저장하려 합니다.
        // 유저 이름, 나이, 전화번호를 저장하려 합니다.
        User u1 = new User();
        u1.name = "홍길동";
        u1.age = 30;
        u1.phone = "010-1234-5678";

        User u2 = new User();
        u2.name = "김나나";
        u2.age = 25;
        u2.phone = "010-1111-2222";

        // 이름 : 홍길동, 나이 : 30, 전화번호 : 010-1234-5678
        u1.print();

        // 이름 : 김나나, 나이 : 25, 전화번호 : 010-1111-2222
        u2.print();

        // 10년 후의 나이를 계산해서 리턴하는 함수가 필요!
        System.out.println( u1.futureAge() );
        System.out.println( u2.futureAge() );

        u1.say("안녕?");
        // 화면에 출력 : 홍길동님 안녕?

        u1.say("반가워요");
        // 화면에 출력 : 홍길동님 반가워요

    }
}

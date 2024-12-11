public class MemberTest {
    public static void main(String[] args) {

        // 주소록 서비스 입니다.
        // 이름, 전화번호, 주소를 저장하고 관리하는 서비스 입니다.

        // 홍길동, 010-1111-2222, 경기도 성남시 => 만드세요.
        Member m1 = new Member();
        m1.name = "홍길동";
        m1.tel = "010-1111-2222";
        m1.address = "경기도 성남시";

        m1.print();

        // 김나나, 010-2222-5555, 경기도 광주시
        Member m2 = new Member("김나나", "010-2222-5555", "경기도 광주시");

        m2.print();

        // 김영수, 010-3333-4444 이고, 주소는 없어서 나중에 입력할거다.
        // 메모리 생성하면서, 한줄에 데이터 입력할 수 있도록 해주세요.
        Member m3 = new Member("김영수", "010-3333-4444");

        m3.print();
    }
}

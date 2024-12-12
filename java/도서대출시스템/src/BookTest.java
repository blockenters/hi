import entity.Book;
import entity.Member;

public class BookTest {
    public static void main(String[] args) {
        // 홍길동 생성
        Member m1 = new Member("홍길동");
        // 김나나 생성
        Member m2 = new Member("김나나");

        // 재밌는책, mike  생성
        Book b1 = new Book("재밌는책", "mike", true);
        // 무서운책, tom   생성
        Book b2 = new Book("무서운책", "tom", true);

        // 홍길동이 재밌는책을 빌린다.
        m1.borrowBook(b1);

        m1.print();
        // 김나나가 재밌는책을 빌리려고 했다.
        m2.borrowBook(b1);

        // 홍길동이 재밌는책을 반납한다.
        m1.returnBook(b1);
        // 홍길동의 상태를 화면에 출력
        m1.print();

        m2.borrowBook(b1);
    }
}

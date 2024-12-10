public class BookTest {

    public static void main(String[] args) {
        // 책 정보를 저장하려고 한다.
        // 책타이틀, 책설명, 책가격,
        // 책을쓴작가의 이름, 책을쓴 작가의 이메일, 그 작가의 폰번
        // 을 저장하려 한다.

        // Good Night, 무서운책, 30000,
        // Mike, abc@gmail.com, 010-1111-2222
        Book b1 = new Book();
        b1.title = "Good Night";
        b1.description = "무서운책";
        b1.price = 30000;
        b1.author = new Author();
        b1.author.name = "Mike";
        b1.author.email = "abc@gmail.com";
        b1.author.phone = "010-1111-2222";

        // 무서운책, 공포가최고, 25000,
        // Mike, abc@gmail.com, 010-1111-2222
        Book b2 = new Book();
        b2.title = "무서운책";
        b2.description = "공포가최고";
        b2.price = 25000;
        b2.author = new Author();
        b2.author.name = "Mike";
        b2.author.email = "abc@gmail.com";
        b2.author.phone = "010-1111-2222";

        // 재밌는책, 유머가최고, 20000,
        // 홍길동, aaa@naver.com, 010-3333-5555
        Book b3 = new Book();
        b3.title = "재밌는책";
        b3.description = "유머가최고";
        b3.price = 20000;
        b3.author = new Author();
        b3.author.name = "홍길동";
        b3.author.email = "aaa@naver.com";
        b3.author.phone = "010-3333-5555";

        // 책의 타이틀, 설명, 가격을 화면에 출력하는 함수가 필요합니다.
        // 제목 : 재밌는책
        // 설명 : 유머가최고
        // 가격 : 20000원

        b3.print();

        b1.print();

        b2.print();

    }

}

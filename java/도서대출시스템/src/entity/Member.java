package entity;

public class Member {

    private String name;
    private Book borrowedBook;

    public Member() {
    }

    public Member(String name) {
        this.name = name;
    }

    public void borrowBook(Book book){
        // 이 사람이 책을 빌릴때 작업
        // 1. 책이 빌릴 수 있는 상태인지 확인해서 빌릴수 없으면,
        //    화면에, "책 대출 불가. 다른책 빌리세요." 라고 출력
        //    함수 끝낸다.
        // 2. 그렇지 않으면, 이 책을 내 멤버변수에 저장한다.
        //    책의 상태를, 빌려갔으니까 isAvailable 을 false로 변경.
        //    유저한테 "대출 완료 되었습니다."
        if ( !book.isAvailable()  ){   // book.isAvailable() == false
            System.out.println("책 대출 불가! 다른책을 빌리세요");

        } else {
            borrowedBook = book;
            book.borrow();
            System.out.println("대출 완료 되었습니다.");

        }
    }

    public void returnBook(Book book){
        // 이 사람이 책을 반납할때 작업
        // 1. 내 멤버변수 borrowedBook 에서 이 책을 제거.
        // 2. 이 책의 상태를, 다른 사람이 빌려갈수 있도록,
        //    isAvailable 을 true 해줘야 한다.
        borrowedBook = null;
        book.returnBook();
        System.out.println("책 반납이 정상적으로 이루어 졌습니다.");
    }

    public void print(){
        System.out.println(name + "님이 빌린 책 : ");
        // 빌린책이 있는 경우와 없는 경우로 나눠서 개발
        if (borrowedBook == null){
            System.out.println("빌린책이 없습니다.");
        }else{
            System.out.println(borrowedBook.getTitle() + " "+
                    borrowedBook.getAuthor() );
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(Book borrowedBook) {
        this.borrowedBook = borrowedBook;
    }
}

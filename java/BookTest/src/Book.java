public class Book {
    String title;
    String description;
    int price;
    Author author;

    // print();

    void print(){
        System.out.println("제목 : " + title);
        System.out.println("설명 : " + description);
        System.out.println("가격 : " + price + "원");
    }

}

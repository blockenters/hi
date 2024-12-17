package entity;

public class Product {

    private String id;
    private String name;
    private double price;

    // TODO: Product 클래스의 디폴트 생성자를 만드세요.

    public Product() {
    }
    // TODO: Product 클래스의 다음 생성자를 구현하세요.
    // 힌트: id, name, price를 매개변수로 받아 초기화합니다.

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    // TODO: getter 메소드들을 구현하세요.
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    // TODO: print 함수
    // 힌트: "ID: [id], 상품명: [name], 가격: [price]원" 형식출력.
    public void print(){
        System.out.println( "ID: "+id+", 상품명: "+name+", 가격: "+price+"원" );
    }

}

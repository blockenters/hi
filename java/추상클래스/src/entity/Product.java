package entity;

public abstract class Product {
    protected String productId;
    protected String name;
    protected double price;

    // 디폴트 생성자
    public Product() {

    }

    // 매개변수가 있는 생성자
    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    // 상품 정보를 출력하는 추상 메서드
    public abstract void displayInfo();

    // 할인된 가격을 계산하는 추상 메서드
    public abstract double calculateDiscountedPrice();



}

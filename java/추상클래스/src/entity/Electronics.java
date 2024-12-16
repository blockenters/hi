package entity;

public class Electronics extends Product{

    private String brand;
    private int warrantyPeriod; // 무상 보증 개월 단위

    // 디폴트 생성자
    public Electronics() {

    }

    // 매개변수가 있는 생성자
    public Electronics(String productId, String name, double price, String brand, int warrantyPeriod) {
        super(productId, name, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }


    @Override
    public void displayInfo() {
        // 다음 정보를 순서대로 출력:
        // 1. "상품 ID: [productId]"
        System.out.println("상품 ID: " + productId);
        // 2. "상품 이름: [name]"
        System.out.println("상품 이름: "+name);
        // 3. "가격: [price]원"
        System.out.println("가격: "+price+"원");
        // 4. "브랜드: [brand]"
        System.out.println("브랜드: "+brand);
        // 5. "보증 기간: [warrantyPeriod]개월"
        System.out.println("보증 기간: "+warrantyPeriod+"개월");
        // 6. "할인된 가격: [calculateDiscountedPrice()]원"
        double discountPrice = calculateDiscountedPrice();
        System.out.println("할인된 가격: "+discountPrice+"원");
        // 7. 빈 줄 출력
        System.out.println();
    }

    @Override
    public double calculateDiscountedPrice() {
        // price에서 10% 할인된 가격을 계산하여 리턴하시오.
        return price * 0.9;
    }
}

package entity;

public class Clothing extends Product{

    private String size;
    private String material;

    // 디폴트 생성자
    public Clothing() {

    }

    // 매개변수가 있는 생성자
    public Clothing(String productId, String name, double price, String size, String material) {
        super(productId, name, price);
        this.size = size;
        this.material = material;
    }


    @Override
    public void displayInfo() {
        // 다음 정보를 순서대로 출력:
        // 1. "상품 ID: [productId]"
        System.out.println("상품 ID: "+productId);
        // 2. "상품 이름: [name]"
        System.out.println("상품 이름: "+name);
        // 3. "가격: [price]원"
        System.out.println("가격: "+price+"원");
        // 4. "사이즈: [size]"
        System.out.println("사이즈: "+size);
        // 5. "소재: [material]"
        System.out.println("소재: "+material);
        // 6. "할인된 가격: [calculateDiscountedPrice()]원"
        double discountPrice = calculateDiscountedPrice();
        System.out.println("할인된 가격: "+ discountPrice +"원");
        // 7. 빈 줄 출력
        System.out.println();
    }

    @Override
    public double calculateDiscountedPrice() {
        // price에서 5% 할인된 가격을 계산하여 리턴하시오.
        return price * 0.95;
    }
}

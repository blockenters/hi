import entity.Clothing;
import entity.Electronics;

public class Main {
    public static void main(String[] args) {
        // Electronics 객체 생성 (디폴트 생성자 사용)
        Electronics electronicProduct = new Electronics();

        // Clothing 객체 생성 (디폴트 생성자 사용)
        Clothing clothingProduct = new Clothing();

        // 각 객체의 displayInfo() 메서드 호출하여 정보 출력
        electronicProduct.displayInfo();
        clothingProduct.displayInfo();

        // 매개변수가 있는 생성자를 사용하여 Electronics 객체 생성
        // productId: "E001", name: "Smart TV", price: 1200000, brand: "삼성전자", warrantyPeriod: 24
        Electronics specificElectronicProduct = new Electronics("E001", "Smart TV", 1200000, "삼성전자", 24);
        specificElectronicProduct.displayInfo();

        // 매개변수가 있는 생성자를 사용하여 Clothing 객체 생성
        // productId: "C001", name: "T-Shirt", price: 20000, size: "L", material: "폴리에스테르"
        Clothing specificClothingProduct = new Clothing("C001", "T-Shirt", 20000, "L", "폴리에스테르");
        specificClothingProduct.displayInfo();

    }
}

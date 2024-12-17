import controller.OnlineStore;
import entity.Product;

public class Main {
    public static void main(String[] args) {

        OnlineStore store = new OnlineStore();

        // TODO: 최소 3개의 상품을 생성하고 store에 추가하세요.
        Product product1 = new Product("P001", "스마트폰", 899000.0);
        Product product2 = new Product("P002", "노트북", 1299000.0);
        Product product3 = new Product("P003", "무선이어폰", 199000.0);

        store.addProduct(product1);
        store.addProduct(product2);
        store.addProduct(product3);

        // TODO: 모든 상품 정보를 출력하세요.
        store.displayAllProducts();

        // TODO: ID로 한 개의 상품을 검색하고 그 정보를 출력하세요.
        // ID P002 의 상품을 검색하고 정보 출력
        store.findProduct("P003").print();

        // TODO: 한 개의 상품을 삭제하세요.
        store.removeProduct("P002");

        // TODO: 삭제 후 모든 상품 정보를 다시 출력하세요.
        store.displayAllProducts();

    }
}

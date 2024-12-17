package controller;

import entity.Product;

import java.util.HashMap;
import java.util.Map;

public class OnlineStore {

    private HashMap<String, Product> products;

    public OnlineStore() {
        // TODO: products HashMap을 초기화하세요.
        products = new HashMap<>();
    }

    public void addProduct(Product product) {
        // TODO: 상품을 HashMap에 추가하세요. 상품 ID를 키로 사용합니다.
        // 힌트: put 메소드를 사용하세요.
        products.put( product.getId() , product );
    }

    public Product findProduct(String id) {
        // TODO: 주어진 ID로 상품을 찾아 반환하세요.
        // 힌트: get 메소드를 사용하세요.
        Product product = products.get(id);
        // 프러덕트가 있으면 프로덕트를 리턴하고, 없으면 null 을 리턴합니다.
        if(product != null){
            return product;
        }else {
            return null;
        }
    }

    public void removeProduct(String id) {
        // TODO: 주어진 ID의 상품을 HashMap에서 제거하세요.
        // 힌트: remove 메소드를 사용하세요.
        products.remove(id);
    }

    public void displayAllProducts() {
        // TODO: 모든 상품의 정보를 출력하세요.
        // 힌트: entrySet()을 사용하여 HashMap을 순회하세요.
        for (Map.Entry<String, Product>  productPair : products.entrySet()){
            productPair.getValue().print();
        }
    }

}

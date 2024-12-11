package entity;

public class Order {
    String id;
    String date;
    String productId;
    User user;

    public Order(){

    }

    public Order(String orderId, String date, String productId,
          String userId, String userName, String userAddress){
        this.id = orderId;
        this.date = date;
        this.productId = productId;

        User user = new User(userId, userName, userAddress);
        this.user = user;
    }

    public void print(){
        System.out.println(id);
        System.out.println(user.id);
        System.out.println(date);
        System.out.println(user.name);
        System.out.println(productId);
        System.out.println(user.address);
    }

}



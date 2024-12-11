import entity.Order;

public class OrderTest {
    public static void main(String[] args) {

       Order o1 = new Order("20180312001",
               "2018년 3월 12일",
               "PD0345-12",
               "abc123",
               "홍길순",
               "서울시 영등포구 여의도동 20번지");

        o1.print();

    }
}

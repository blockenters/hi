public class Member {
    String name;
    String tel;
    String address;

    // 디폴트 생성자는 무조건 만들어 주자.
    Member(){

    }

    // 생성자는 리턴타입이 없다, 아무것도 쓰지 마세요!
    Member(String name, String tel, String address){
        this.name = name;
        this.tel = tel;
        this.address = address;
    }

    Member(String name, String tel){
        this.name = name;
        this.tel = tel;
        this.address = "";
    }

    void print(){
        System.out.println(name+ " "+ tel+ " "+ address);
    }
}

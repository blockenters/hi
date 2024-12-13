package entity;

public class Child extends Parent {

    // 상속 받을때 사용하는 키워드가 extends 다.
    // extends 오른쪽에, 상속받을 클래스명을 쓴다.(딱 1개만 상속)
    // 상속받으면, 멀 상속받는데??
    // 멤버변수와 메소드 모두 눈에 안보이지만, 상속 받은것!
    // 상속받았다는것은, 이 클래스에서 바로 이용이 가능하다는 것.

//    // 이름
//    String name;
//    // 나이
//    int age;
//    // 보유자산(돈)
//    int money;

    String hobby;

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}

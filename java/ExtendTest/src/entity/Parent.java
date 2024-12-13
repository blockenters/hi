package entity;

public class Parent {

    // 이름
    String name;
    // 나이
    int age;
    // 보유자산(돈)
    int money;

    public void print(){
        System.out.println("이름 : " + name);
        System.out.println("나이 : " + age);
        System.out.println("자산 : " + money);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

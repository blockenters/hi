package entity;

public class Person {
    private String name;
    private int age;
    private boolean isMarried;
    private int children;

    public Person() {
    }

    public Person(String name, int age, boolean isMarried, int children) {
        this.name = name;
        this.age = age;
        this.isMarried = isMarried;
        this.children = children;
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

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void print(){
        System.out.println(name);
        System.out.println(age);
        System.out.println(isMarried);
        System.out.println(children);
    }

}

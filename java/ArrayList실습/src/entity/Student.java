package entity;

public class Student {

    private int id;
    private String name;
    private int age;

    // TODO: 디폴트생성자를 만드세요.

    public Student() {
    }

    // TODO: 다음 생성자를 구현하세요.
    // 힌트: id, name, age를 매개변수로 받아 초기화합니다.

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    // TODO: getter와 setter 메소드를 구현하세요.
    // 힌트: 각 필드에 대해 getter와 setter를 만듭니다.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    // TODO: print 함수를 만드세요. 학생 정보를 문자열로 출력
    // 힌트: "ID: [id], 이름: [name], 나이: [age]" 형식 출력
    public void print(){
        System.out.println("ID: "+id+", 이름: "+name+", 나이: "+age);
    }

}

public class User {
    String name;
    int age;
    String phone;

    void print(){
       String sentence = "이름 : "+name+", 나이 : "+age+", 전화번호 : "+phone;
       System.out.println(sentence);
    }

    // 10년 후의 나이를 리턴하는 함수
    int futureAge(){
        return age + 10;
    }

    void say(String sentence){
        System.out.println( name + "님 " + sentence );
    }

}

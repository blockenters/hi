public class Number {
    int num1;
    int num2;
    // 멤버변수 두개를 더해서, 리턴하는 함수.
    int sum(){
        int sum = num1 + num2;
        return sum;
    }
    // 멤버변수 두개의 평균을 구해서, 리턴하는 함수
    double avg(){
        double avg = (num1 + num2) / 2.0;
        return avg;
    }

    // print();
    void print(){
        System.out.println("첫번째숫자 : "+num1 +", 두번째숫자 : "+num2);
    }

}

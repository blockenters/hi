public class StudentMain {
    public static void main(String[] args) {

        // 학생 정보를 만든다.
        // 이름은 홍길동, 점수는 70점
        Student s1 = new Student();
        s1.name = "홍길동";
        s1.score = 70;

        // 홍길동의 점수를 80점으로 변경하고 싶다. 함수로!
        s1.updateScore(80);

        // 홍길동의 점수를 60점으로 입력하고, isExtraCredit를 true로 해보자.
        s1.updateScore(60, true);

        s1.printStudentInfor();

    }
}

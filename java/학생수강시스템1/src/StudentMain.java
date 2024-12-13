import entity.Lecture;
import entity.Student;

public class StudentMain {
    public static void main(String[] args) {
        // 1. 영어 과목을 생성
        Lecture l1 = new Lecture("영어", 20);
        // 2. 수학 과목을 생성
        Lecture l2 = new Lecture("수학", 20);
        // 3. 홍길동 생성
        Student s1 = new Student("홍길동", "123");
        // 4. 김나나 생성
        Student s2 = new Student("김나나", "456");
        // 5. 홍길동이 영어를 수강신청
        s1.registerLecture(l1);
        s1.printInfo();
        // 6. 김나나도 영어를 수강신청
        s2.registerLecture(l1);
        //    김나나 print 해보고
        s2.printInfo();
        //    영어과목도 print 해보고.
        l1.printInfo();
        // 7. 홍길동이 수학과목 신청
        s1.registerLecture(l2);
        // 8. 홍길동이 영어과목 취소하고
        s1.cancelLecture();
        s1.printInfo();
        // 9. 수학 신청.
        s1.registerLecture(l2);
        s1.printInfo();

    }
}

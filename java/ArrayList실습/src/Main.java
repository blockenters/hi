import controller.StudentManager;
import entity.Student;

public class Main {
    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        // TODO: 최소 3명의 학생을 생성하고 manager에 추가하세요.
        // id 1, 홍길동, 30
        Student s1 = new Student(1, "홍길동", 30);
        // id 2, 김나나, 22
        Student s2 = new Student(2, "김나나", 22);
        // id 3, 최영수, 25
        Student s3 = new Student(3, "최영수", 25);

        manager.addStudent(s1);
        manager.addStudent(s2);
        manager.addStudent(s3);

        // TODO: 모든 학생 정보를 출력하세요.
        manager.displayAllStudents();

        // TODO: ID로 한 명의 학생을 검색하고 그 정보를 출력하세요.
        // id 가 3인 학생 검색하여 출력.

        // TODO: 한 명의 학생을 삭제하세요. id가 1인 학생 삭제.

        // TODO: 삭제 후 모든 학생 정보를 다시 출력하세요.


    }
}

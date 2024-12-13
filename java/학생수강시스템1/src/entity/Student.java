package entity;

public class Student {
    // 학생 이름
    private String name;
    // 학번
    private String studentId;
    // 학생은 과목을 하나만 수강 가능.
    private Lecture registeredLecture;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public boolean registerLecture(Lecture lecture) {
        // 1. 이미 수강 중인 강의가 있는지 확인합니다.
        if (registeredLecture != null){
            System.out.println("이미 수강중인 과목이 있습니다. 취소 먼저 하세요.");
            return false;
        }
        // 2. 수강 중인 강의가 없다면, 주어진 강의에 학생을 등록합니다.
        if( lecture.addStudent() == false){
            return false;
        }
        // 3. 강의 등록이 성공하면 registeredLecture를 업데이트하고 true를 반환합니다.
        registeredLecture = lecture;
        // 4. 실패하면 (이미 수강 중이거나 강의가 가득 찼을 경우) false를 반환합니다.
        return true;
    }

    public boolean cancelLecture() {
        // 1. 현재 수강 중인 강의가 있는지 확인합니다.
        // 2. 수강 중인 강의가 있다면, 해당 강의에서 학생을 제거합니다.
        // 3. registeredLecture를 null로 설정합니다.
        // 4. 취소가 성공하면 true, 실패하면 false를 반환합니다.
        return true;
    }

    public void printInfo() {
        // 학생의 이름, 학번을 출력합니다.
        System.out.println(name + " , 학번 : " + studentId);
        // 수강 중인 강의가 있다면 강의명도 출력합니다.
        if(registeredLecture == null){
            System.out.println("수강 중인 강의 없음");
        }else{
            System.out.println( "수강중인 강의는 : "+registeredLecture.getLectureName() );
        }
        // 수강 중인 강의가 없다면 "수강 중인 강의 없음"을 출력합니다.
    }


}

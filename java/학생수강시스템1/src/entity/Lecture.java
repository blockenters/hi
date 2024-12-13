package entity;

public class Lecture {
    // 과목명
    private String lectureName;
    // 최대 수강 인원
    private int maxStudents;
    // 현재 수강 인원
    private int currentStudents;

    public Lecture(String lectureName, int maxStudents) {
        this.lectureName = lectureName;
        this.maxStudents = maxStudents;
    }

    public boolean addStudent() {
        // 1. 현재 수강 인원이 최대 인원보다 적은지 확인합니다.
        if(currentStudents >= maxStudents){
            System.out.println("이과목은 수강인원이 다 차서 수강을 할 수 없습니다.");
            return false;
        }
        // 2. 여유가 있다면 currentStudents를 1 증가시키고 true를 반환합니다.
        currentStudents = currentStudents + 1;
        // 3. 강의가 이미 가득 찼다면 false를 반환합니다.
        return true;
    }

    public boolean removeStudent() {
        // 1. 현재 수강 인원이 0보다 큰지 확인합니다.
        if (currentStudents <= 0){
            System.out.println("수강인원은 0보다 작을수가 없습니다.");
            return false;
        }
        // 2. 수강 중인 학생이 있다면 currentStudents를 1 감소시키고 true를 반환합니다.
        currentStudents = currentStudents - 1;
        // 3. 수강 중인 학생이 없다면 false를 반환합니다.
        return true;
    }

    public void printInfo() {
        // 강의명, 현재 수강 인원, 최대 수강 인원을 출력합니다.
        System.out.println("강의명 : " +lectureName + " , 현수강인원은 " +
                currentStudents + "명 이고, 최대수강인원은 "+
                maxStudents +"명 입니다.");
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public int getCurrentStudents() {
        return currentStudents;
    }

    public void setCurrentStudents(int currentStudents) {
        this.currentStudents = currentStudents;
    }
}

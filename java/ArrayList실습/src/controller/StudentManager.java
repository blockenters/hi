package controller;

import entity.Student;

import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students;

    public StudentManager() {
        // TODO: students ArrayList를 초기화하세요.
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        // TODO: 학생을 ArrayList에 추가하는 코드를 작성하세요.
        students.add(student);
    }

    public void removeStudent(int id) {
        // TODO: 주어진 id와 일치하는 학생을 ArrayList에서 제거하는 코드를 작성하세요.
        // 힌트: ArrayList의 remove() 메소드를 사용하세요.

        for ( Student student : students){
            if( student.getId() == id ){
                // 어레이 리스트에서 삭제
                students.remove(student);
                return;
            }
        }

    }

    public Student findStudent(int id) {
        // TODO: 주어진 id와 일치하는 학생을 찾아 반환하는 코드를 작성하세요.
        // 힌트: for 루프를 사용하여 ArrayList를 순회하며 찾습니다.
        for (  Student student : students){
            if (student.getId() == id ){
                return student;
            }
        }
        return null; // 찾지 못한 경우 null 반환
    }

    public void displayAllStudents() {
        // TODO: 모든 학생의 정보를 출력하는 코드를 작성하세요.
        // 힌트: for-each 루프를 사용하여 ArrayList를 순회하며 각 학생 정보를 출력합니다.
        for(  Student student : students){
            student.print();
        }
    }


}

public class Student {

    String name;
    int score;

    void updateScore(int score) {
         this.score = 80;
    }

    void updateScore(int score, boolean isExtraCredit){
        if(isExtraCredit == true){
           this.score = score + 10;
        }else {
           this.score = score + 3;
        }
    }

    void printStudentInfor(){
        System.out.println(name + " , 점수 : " + score);
    }
}

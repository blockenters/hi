package entity;

public class MyDate {

    private int day;
    private int month;
    private int year;

    public MyDate() {
    }

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // todo 우리가 만들어야 할 메소드
    public boolean isValid(){

        // 월은, 1부터 12까지만 되어야 한다.
        // 일은, 1, 3, 5, 7, 8, 10, 12 월은 1부터 31까지 가능
        //      4, 6, 9, 11 월은 1부터 30까지 가능
        //      2 월은  4로 나누어서 나머지를 확인. 나머지가 0이면 1부터 29까지
        //                                      그렇지 않으면 1부터 28까지 가능
        if(month < 1 || month > 12){
            return false;
        }

        if(month == 1 || month == 3 || month == 5 || month == 7 ||
            month == 8 || month == 10 || month == 12){
            if(day < 1 || day > 31) {
                return false;
            }
        }else if(month == 4 || month == 6 || month == 9 || month == 11){
            if(day < 1 || day > 30){
                return false;
            }
        }else if(month == 2){
            // 윤년인 경우
            if(year % 4 == 0){
                if(day < 1 || day > 29){
                    return false;
                }
            } else {
                // 윤년이 아닌경우
                if(day < 1 || day > 28) {
                    return false;
                }
            }
        }
        return true;
    }


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

package entity;

public class Movie extends Video{

    // 감독 이름
    private String director;
    // 개봉년도
    private int releaseYear;

    public Movie(){

    }

    public Movie(String title, int duration, String resolution, String director, int releaseYear) {
        // 부모 클래스의 생성자 호출
        super(title, duration, resolution);
        // director와 releaseYear 초기화
        this.director = director;
        this.releaseYear = releaseYear;
    }

    @Override
    public void play() {
        // 부모 클래스의 play() 메서드 호출
        super.play();
        // "감독: [director], 개봉년도: [releaseYear]" 메시지 추가 출력
        System.out.println("감독: "+director+", 개봉년도: "+releaseYear);
    }

    @Override
    public void displayInfo() {
        // 부모 클래스의 displayInfo() 메서드 호출
        super.displayInfo();
        // 감독과 개봉년도 정보 추가 출력
        System.out.println("감독 : " +director);
        System.out.println("개봉년도 : " +releaseYear);
    }

    public void displayCast() {
        // "영화 [title]의 출연진 정보입니다." 메시지 출력
        System.out.println("영화 "+title+"의 출연진 정보입니다.");
        // (실제 출연진 정보는 구현하지 않음)
    }

    public void fullDisplay() {
        // displayInfo() 메서드 호출
        displayInfo();
        // play() 메서드 호출
        play();
        // displayCast() 메서드 호출
        displayCast();
        // adjustResolution() 메서드를 호출하여 해상도를 "4K"로 변경
        adjustResolution("4K");
    }

}





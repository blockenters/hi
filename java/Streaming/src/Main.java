import entity.Movie;

public class Main {

    public static void main(String[] args) {
        // Movie 객체 생성 (제목, 재생시간, 해상도, 감독, 개봉년도 지정)
        Movie m1 = new Movie("재밌는영화", 120, "1080p", "홍길동", 2024);
        // 생성된 Movie 객체의 fullDisplay() 메서드 호출
        m1.fullDisplay();
    }
}

package entity;

public class Content {
    // 제목
    protected String title;
    // 재생 시간
    protected int duration;

    public Content() {

    }

    public Content(String title, int duration) {
        // title과 duration을 초기화
        this.title = title;
        this.duration = duration;
    }

    public void play() {
        // "[title]을 재생합니다. 재생 시간: [duration]분" 메시지 출력
        System.out.println( title+"을 재생합니다. 재생 시간: "+duration+"분" );
    }

    public void displayInfo() {
        // 제목과 재생 시간 정보 출력
        System.out.println("제목 : " + title);
        System.out.println("재생 시간 : " + duration +"분");
    }
}

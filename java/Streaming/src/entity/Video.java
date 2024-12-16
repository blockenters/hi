package entity;

public class Video extends Content{

    // 해상도 : "720p", "1080p", "4K"
    protected String resolution;

    public Video(){

    }

    public Video(String title, int duration, String resolution) {
        // 부모 클래스의 생성자 호출
        super(title, duration);
        // resolution 초기화
        this.resolution = resolution;
    }

    @Override
    public void play() {
        // 부모 클래스의 play() 메서드 호출
        super.play();
        // "[resolution] 해상도로 재생합니다." 메시지 추가 출력
        System.out.println(resolution + " 해상도로 재생합니다.");
    }

    @Override
    public void displayInfo() {
        // 부모 클래스의 displayInfo() 메서드 호출
        super.displayInfo();
        // 해상도 정보 추가 출력
        System.out.println("해상도 : " + resolution);
    }

    public void adjustResolution(String newResolution) {
        // resolution을 newResolution으로 변경
        resolution = newResolution;
        // "해상도가 [newResolution]로 변경되었습니다." 메시지 출력
        System.out.println("해상도가 "+newResolution+"로 변경되었습니다.");
    }

}








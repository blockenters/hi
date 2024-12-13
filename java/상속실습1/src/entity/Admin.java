package entity;

public class Admin extends User{

    private String role;

    public Admin(String username, String email) {
        super(username, email);
        role = "관리자";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("역할 : " +role);
    }

    public void manageService() {
        // "관리자 [username]이(가) [role] 서비스를 관리합니다." 메시지 출력
        System.out.println( "관리자 "+username+"이(가) "+role+" 서비스를 관리합니다." );
    }


}





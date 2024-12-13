package entity;

public class User {

    protected String username;
    protected String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void displayInfo(){
        System.out.println( "사용자 : "+username+
                " , 이메일 : " +email);
    }
}

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 학생 이름과 점수를 저장하려 한다.
        // 이순신, 95
        // 홍길동, 80
        // 김나나, 70
        // 즉, 이름과 점수의 쌍(pair)으로 저장하고 싶다.
        // 이 쌍(pair)를 key와 value라고 합니다.

        // 변수 이름 하나로, 여러 데이터를 저장할 수 있다.
        HashMap<String, Integer> scoreMap = new HashMap<>();

        // 데이터 추가 : CRUD 의 C
        scoreMap.put("이순신", 95);
        scoreMap.put("홍길동", 80);
        scoreMap.put("김나나", 70);

        // 데이터 가져오기, 데이터 억세스 : CRUD 의 R
        // 해쉬맵은, Key로 한번에 데이터 억세스가 가능하다.
        // 김나나 점수는?
        System.out.println( scoreMap.get("김나나") );

        // 김나나의 점수를 88로 바꾸세요. : CRUD 의 U
        scoreMap.put("김나나", 88);

        System.out.println( scoreMap.get("김나나") );

        // 홍길동 데이터를 삭제하시오., : CRUD 의 D
        // 데이터 삭제도 key 로 한다.
        scoreMap.remove("홍길동");

        // 해쉬맵에 저장되어있는, 전체 데이터를 모두 가져와서 출력.
        for(  Map.Entry<String, Integer> scorePair : scoreMap.entrySet() ) {
            System.out.println( scorePair.getKey() + " : " + scorePair.getValue() );
        }

        // 데이터 모두 삭제
        scoreMap.clear();

        // 진짜 비어있는지 확인.
        System.out.println( scoreMap.isEmpty() );

    }
}

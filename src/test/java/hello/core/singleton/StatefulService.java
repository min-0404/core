package hello.core.singleton;

// 싱글톤 방식의 주의점 예시
public class StatefulService {
    private int price; // 상태를 유지하는 필드

    public void order(String name, int price){
        System.out.println("name = " + name + "price = " + price);
        this.price = price; // 여기가 문제 !!!!!!!!!!!!!!!!!!!!!!!!!!! : 상태를 유지하지 않는 필드(특정 클라이언트가 접근해서 수정해버릴수 있는 필드)
    }
    public int getPrice(){
        return price;
    }
}

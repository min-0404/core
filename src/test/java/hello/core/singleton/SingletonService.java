package hello.core.singleton;

public class SingletonService {

    // 1. 유일하게 사용할 수 있는 SingletonService 객체
    private static final SingletonService instance = new SingletonService();

    // 2. 외부에서 instance 객체에 접근할 수 있는 유일한 방법
    public static SingletonService getInstance(){
        return instance;
    }

    // 3. 생성자를 private 으로 설정 -> 외부에서 함부로 SingletonService 객체를 생성 할 수 없게 됨
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}

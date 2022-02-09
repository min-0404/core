package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonServiceTest {
    @Test
    @DisplayName("싱글톤 패턴 객체를 사용")
    public void singletonUse(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // singletonService1 과 singletonService2의 참조값이 같다는 것을 확인
        System.out.println(singletonService1);
        System.out.println(singletonService2);
        Assertions.assertThat(singletonService1).isNotSameAs(singletonService2);

        // 참고
        // isEqualTo 함수 : 객체가 동일한 타입인지만 비교
        // isSameAs 함수 : 객체의 참조값이 동일한지 비교(아예 동일한 객체인지 비교)
     }
}

package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.plaf.nimbus.State;

public class StatefulServiceTest {

    static class TestConfig{
        @Bean // 빈으로 설정
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        statefulService1.order("minseok", 1000);
        statefulService2.order("hyelime", 2000);

        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
        // 당연히 1000원이 나와야겠지만, 실제론 2000원이 출력됨 : statefulService1 과 statefulService2 는 스프링 컨테이너로 동일한 인스턴스를 사용하기 때문
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(2000);
    }
}
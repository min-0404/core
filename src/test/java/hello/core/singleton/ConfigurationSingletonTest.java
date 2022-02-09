package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        // 3개의 memberRepository 모두 같은 참조값을 가지는 것을 확인 가능
        System.out.println("memberService -> memberRepository :" + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository : " + orderService.getMemberRepository());
        System.out.println("memberRepository : " + memberRepository );

    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); // 이 때 AppConfig 도 빈으로 등록된다
        AppConfig appConfig = ac.getBean(AppConfig.class); // 빈 조회해보면 AppConfig 도 빈으로 등록되어 있다.
        System.out.println("bean = " + appConfig.getClass()); // 그런데 출력내용이 좀 이상하네?

        System.out.println("");
    }
}

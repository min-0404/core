package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // memberService1 과 memberService2 의 참조값이 다르다는 것을 알 수 있다.
        System.out.println(memberService1);
        System.out.println(memberService2);
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // memberService1 과 memberService2 의 참조값이 같다는 것을 알 수 있다.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = "  +memberService2);
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }

}
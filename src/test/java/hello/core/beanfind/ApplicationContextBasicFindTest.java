package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("1. 빈 이름 + 빈 타입으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        // 현재 빈에 memberService 라는 이름 + MemberService 타입으로 등록된 빈의 객체가 MemberServiceImpl 이라는 것을 확인할 수 있다.
    }

    @Test
    @DisplayName("2. 이름 없이 빈 타입만으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class); // 이름 없이 타입만으로 조회가능함. 근데 같은 타입 빈이 여러개이면...?
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        // 현재 빈에 memberService 타입으로 등록된 빈의 객체가 MemberServiceImpl 객체라는 것을 확인할 수 있다.
    }

    @Test
    @DisplayName("3. 빈 구현체의 타입으로 조회") // 별로 좋은 방법은 아님
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("4. 빈 이름으로 조회X")
    void findBeanByNameX(){
        // ac.getBean("xxxx", MemberService.class);
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () ->
                ac.getBean("xxxxx", MemberService.class)); // 에러가 터져야 성공임
    }


}

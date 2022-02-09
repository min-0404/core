package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 애플리케이션의 설정 정보라고 보면 된다
public class AppConfig {

    @Bean // 빈 설정시 스프링 컨테이너에 등록됨
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
        // MemberServiceImpl 구현체를 선택했고, "memberRepository" 빈에 의존 관계가 있다
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        // OrderServiceImpl 구현체를 선택했고, "memberRepository" 빈, "discountPolicy" 빈에 의존 관계가 있다
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
        // MemoryMemberRepository 구현체를 선택했다.
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
        // RateDiscountPolicy 구련체를 선택했다.
    }
}

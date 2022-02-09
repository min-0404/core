package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextSameBeanFindTest {

    // 해당 테스트에서만 사용할 임시 클래스
    @Configuration
    static class SameBeanConfig{
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
        // "memberRepository1" 과 "memberRepository2" 의 빈 구현체는 동일하다
    }

    // 위에서 만든 SameBeanConfig 클래스를 구성정보로 컨테이너 구현체 생성
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("빈 타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류가 발생한다")
    void findBeanByTypeDuplicate(){
        // DiscountPolicy bean = ac.getBean(MemberRepository.class); 오류발생 : MemberRepository 객체를 가진 애가 두개 있어서
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("빈 타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름으로 찾으면 된다. 당연한걸...")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class); // String 은 빈이름, MemberRepository 는 빈 타입
        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }
}

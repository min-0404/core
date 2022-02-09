package hello.core.beanfind;

import hello.core.MemberApp;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    // 해당 테스트에서만 사용할 임시 클래스
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
        // "rateDiscountPolicy" 와 "fixDiscountPolicy" 는 부모 타입은 같지만, 구현체는 다르다.
    }

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    // TestConfig 클래스를 구성정보로 스프링 컨테이너 구현체를 생성.

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate(){
        // DiscountPolicy bean = ac.getBean(DiscountPolicy.class); 오류 발생!!
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈이름으로 찾으면 된다... 당연한 걸")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy discountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회하기") // 별로 안좋은 방법
    void findBeanBySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 조회하기")
    void findBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
        for(String key : beansOfType.keySet()){
            System.out.println("key = " +  key + "value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object") // 중요!!!! 모든 객체의 최종 부모는 Object 이다.
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key + "value = "+ beansOfType.get(key));
        }
    }
}

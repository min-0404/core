package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 함")
    void vip_o(){
        //given
        Member member1 = new Member(1L, "memberA", Grade.VIP);
        //when
        int discount = rateDiscountPolicy.discount(member1, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP 가 아니면 할인이 적용안됨")
    void vip_x(){
        //given
        Member member2 = new Member(2L, "memberB", Grade.BASIC);
        //when
        int discount = rateDiscountPolicy.discount(member2, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}

package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price); // 할인가격(할인된 가격은 아님)을 반환하는 기능
}

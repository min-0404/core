package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository; // 주입할 인터페이스를 선언만 해줌
    private final DiscountPolicy discountPolicy; // 주입할 인터페이스를 선언만 해줌
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){ // 생성자 주입 : 어떤 구현체를 사용할지 선택하지 않는다
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
        // 어떤 구현체를 사용할지는 AppConfig 에서 결정함
    }

    // 싱글톤이 지켜지고 있는지 확인하기 위한 테스트 코드
    public MemberRepository getMemberRepository(){
        return this.memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName,itemPrice, discountPrice);
    }
}


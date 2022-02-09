package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository; // 주입할 인터페이스를 선언만 해줌
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository){ // 생성자 주입
        this.memberRepository = memberRepository;
        // 어떤 구현체를 쓸지는 AppConfig 에서 결정해줌
    }

    // 싱글톤이 지켜지고 있는지 확인하기 위한 임시 테스트 코드
    public MemberRepository getMemberRepository(){
        return this.memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

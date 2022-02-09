package hello.core.member;

public interface MemberRepository {
    void save(Member member); // 1. 저장 기능
    Member findById(Long memberId); // 2. 찾기 기능
}

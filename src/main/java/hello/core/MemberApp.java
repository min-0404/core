package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// MemberService 를 테스트하기 위해 만든 파일
public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = new MemberServiceImpl();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // AppConfig 의 클래스들을 구성정보로 스프링 컨테이너를 생성한다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}

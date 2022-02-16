package hello.core.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.lang.reflect.Member;
import java.util.Optional;

// 주입할 스프링 빈이 없을때 대처법
public class AutoWiredTest {

    static class TestBean{

        @Autowired(required = false) // 방법 1
        public void setNoBean1(Member member){
            System.out.println("member1 =  " + member);
        }

        @Autowired // 방법 2
        public void setNoBean2(@Nullable Member member){
            System.out.println("member2 = " + member);
        }

        @Autowired(required = false) // 방법 3
        public void setNoBean3(Optional<Member> member){
            System.out.println("member3 = " + member);
        }
    }

    @Test
    void autoWiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); // TestBean 클래스를 빈으로 등록해주기
    }
}

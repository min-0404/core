package hello.core;

import hello.core.member.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Configuration
@ComponentScan( // AppConfig 파일의 내용은 스캔에서 제외시키기 위해 Configuration 으로 어노테이션 된 것들은 제외한다는 의미
        basePackages =  "hello.core.member", // memeber 패키지 안의 파일들만 스캔 대상이 됨
        excludeFilters =  @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =  Configuration.class))
public class AutoAppConfig {
    // AppConfig 처럼 여기에 빈을 직접 자바코드로 작성할 필요가 없음
    // 물론, 수동으로 빈 등록하고 싶으면 여기에 코드 작성하면 됨
}


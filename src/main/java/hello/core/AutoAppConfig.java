package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Configuration
@ComponentScan(excludeFilters =  @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =  Configuration.class))
public class AutoAppConfig {
    // AppConfig 처럼 여기에 빈을 직접 자바코드로 작성할 필요가 없음

}

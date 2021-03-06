package grace.advenced;

import grace.advenced.trace.logtrace.LogTrace;
import grace.advenced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean // 빈 수동 등록 (싱글톤으로 등록됨)
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}

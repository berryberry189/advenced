package grace.advenced.trace.strategy;

import grace.advenced.trace.strategy.code.ContextV2;
import grace.advenced.trace.strategy.code.StrategyLogic1;
import grace.advenced.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1(){
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());
    }

    /**
     * 전략패턴 익명 내부 클래스
     */
    @Test
    void strategyV2(){
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(() -> log.info("비지니스 로직1 실행"));
        contextV2.execute(() -> log.info("비지니스 로직2 실행"));
    }


}

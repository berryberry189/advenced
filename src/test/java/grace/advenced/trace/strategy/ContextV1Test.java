package grace.advenced.trace.strategy;

import grace.advenced.trace.strategy.code.strategy.ContextV1;
import grace.advenced.trace.strategy.code.strategy.Strategy;
import grace.advenced.trace.strategy.code.strategy.StrategyLogic1;
import grace.advenced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0(){
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        log.info("비지니스 로직1 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        log.info("비지니스 로직2 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    /**
     * 전략 패턴 사용
     */
    @Test
    void strategyV1(){
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }

    /**
     * 익명 내부 클래스 사용
     */
    @Test
    void strategyV2(){
        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        };
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        // strategyLogic1=class grace.advenced.trace.strategy.ContextV1Test$1
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        contextV1.execute();

        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        };
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        // strategyLogic2=class grace.advenced.trace.strategy.ContextV1Test$2
        log.info("strategyLogic2={}", strategyLogic2.getClass());
        contextV2.execute();
    }

    @Test
    void strategyV3(){
        ContextV1 contextV1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
        contextV2.execute();
    }

    /**
     * 람다 사용
     * - 람다로 변경하려면 인터페이스에 메소드가 1개만 있으면 된다.
     */
    @Test
    void strategyV4(){
        ContextV1 contextV1 = new ContextV1(() -> log.info("비지니스 로직1 실행"));
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(() -> log.info("비지니스 로직2 실행"));
        contextV2.execute();
    }


}

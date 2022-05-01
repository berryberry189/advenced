package grace.advenced.app.v3;

import grace.advenced.trace.TraceStatus;
import grace.advenced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(String itemId){

        TraceStatus status = null;
        try{
            status = trace.begin("OrderRepositoryV1.request()");
            // 저장 로직
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생!!");
            }
            sleep(1000); // 1초
            trace.end(status);
        } catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외 던지기
        }
    }

    private void sleep(int mullis){
        try {
            Thread.sleep(mullis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

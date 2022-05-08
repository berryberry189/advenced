package grace.advenced.app.v2;

import grace.advenced.trace.TraceId;
import grace.advenced.trace.TraceStatus;
import grace.advenced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId){

        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId, "OrderRepository.request()");
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

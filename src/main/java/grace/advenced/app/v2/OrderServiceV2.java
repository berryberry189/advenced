package grace.advenced.app.v2;

import grace.advenced.trace.TraceId;
import grace.advenced.trace.TraceStatus;
import grace.advenced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId){
        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId, "OrderService.request()");
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외 던지기
        }
    }

}

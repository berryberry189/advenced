package grace.advenced.app.v5;

import grace.advenced.trace.callback.TraceTemplate;
import grace.advenced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId){
        template.execute("OrderRepository.request()", ()->{
            // 저장 로직
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생!!");
            }
            sleep(1000); // 1초
            return null;
        });
    }

    private void sleep(int mullis){
        try {
            Thread.sleep(mullis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

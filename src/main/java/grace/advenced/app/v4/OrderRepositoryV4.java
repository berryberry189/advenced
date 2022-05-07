package grace.advenced.app.v4;

import grace.advenced.trace.logtrace.LogTrace;
import grace.advenced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId){

        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                // 저장 로직
                if(itemId.equals("ex")){
                    throw new IllegalStateException("예외 발생!!");
                }
                sleep(1000); // 1초
                return null;
            }
        };
        template.execute("OrderRepository.request()");
    }

    private void sleep(int mullis){
        try {
            Thread.sleep(mullis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

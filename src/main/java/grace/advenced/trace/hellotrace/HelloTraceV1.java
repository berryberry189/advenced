package grace.advenced.trace.hellotrace;

import grace.advenced.trace.TraceId;
import grace.advenced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV1 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<-X";


    public TraceStatus begin(String message) {
        TraceId id = new TraceId();
        long startTimeMs = System.currentTimeMillis();
        // 로그 출력
        log.info("[{}] {}{}", id.getId(), assSpace(START_PREFIX, id.getLevel()), message);
        return new TraceStatus(id, startTimeMs, message);
    }

    public void end(TraceStatus status) {
        complete(status, null);
    }

    // 예외 발생 시
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), assSpace(START_PREFIX, traceId.getLevel()),
                    status.getMessage(), resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), assSpace(EX_PREFIX, traceId.getLevel()),
                    status.getMessage(), resultTimeMs, e.toString());
        }

    }

    private static String assSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|  ");
        }
        return sb.toString();
    }
}

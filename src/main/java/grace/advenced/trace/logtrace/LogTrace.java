package grace.advenced.trace.logtrace;

import grace.advenced.trace.TraceStatus;

// 로그 추척기
public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);

}

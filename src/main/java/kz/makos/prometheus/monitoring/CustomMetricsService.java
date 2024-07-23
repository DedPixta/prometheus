package kz.makos.prometheus.monitoring;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomMetricsService {

    private final MeterRegistry meterRegistry;

    public CustomMetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;

        Counter.builder("http_loan_requests_total")
                .description("Total number of customer check HTTP requests")
                .tags("type", "check_customer", "status", "2xx")
                .register(meterRegistry);
    }

    public void incrementCheckCustomerRequests(int statusCode) {
        String statusCategory = categorizeStatusCode(statusCode);
        meterRegistry.counter("http_loan_requests_total",
                Tags.of("type", "check_customer", "status", statusCategory)).increment();
    }

    public void incrementLoanActivationRequests(int statusCode) {
        String statusCategory = categorizeStatusCode(statusCode);
        meterRegistry.counter("http_loan_requests_total",
                Tags.of("type", "loan_activation", "status", statusCategory)).increment();
    }

    private String categorizeStatusCode(int statusCode) {
        if (statusCode >= 200 && statusCode < 300) {
            return "2xx";
        } else if (statusCode >= 300 && statusCode < 400) {
            return "3xx";
        } else if (statusCode >= 400 && statusCode < 500) {
            return "4xx";
        } else if (statusCode >= 500) {
            return "5xx";
        } else {
            return "other";
        }
    }
}
package kz.makos.prometheus.service;

import kz.makos.prometheus.monitoring.CustomMetricsService;
import kz.makos.prometheus.util.Randomizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoanActivationService {

    private final CustomMetricsService metricsService;

    @Scheduled(fixedRate = 100)
    public void generateCheckCustomerRequest() throws InterruptedException {
        Thread.sleep(Randomizer.getRandomSeconds(1, 2));
        int httpStatusCode = Randomizer.getRandomHttpStatusCode();
        metricsService.incrementCheckCustomerRequests(httpStatusCode);
        if (httpStatusCode >= 200 && httpStatusCode < 300) {
            for (int i = 0; i < 3; i++) {
                metricsService.incrementCheckCustomerRequests(httpStatusCode);
            }
        }
        log.info("Customer check - http code {}", httpStatusCode);
    }

    @Scheduled(fixedRate = 200)
    public void generateLoanActivationRequest() throws InterruptedException {
        Thread.sleep(Randomizer.getRandomSeconds(3, 7));
        int httpStatusCode = Randomizer.getRandomHttpStatusCode();
        metricsService.incrementLoanActivationRequests(httpStatusCode);
        if (httpStatusCode >= 200 && httpStatusCode < 300) {
            for (int i = 0; i < 3; i++) {
                metricsService.incrementLoanActivationRequests(httpStatusCode);
            }
        }
        log.info("Loan activation - http code {}", httpStatusCode);
    }
}

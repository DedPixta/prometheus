package kz.makos.prometheus;

import kz.makos.prometheus.monitoring.CustomMetricsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@SpringBootApplication
public class PrometheusApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(PrometheusApplication.class, args);
        CustomMetricsService metricsService = ctx.getBean(CustomMetricsService.class);

        while (true) {
            metricsService.incrementCustomMetric();
            log.info("Custom metric incremented");
            Thread.sleep(getRandomSeconds());
        }

    }

    public static long getRandomSeconds() {
        // Генерация случайного числа от 1 до 15 включительно
        return ThreadLocalRandom.current().nextLong(1, 16) * 1000L;
    }

}

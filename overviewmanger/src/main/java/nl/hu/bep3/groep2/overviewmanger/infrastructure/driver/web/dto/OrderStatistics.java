package nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.web.dto;

import java.time.Duration;

public record OrderStatistics(Duration quickestOrderCompletionTime, Duration longestOrderCompletionTime,
                              Duration averageOrderCompletionTime, Duration averageTimePerMeal) {
}

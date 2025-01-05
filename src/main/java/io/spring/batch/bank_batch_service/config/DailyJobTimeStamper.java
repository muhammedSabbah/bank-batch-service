package io.spring.batch.bank_batch_service.config;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.util.Date;

import static io.spring.batch.bank_batch_service.constant.Const.CURRENT_DATE;

public class DailyJobTimeStamper implements JobParametersIncrementer {

    @Override
    public JobParameters getNext(JobParameters parameters) {
        return new JobParametersBuilder(parameters)
                .addDate(CURRENT_DATE, new Date())
                .toJobParameters();
    }
}

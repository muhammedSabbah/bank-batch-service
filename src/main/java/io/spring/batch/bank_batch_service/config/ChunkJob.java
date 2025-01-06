package io.spring.batch.bank_batch_service.config;

import io.spring.batch.bank_batch_service.listener.LoggingChunkStartStopListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class ChunkJob {
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    public ChunkJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job chunkBasedJob() {
        return this.jobBuilderFactory.get("chunkBasedJob")
                .start(chunkStep())
                .build();
    }

    @Bean
    public Step chunkStep() {
        return this.stepBuilderFactory.get("chunkStep")
                .<String, String>chunk(5)
                .reader(itemReader())
                .writer(itemWriter())
                .listener(new LoggingChunkStartStopListener())
                .build();
    }

    @Bean
    public ListItemReader<String> itemReader() {
        List<String> items = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            items.add(i + " - " + UUID.randomUUID().toString());
        }
        return new ListItemReader<>(items);
    }

    @Bean
    public ItemWriter<String> itemWriter() {
        return items -> {
            for (String item : items) {
                System.out.println(">> current item = " + item);
            }
        };
    }
}

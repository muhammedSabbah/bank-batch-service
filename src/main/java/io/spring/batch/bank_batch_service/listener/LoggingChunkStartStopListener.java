package io.spring.batch.bank_batch_service.listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

public class LoggingChunkStartStopListener {
    @BeforeChunk
    public void beforeStep(ChunkContext chunkContext) {
        System.out.println(chunkContext.getStepContext().getStepName() + " has begun!");
    }

    @AfterChunk
    public void afterStep(ChunkContext chunkContext) {
        System.out.println(chunkContext.getStepContext().getStepName() + " has ended!");
    }
}

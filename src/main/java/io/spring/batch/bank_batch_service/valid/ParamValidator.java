package io.spring.batch.bank_batch_service.valid;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

import static io.spring.batch.bank_batch_service.constant.Const.CSV;
import static io.spring.batch.bank_batch_service.constant.Const.FILE_NAME;

public class ParamValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        String name = parameters.getString(FILE_NAME);
        if (!StringUtils.hasText(name)) {
            throw new JobParametersInvalidException("Name parameter is missing");
        } else if (!StringUtils.endsWithIgnoreCase(name, CSV)) {
            throw new JobParametersInvalidException("Name parameter does not use the csv file extension");
        }
    }
}

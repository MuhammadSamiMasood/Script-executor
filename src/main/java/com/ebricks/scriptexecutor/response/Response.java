package com.ebricks.scriptexecutor.response;

import com.ebricks.scriptexecutor.executor.StepExecutorResponse;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private List<StepExecutorResponse> stepExecutorResponses = new ArrayList<StepExecutorResponse>();

    public List<StepExecutorResponse> getStepExecutorResponses() {
        return stepExecutorResponses;
    }

    public void setStepExecutorResponses(List<StepExecutorResponse> stepExecutorResponses) {
        this.stepExecutorResponses = stepExecutorResponses;
    }
}

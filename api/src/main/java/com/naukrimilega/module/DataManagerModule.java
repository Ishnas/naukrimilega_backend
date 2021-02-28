package com.naukrimilega.module;

import com.google.inject.AbstractModule;
import com.naukrimilega.service.jobs.JobsService;

public class DataManagerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IJobsService.class).to(JobsService.class).asEagerSingleton();
    }
}

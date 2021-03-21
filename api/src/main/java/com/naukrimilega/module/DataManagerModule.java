package com.naukrimilega.module;

import com.google.inject.AbstractModule;
import com.naukrimilega.firebaseutil.service.DatabaseService;
import com.naukrimilega.firebaseutil.service.IDatabaseService;

public class DataManagerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IDatabaseService.class).to(DatabaseService.class).asEagerSingleton();
    }
}

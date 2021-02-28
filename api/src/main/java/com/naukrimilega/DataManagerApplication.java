package com.naukrimilega;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.naukrimilega.firebaseutil.InitializeFirebase;
import com.naukrimilega.configs.DataManagerConfigs;
import com.naukrimilega.facade.JobsFacade;
import com.naukrimilega.health.AppHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import com.naukrimilega.module.DataManagerModule;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class DataManagerApplication extends Application<DataManagerConfigs> {

    @Override
    public void initialize(Bootstrap<DataManagerConfigs> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<DataManagerConfigs>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DataManagerConfigs configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }

    public static void main(String[] args) throws Exception {
        new DataManagerApplication().run(args);
    }

    @Override
    public void run(DataManagerConfigs dataManagerConfigs, Environment environment) throws Exception {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        Injector injector = Guice.createInjector(new DataManagerModule());
        registerResources(environment, injector);
        environment.healthChecks().register("health", new AppHealthCheck());

        String serviceAccFilePath = "/Users/santanu.naskar/IdeaProjects/data-manager/api/src/main/resources/naukrimilega-32e0e.json";
        String dbUrl = "https://naukrimilega-32e0e-default-rtdb.firebaseio.com/";
        InitializeFirebase.initialize(serviceAccFilePath, dbUrl);

    }

    private void registerResources(Environment environment, Injector injector) {
        environment.jersey().register(injector.getInstance(JobsFacade.class));
    }
}
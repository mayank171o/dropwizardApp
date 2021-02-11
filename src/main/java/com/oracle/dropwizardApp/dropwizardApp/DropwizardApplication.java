package com.oracle.dropwizardApp.dropwizardApp;

import com.oracle.dropwizardApp.dropwizardApp.health.TemplateHealthCheck;
import com.oracle.dropwizardApp.dropwizardApp.resources.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardApplication extends Application<DropwizardConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizardAppSample";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardConfiguration configuration,
                    final Environment environment) {
    	final HelloWorldResource resource = new HelloWorldResource(
    	        configuration.getTemplate(),
    	        configuration.getDefaultName()
    	    );
    	final TemplateHealthCheck healthCheck =
    	        new TemplateHealthCheck(configuration.getTemplate());
    	    environment.healthChecks().register("template", healthCheck);
    	    environment.jersey().register(resource);
    }

}

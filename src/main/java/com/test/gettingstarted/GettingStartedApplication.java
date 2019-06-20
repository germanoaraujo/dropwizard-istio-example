package com.test.gettingstarted;

import com.test.gettingstarted.health.TemplateHealthCheck;
import com.test.gettingstarted.orika.MapperModule;
import com.test.gettingstarted.resources.AnotherResource;
import com.test.gettingstarted.resources.HelloWorldResource;
import com.test.gettingstarted.resources.JohnsResource;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.bundles.version.VersionBundle;
import io.dropwizard.bundles.version.VersionSupplier;
import io.dropwizard.bundles.version.suppliers.MavenVersionSupplier;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class GettingStartedApplication extends Application<GettingStartedApplicationConfiguration> {

	private final VersionSupplier supplier = new MavenVersionSupplier("com.test", "getting-started-dropwizard");

	private GuiceBundle<GettingStartedApplicationConfiguration> guiceBundle;

	public static void main(String[] args) throws Exception {
		new GettingStartedApplication().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<GettingStartedApplicationConfiguration> bootstrap) {
		/* Hooks up guice for dependency injection and registers any Guice modules we need */
		guiceBundle = GuiceBundle.<GettingStartedApplicationConfiguration>newBuilder()
				.setConfigClass(GettingStartedApplicationConfiguration.class)
				.addModule(new GettingStartedModule())
				.addModule(new MapperModule())
				.addModule(new AbstractModule() {
					@Override
					protected void configure() {
						// Bind the version to a string so it could be injected if needed
						bind(String.class).annotatedWith(Names.named("version")).toInstance(supplier.getApplicationVersion());
					}
				})
				.build();
		bootstrap.addBundle(guiceBundle);

		/* Creates a /version endpoint on the admin port that will display the version of the application and its dependencies */
		bootstrap.addBundle(new VersionBundle(supplier));
	}

	@Override
	public void run(final GettingStartedApplicationConfiguration configuration, Environment environment) {

		/* Create our (singleton) resources... */
		final HelloWorldResource resource = new HelloWorldResource(
				configuration.getTemplate(),
				configuration.getDefaultName(),
				configuration.getVersion()
		);

		/* ... and then register them with Jersey */
		environment.jersey().register(resource);
		final AnotherResource another = new AnotherResource(configuration.getVersion());
		environment.jersey().register(another);
		environment.jersey().register(JohnsResource.class);

		/* Set up health checks */
		final TemplateHealthCheck healthCheck =
				new TemplateHealthCheck(configuration.getTemplate());

		environment.healthChecks().register("template", healthCheck);
	}
}

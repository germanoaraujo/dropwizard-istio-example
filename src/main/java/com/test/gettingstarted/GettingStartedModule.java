package com.test.gettingstarted;

import com.google.inject.AbstractModule;
import com.test.gettingstarted.core.JohnsService;
import com.test.gettingstarted.core.SimpleJohnsService;

public class GettingStartedModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(JohnsService.class).to(SimpleJohnsService.class);
	}
}

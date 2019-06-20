package com.test.gettingstarted.resources;

import com.test.gettingstarted.orika.MapperModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;

public class MapperTestHelper {

	private MapperFactory factory;
	private Injector injector;

	public MapperTestHelper() {
		injector = Guice.createInjector(new MapperModule());
	}

	public MapperFacade getMapper() {
		return injector.getInstance(MapperFacade.class);
	}
}

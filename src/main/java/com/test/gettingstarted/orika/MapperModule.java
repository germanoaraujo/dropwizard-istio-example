package com.test.gettingstarted.orika;

import com.test.gettingstarted.core.JohnsModel;
import com.test.gettingstarted.views.JohnsViewModel;
import com.google.inject.AbstractModule;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class MapperModule extends AbstractModule {
	@Override
	protected void configure() {
		final MapperFactory factory = new DefaultMapperFactory.Builder().build();
		factory.classMap(JohnsModel.class, JohnsViewModel.class);

		bind(MapperFacade.class).toInstance(factory.getMapperFacade());
	}
}

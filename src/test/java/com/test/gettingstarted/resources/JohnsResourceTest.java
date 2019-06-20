package com.test.gettingstarted.resources;

import com.test.gettingstarted.core.JohnsModel;
import com.test.gettingstarted.core.JohnsService;
import com.test.gettingstarted.views.JohnsViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class JohnsResourceTest {

	private MapperTestHelper mapperHelper = new MapperTestHelper();

	@Mock
	private JohnsService serviceMock;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSayHelloReturnsProperMessage() {
		JohnsModel model = new JohnsModel("blue", 1);
		Mockito.when(serviceMock.getInfo()).thenReturn(model);

		JohnsResource resource = new JohnsResource(serviceMock, mapperHelper.getMapper());
		Response resp = resource.sayHello();
		JohnsViewModel result = (JohnsViewModel)resp.getEntity();
		assertEquals("blue", result.getFavoriteColor());
		assertEquals(1, result.getFavoriteNumber());
	}
}

package com.test.gettingstarted.resources;


import com.codahale.metrics.annotation.Timed;
import com.test.gettingstarted.core.Saying;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/another")
@Produces(MediaType.APPLICATION_JSON)
public class AnotherResource {
    private final String version;
    private final AtomicLong counter;

    public AnotherResource(String version) {
        this.version = version;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = "I'm another " + name.or("test");
        return new Saying(counter.incrementAndGet(), value, version);
    }
}

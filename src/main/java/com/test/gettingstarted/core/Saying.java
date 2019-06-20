package com.test.gettingstarted.core;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Saying {
    private long id;

    @Length(max = 3)
    private String content;

    private String version;;

    public Saying(){
    }

    public Saying(long id, String content, String version) {
        this.id = id;
        this.content = content;
        this.version = version;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    @JsonProperty
    public String getVersion() {
        return version;
    }
}

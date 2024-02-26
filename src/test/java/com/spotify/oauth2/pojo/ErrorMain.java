
package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Generated("jsonschema2pojo")
public class ErrorMain {

    @JsonProperty("error")
    private InnerError error;

    @JsonProperty("error")
    public InnerError getError() {

        return error;
    }

    @JsonProperty("error")
    public void setError(InnerError error) {

        this.error = error;
    }


}

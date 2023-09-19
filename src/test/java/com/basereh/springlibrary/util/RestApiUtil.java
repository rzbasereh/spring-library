package com.basereh.springlibrary.util;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Component
@RequiredArgsConstructor
public class RestApiUtil {
    private final MockMvc mockMvc;

    public MvcResult postRequest(String path, JSONObject body) {
        try {
            return mockMvc.perform(
                    post(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body.toString())
                            .accept(MediaType.APPLICATION_JSON)
            ).andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MvcResult getRequest(String path) {
        try {
            return mockMvc.perform(
                    get(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
            ).andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MvcResult putRequest(String path, JSONObject body) {
        try {
            return mockMvc.perform(
                    put(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body.toString())
                            .accept(MediaType.APPLICATION_JSON)
            ).andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MvcResult deleteRequest(String path) {
        try {
            return mockMvc.perform(
                    delete(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
            ).andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

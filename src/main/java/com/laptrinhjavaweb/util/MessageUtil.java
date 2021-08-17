package com.laptrinhjavaweb.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageUtil {
    public Map<String, String> getMessage(String message) {
        Map<String, String> result = new HashMap<>();
        if (message.equals("update_success")) {
            result.put("message", "update success");
            result.put("alert", "success");
        } else if (message.equals("insert_success")) {
            result.put("message", "insert success");
            result.put("alert", "success");
        } else if (message.equals("delete_success")) {
            result.put("message", "Deleted success");
            result.put("alert", "success");
        } else if (message.equals("sign_up_success")){
            result.put("message", "Sign up success, login now !");
            result.put("alert", "success");
        } else {
            result.put("message", "error system");
            result.put("alert", "danger");
        }
        return result;
    }
}

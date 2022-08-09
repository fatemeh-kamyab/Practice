package com.example.service;

import com.example.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

    public String helloworldByName(String name) {
        return "Hello ".concat(StringUtils.splitCamelCase(name));
    }
}
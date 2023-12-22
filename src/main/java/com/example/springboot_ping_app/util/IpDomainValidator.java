package com.example.springboot_ping_app.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Data
@Component
public class IpDomainValidator {

    private final String IP_ADDRESS_PATTERN = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b";
    private final String DOMAIN_NAME_PATTERN = "^(?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.(?!-)[A-Za-z]{2,63}(?<!-)$";


    public boolean isIpName(String input) {
        return Pattern.matches(IP_ADDRESS_PATTERN, input);
    }

    public boolean isDomainName(String input) {
        return Pattern.matches(DOMAIN_NAME_PATTERN, input);
    }

}

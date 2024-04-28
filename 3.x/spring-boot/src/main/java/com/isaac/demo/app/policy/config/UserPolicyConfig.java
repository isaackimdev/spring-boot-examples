package com.isaac.demo.app.policy.config;

import com.isaac.demo.app.policy.RateUserDiscountPolicy;
import com.isaac.demo.app.policy.UserDiscountPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPolicyConfig {

    @Bean
    public UserDiscountPolicy userDiscountPolicy() {
        return new RateUserDiscountPolicy();
    }
}

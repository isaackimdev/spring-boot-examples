package com.isaac.demo.app.policy;

import com.isaac.demo.app.user.entity.User;

public interface UserDiscountPolicy {
    long discount(User user, long price);
}

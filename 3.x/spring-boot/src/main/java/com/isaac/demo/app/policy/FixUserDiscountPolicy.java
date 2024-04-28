package com.isaac.demo.app.policy;

import com.isaac.demo.app.user.entity.AgeInfo;
import com.isaac.demo.app.user.entity.User;

public class FixUserDiscountPolicy implements UserDiscountPolicy {

    private final int discountPrice = 3000;
    @Override
    public long discount(User user, long price) {
        if(user.getAgeInfo() == AgeInfo.JUNIOR) {
            return discountPrice;
        }
        return 0;
    }
}

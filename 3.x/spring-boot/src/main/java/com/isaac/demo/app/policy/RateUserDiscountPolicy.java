package com.isaac.demo.app.policy;

import com.isaac.demo.app.user.entity.AgeInfo;
import com.isaac.demo.app.user.entity.User;

public class RateUserDiscountPolicy implements UserDiscountPolicy{
    private final long discountPercent = 20;
    @Override
    public long discount(User user, long price) {
        if(user.getAgeInfo() == AgeInfo.JUNIOR) {
            return (long) (price * discountPercent / 100);
        }
        return 0;
    }
}

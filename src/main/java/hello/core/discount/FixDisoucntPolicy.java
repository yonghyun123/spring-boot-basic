package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDisoucntPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        } else {
            return 0;
        }

    }
}

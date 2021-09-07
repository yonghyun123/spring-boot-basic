package hello.core.order;

import hello.core.discount.FixDisoucntPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    @Test
    void createOrder(){
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDisoucntPolicy());
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "memberA", Grade.VIP));

        Order itemA = orderService.createOrder(1L, "itemA", 100000);
        Assertions.assertThat(itemA.getDiscountPrice()).isEqualTo(1000);


    }
}

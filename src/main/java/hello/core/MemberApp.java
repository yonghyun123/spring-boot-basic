package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberServciceImpl;
import hello.core.member.MemberService;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServciceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMemeber(1L);

        System.out.println("member name"+member.getName());
        System.out.println("find member name"+findMember.getName());


    }
}

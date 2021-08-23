package hello.core.member;

public class MemberServciceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServciceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMemeber(Long id) {
        return memberRepository.findById(id);
    }

}

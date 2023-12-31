package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private MemberRepository memberRepository;
    // 생성자를 통해 memberRepository인스턴스를 결정해준다. (생성자 주입)
    // 인스턴스는 AppConfig.java에서 주입해준다.
    // public MemberServiceImpl(MemberRepository memberRepository) {
    //     this.memberRepository = memberRepository;
    // }
    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

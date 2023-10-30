package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    
    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given 
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then      검증 Assertions.assertThat
//      AssertJ의 assertThat을 사용해야하는 이유는 자동완성, Assertion 분류, 확장성 입니다.
//      필요한 메소드를 검색하고 알맞은 항목을 찾아 import 해야하는 번거로움이 없을 뿐더러, 가독성도 좋습니다.
//      가독성 좋은 코드는 유지보수와 리펙토링이 용이하기 때문에 생산성을 향상시키는 좋은 효과를 불러옵니다.
        Assertions.assertThat(member).isEqualTo(findMember);
    }

}

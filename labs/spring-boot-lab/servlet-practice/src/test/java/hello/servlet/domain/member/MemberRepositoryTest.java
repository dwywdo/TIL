package hello.servlet.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

// JUnit 5부터는 테스트 클래스에 Public이 없어도 된다.
class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // given
        final Member member = new Member("hello", 20);

        // when
        final Member savedMember = memberRepository.save(member);

        // then
        final Member foundMember = memberRepository.findById(savedMember.getId());
        assertThat(foundMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        // given
        final Member member1 = new Member("member1", 20);
        final Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        final List<Member> result = memberRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}

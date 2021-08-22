package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJapMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 인터페이스 메소드 이름으로도 쿼리를 짤 수 있다.
    //JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}

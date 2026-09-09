package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

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

    // 테스트용
    // 스프링 빈을 등록하는 과정에서 생성자가 여러번 호출되는데,
    // 과연 Singleton이 유지되는지 확인하기 위한 메서드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}

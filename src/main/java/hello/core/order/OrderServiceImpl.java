package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(
            MemberRepository memberRepository,
            DiscountPolicy discountPolicy
    ) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = this.memberRepository.findById(memberId);
        int discountPrice = this.discountPolicy.discount(member, itemPrice);

        return new Order(
                memberId,
                itemName,
                itemPrice,
                discountPrice
        );
    }

    // 테스트용
    // 스프링 빈을 등록하는 과정에서 생성자가 여러번 호출되는데,
    // 과연 Singleton이 유지되는지 확인하기 위한 메서드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

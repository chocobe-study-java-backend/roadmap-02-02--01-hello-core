package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // @Bean memberService => new MemoryMemberRepository()
    // @Bean orderService => new MemoryMemberRepository()
    // 여기서 의문이 생김
    // => @Bean 을 생성하면서 new 가 여러번 호출되는 객체가 보인다
    // => Singleton이 유지될 수 있는건가???
    //
    // 결과: `ConfigurationSingletonTest`
    // => 싱글톤이 보장 되더라!

    // 아래 @Bean 메서드 호출에 의한 콘솔 출력 확인하기
    // => "call AppConfig.memberRepository" 1번만 호출됨!

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

}

package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{ // Impl단에서 실제 구현체에 대한 내용을 알수가 없다.

    //   private final MemberRepository memberRepository = new MemoryMemberRepository();
    //   private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //   private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //   private DiscountPolicy discountPolicy; //인터페이스에만 의존하도록 변경 (DIP는 지켰다.)

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order CreateOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        //최종 생성된 주문
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

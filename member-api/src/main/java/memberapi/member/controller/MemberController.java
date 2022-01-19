//package memberapi.member.controller;
//
//import memberapi.member.model.entity.Admin;
//import memberapi.member.model.entity.Member;
//import memberapi.member.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author agj017@gmail.com
// * @since 2021/10/19
// */
//@RequiredArgsConstructor
//abstract class MemberController<T extends Member> {
//    private final MemberService<T> memberService;
//
//    @GetMapping("/{id}")
//    public T read(@PathVariable long id) {
//
//        T member = memberService.get(id);
//
//        return member;
//    }
//
//    @PostMapping
//    public Long create(@RequestBody T member) {
//
//        Long memberId = memberService.save(member);
//
//        return memberId;
//    }
//
//    @PutMapping("/{id}")
//    public void update(@PathVariable long id) {
//
//        System.out.println("update");
//    }
//
//    @DeleteMapping()
//    public void delete(@PathVariable long id) {
//
//        System.out.println("delete");
//    }
//}

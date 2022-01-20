package memberapi.member.service;

import memberapi.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author agj017@gmail.com
 * @since 2021/10/19
 */
public abstract class AbstractMemberService<T extends Member> {

    protected JpaRepository<T, Long> repo;

    public AbstractMemberService(JpaRepository<T, Long> repo) {
        this.repo = repo;
    }

//    T get(Long id){
//        T member = repo.getById(id);
//        return member;
//    };

    Long save(T member){
        T savedMember = repo.save(member);

        return savedMember.getId();

    };


    Long update(T updatedMember){

        Long memberId = updatedMember.getId();
        Optional<T> byId = repo.findById(memberId);
        T oldMember = byId.get();
        //putMemberInfo(member);

        return memberId;
    };

    void delete(Long id){
    };

    abstract protected Long putMemberInfo(T member);

}

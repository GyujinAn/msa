package memberapi.member.service;


/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
public interface MemberService <T> {

    T get(Long id);

    Long save(T admin);

    Long update(T admin);

    void delete(Long id);
}

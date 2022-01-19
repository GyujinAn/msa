package memberapi.member.service.conc;

import memberapi.member.model.entity.Provider;
import memberapi.member.service.AbstractMemberService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author agj017@gmail.com
 * @since 2021/10/21
 */
public class ProviderConcreateService extends AbstractMemberService<Provider> {
    public ProviderConcreateService(JpaRepository<Provider, Long> repo) {
        super(repo);
    }


    @Override
    protected Long putMemberInfo(Provider member) {
        return null;
    }
}

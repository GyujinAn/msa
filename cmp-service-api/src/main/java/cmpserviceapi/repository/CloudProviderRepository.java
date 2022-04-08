//package cloudserviceapi.repository;
//
//import cloudserviceapi.domain.CloudProvider;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
///**
// * @author agj017@gmail.com
// * @since 2022/03/10
// */
//@Repository
//public class CloudProviderRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    public Long save(CloudProvider cloudProvider){
//        em.persist(cloudProvider);
//        return cloudProvider.getId();
//
//    }
//
//    public CloudProvider find(Long id){
//        return em.find(CloudProvider.class, id);
//
//    }
//}

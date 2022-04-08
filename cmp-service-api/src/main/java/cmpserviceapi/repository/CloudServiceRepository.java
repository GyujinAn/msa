//package cloudserviceapi.repository;
//
//import cloudserviceapi.domain.CloudProvider;
//import cloudserviceapi.domain.CloudService;
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
//public class CloudServiceRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//
//    public Long save(CloudService cloudService){
//        em.persist(cloudService);
//        return cloudService.getId();
//
//    }
//
//    public CloudService find(Long id){
//        return em.find(CloudService.class, id);
//
//    }
//}

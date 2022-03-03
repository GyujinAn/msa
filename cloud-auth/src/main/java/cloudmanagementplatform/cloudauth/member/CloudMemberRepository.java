//package cloudmanagementplatform.cloudauth.member;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// * @author agj017@gmail.com
// * @since 2022/02/08
// */
//public interface CloudMemberRepository extends JpaRepository<CloudMember, Long> {
//
//    @Query(value = "SELECT m from CloudMember m where m.username= :username", nativeQuery = false)
//    CloudMember findByUsername(@Param("username") String username);
//
//
//}

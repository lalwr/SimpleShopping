package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//@Respository를 붙이지 않아도 Respository가 자동으로 등록 된다
public interface UserRepository extends JpaQueryDslPredicateRepository<User, Long> {

    public User findUserByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.use = 'Y' AND u.email = :email ")
    public User findUserByEmailAndUse(@Param("email")String email);
    public Long countByEmail(String email);
    @Query("SELECT uc.user FROM UserConnection uc WHERE uc.providerId = :type and uc.providerUserId = :providerUserId")
    public User getSocialUser(@Param("type") String type, @Param("providerUserId") String providerUserId);

}
// spring data method query

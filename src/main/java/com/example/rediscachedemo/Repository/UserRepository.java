package com.example.rediscachedemo.Repository;

import com.example.rediscachedemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}

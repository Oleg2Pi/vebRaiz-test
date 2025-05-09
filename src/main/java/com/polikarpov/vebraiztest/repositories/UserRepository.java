package com.polikarpov.vebraiztest.repositories;

import com.polikarpov.vebraiztest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

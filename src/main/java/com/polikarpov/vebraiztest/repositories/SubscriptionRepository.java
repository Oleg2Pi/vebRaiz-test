package com.polikarpov.vebraiztest.repositories;

import com.polikarpov.vebraiztest.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    @Query("""
            select s
            from Subscription s
            left join s.users u
            group by s.id
            order by count(u) desc limit 3
            """)
    List<Subscription> findTopThreeSubscriptions();
}

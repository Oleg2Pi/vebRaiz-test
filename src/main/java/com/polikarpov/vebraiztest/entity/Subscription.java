package com.polikarpov.vebraiztest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subscriptions")
@Data
@ToString(exclude = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String name;

    @Builder.Default
    @ManyToMany(mappedBy = "subscriptions")
    private List<User> users = new ArrayList<>();

}

package com.luck.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luck.todosimple.models.Dealer;

public interface DealerRepository extends JpaRepository<Dealer, Long> {

}

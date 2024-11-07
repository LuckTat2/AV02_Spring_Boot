package com.luck.todosimple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luck.todosimple.models.Dealer;
import com.luck.todosimple.repositories.DealerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DealerService {

    private final DealerRepository dealerRepository;

    @Autowired
    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public List<Dealer> findAll() {
        return dealerRepository.findAll();
    }

    public Optional<Dealer> findById(Long id) {
        return dealerRepository.findById(id);
    }

    @Transactional
    public Dealer create(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    @Transactional
    public Dealer update(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    @Transactional
    public void delete(Long id) {
        dealerRepository.deleteById(id);
    }
}

package com.example.drools.RuleEngine.Repository;

import com.example.drools.RuleEngine.Model.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offers, Long> {

    @Override
    List<Offers> findAll();

    Optional<Offers> findById(Long Id);
}

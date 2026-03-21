package com.quynh.fxservice.repo;

import com.quynh.fxservice.domain.FxRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FxRateRepository extends JpaRepository<FxRate, Long> {

    List<FxRate> findTop50ByBaseCurrencyOrderByFetchedAtDesc(String baseCurrency);
}
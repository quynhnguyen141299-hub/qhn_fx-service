package com.quynh.fxservice.repo;

import com.quynh.fxservice.domain.ConversionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversionRequestRepository extends JpaRepository<ConversionRequest, Long> {

    List<ConversionRequest> findTop50ByOrderByRequestedAtDesc();
}
package com.example.barbershop.cryptoproject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CryptoProjectService {
    private final CryptoProjectRepository cryptoProjectRepository;

    public CryptoProject create(CryptoProject cryptoProject) {
        log.info("Saving new crypto project: " + cryptoProject.getName());
        return cryptoProjectRepository.save(cryptoProject);
    }



    public Collection<CryptoProject> list() {
        return cryptoProjectRepository.findAll();
    }
}

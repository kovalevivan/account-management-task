package com.example.accountmanagementtask.repository;

import com.example.accountmanagementtask.entity.AccountDetailsEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountDetailsRepository extends CrudRepository<AccountDetailsEntity, UUID> {

    @Lock(LockModeType.OPTIMISTIC)
    @Override
    <S extends AccountDetailsEntity> S save(S entity);
}

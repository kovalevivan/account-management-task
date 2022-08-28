package com.example.accountmanagementtask.repository;

import com.example.accountmanagementtask.entity.MoneyTransferEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MoneyTransferRepository extends CrudRepository<MoneyTransferEntity, UUID> {
}

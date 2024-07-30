package com.fiap.lanchonete.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.lanchonete.infrastructure.persistence.entity.LgpdEntity;

public interface LgpdRepository  extends JpaRepository<LgpdEntity, Integer>{
	
}

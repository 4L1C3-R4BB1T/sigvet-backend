package br.com.sigvet.sigvetapi.feature.state;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sigvet.sigvetapi.common.entities.StateEntity;

public interface StateRepository extends JpaRepository<StateEntity, String> {}

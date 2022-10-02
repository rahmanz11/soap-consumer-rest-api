package org.union.trans.ubica.plus.soap.client.api;


import org.springframework.data.jpa.repository.JpaRepository;
import org.union.trans.ubica.plus.soap.client.api.model.UbicaDetail;

public interface UbicaRepository extends JpaRepository<UbicaDetail, Long> {
}

package zede.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zede.model.PruFactura;

@Repository
public interface PruFacturaRepo extends JpaRepository<PruFactura, Integer>{

}

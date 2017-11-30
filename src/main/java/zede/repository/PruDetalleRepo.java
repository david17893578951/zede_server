package zede.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import zede.model.PruDetalle;


@Repository
public interface PruDetalleRepo extends JpaRepository<PruDetalle, Integer>{
	
	@Modifying
	@Query("SELECT p FROM PruDetalle p WHERE p.pruFactura.pruFacCodigo=:nroId")
	public List<PruDetalle> getDetallesbyFactura(@Param("nroId") int nroId);

	@Modifying
	@Query("SELECT p FROM PruDetalle p WHERE p.pruProducto.pruProNombre=?")
	public List<PruDetalle> getDetallesbyproductos(@Param("nombrepruu") String nombrepruu);

}

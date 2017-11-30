package zede.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the pru_producto database table.
 * 
 */
@Entity
@Table(name="pru_producto")
@NamedQuery(name="PruProducto.findAll", query="SELECT p FROM PruProducto p")
public class PruProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize=1,name="PRU_PRODUCTO_PRUPROCODIGO_GENERATOR", sequenceName="PRU_PRODUCTO_PRU_PRO_CODIGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRU_PRODUCTO_PRUPROCODIGO_GENERATOR")
	@Column(name="pru_pro_codigo")
	private Integer pruProCodigo;

	@Column(name="pru_pro_costo")
	private BigDecimal pruProCosto;

	@Column(name="pru_pro_nombre")
	private String pruProNombre;

	//bi-directional many-to-one association to PruDetalle
	@OneToMany(mappedBy="pruProducto")
	@JsonIgnoreProperties(value={"pruProducto"}, allowGetters=true )
	private List<PruDetalle> pruDetalles;

	public PruProducto() {
	}

	public Integer getPruProCodigo() {
		return this.pruProCodigo;
	}

	public void setPruProCodigo(Integer pruProCodigo) {
		this.pruProCodigo = pruProCodigo;
	}

	public BigDecimal getPruProCosto() {
		return this.pruProCosto;
	}

	public void setPruProCosto(BigDecimal pruProCosto) {
		this.pruProCosto = pruProCosto;
	}

	public String getPruProNombre() {
		return this.pruProNombre;
	}

	public void setPruProNombre(String pruProNombre) {
		this.pruProNombre = pruProNombre;
	}

	public List<PruDetalle> getPruDetalles() {
		return this.pruDetalles;
	}

	public void setPruDetalles(List<PruDetalle> pruDetalles) {
		this.pruDetalles = pruDetalles;
	}

	public PruDetalle addPruDetalle(PruDetalle pruDetalle) {
		getPruDetalles().add(pruDetalle);
		pruDetalle.setPruProducto(this);

		return pruDetalle;
	}

	public PruDetalle removePruDetalle(PruDetalle pruDetalle) {
		getPruDetalles().remove(pruDetalle);
		pruDetalle.setPruProducto(null);

		return pruDetalle;
	}

}
package zede.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;


/**
 * The persistent class for the pru_detalle database table.
 * 
 */
@Entity
@Table(name="pru_detalle")
@NamedQuery(name="PruDetalle.findAll", query="SELECT p FROM PruDetalle p")
public class PruDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize=1, name="PRU_DETALLE_PRUDETACODIGO_GENERATOR", sequenceName="PRU_DETALLE_PRU_DETA_CODIGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRU_DETALLE_PRUDETACODIGO_GENERATOR")
	@Column(name="pru_deta_codigo")
	private Integer pruDetaCodigo;

	@Column(name="pru_deta_tota")
	private BigDecimal pruDetaTota;

	//bi-directional many-to-one association to PruFactura
	@ManyToOne
	@JoinColumn(name="pru_fac_codigo")
	@JsonIgnoreProperties(value={"pruDetalles"})
	private PruFactura pruFactura;

	//bi-directional many-to-one association to PruProducto
	@ManyToOne
	@JoinColumn(name="pru_pro_codigo")
	@JsonIgnoreProperties(value={"pruDetalles"})
	private PruProducto pruProducto;

	public PruDetalle() {
	}

	public Integer getPruDetaCodigo() {
		return this.pruDetaCodigo;
	}

	public void setPruDetaCodigo(Integer pruDetaCodigo) {
		this.pruDetaCodigo = pruDetaCodigo;
	}

	public BigDecimal getPruDetaTota() {
		return this.pruDetaTota;
	}

	public void setPruDetaTota(BigDecimal pruDetaTota) {
		this.pruDetaTota = pruDetaTota;
	}

	public PruFactura getPruFactura() {
		return this.pruFactura;
	}

	public void setPruFactura(PruFactura pruFactura) {
		this.pruFactura = pruFactura;
	}

	public PruProducto getPruProducto() {
		return this.pruProducto;
	}

	public void setPruProducto(PruProducto pruProducto) {
		this.pruProducto = pruProducto;
	}

}
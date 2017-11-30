package zede.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pru_factura database table.
 * 
 */
@Entity
@Table(name="pru_factura")
@NamedQuery(name="PruFactura.findAll", query="SELECT p FROM PruFactura p")
public class PruFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize=1,name="PRU_FACTURA_PRUFACCODIGO_GENERATOR", sequenceName="PRU_FACTURA_PRU_FAC_CODIGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRU_FACTURA_PRUFACCODIGO_GENERATOR")
	@Column(name="pru_fac_codigo")
	private Integer pruFacCodigo;

	@Column(name="pru_fac_cliente")
	private String pruFacCliente;

	@Temporal(TemporalType.DATE)
	@Column(name="pru_fac_fecha")
	private Date pruFacFecha;

	@Column(name="pru_fac_tota")
	private BigDecimal pruFacTota;

	//bi-directional many-to-one association to PruDetalle
	@OneToMany(mappedBy="pruFactura")
	@JsonIgnoreProperties(value={"pruFactura"}, allowGetters=true )
	private List<PruDetalle> pruDetalles;

	public PruFactura() {
	}

	public Integer getPruFacCodigo() {
		return this.pruFacCodigo;
	}

	public void setPruFacCodigo(Integer pruFacCodigo) {
		this.pruFacCodigo = pruFacCodigo;
	}

	public String getPruFacCliente() {
		return this.pruFacCliente;
	}

	public void setPruFacCliente(String pruFacCliente) {
		this.pruFacCliente = pruFacCliente;
	}

	public Date getPruFacFecha() {
		return this.pruFacFecha;
	}

	public void setPruFacFecha(Date pruFacFecha) {
		this.pruFacFecha = pruFacFecha;
	}

	public BigDecimal getPruFacTota() {
		return this.pruFacTota;
	}

	public void setPruFacTota(BigDecimal pruFacTota) {
		this.pruFacTota = pruFacTota;
	}

	public List<PruDetalle> getPruDetalles() {
		return this.pruDetalles;
	}

	public void setPruDetalles(List<PruDetalle> pruDetalles) {
		this.pruDetalles = pruDetalles;
	}

	public PruDetalle addPruDetalle(PruDetalle pruDetalle) {
		getPruDetalles().add(pruDetalle);
		pruDetalle.setPruFactura(this);

		return pruDetalle;
	}

	public PruDetalle removePruDetalle(PruDetalle pruDetalle) {
		getPruDetalles().remove(pruDetalle);
		pruDetalle.setPruFactura(null);

		return pruDetalle;
	}

}
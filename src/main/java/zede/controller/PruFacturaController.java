package zede.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zede.model.PruFactura;
import zede.repository.PruFacturaRepo;

@RestController
@RequestMapping("/factura")
@CrossOrigin
public class PruFacturaController {
	@Autowired
	private PruFacturaRepo repo;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<PruFactura>gerAllFacturas(){
		return this.repo.findAll();
	}
	@RequestMapping(method=RequestMethod.GET,value = "buscar/{id}")
	public PruFactura findFactura(@PathVariable("id") Integer id){
		PruFactura p=this.repo.findOne(id);
		return p;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value ="{id}" )
	public PruFactura eliminarFactura(@PathVariable("id")Integer id){
		PruFactura p=this.repo.findOne(id);
		this.repo.delete(id);
		return p;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/add")
	public PruFactura insertFactura(@RequestBody @Valid PruFactura factura,Errors errors){
		if(errors.hasErrors()){
			System.out.println("La Factura es invalidad");
			factura.setPruFacCliente("El Factura es invalido");
			return factura;
		}
		return this.repo.saveAndFlush(factura);
	}

	@RequestMapping(method=RequestMethod.PUT)
	public PruFactura editarFactura(@RequestBody @Valid PruFactura factura, Errors errors){
		PruFactura fact= null;
		if(factura.getPruFacCodigo() == null){
			System.out.println("El factura es invalido");
			factura.setPruFacCliente("El codigo es invalido");;
			return factura;
		}
		if(errors.hasErrors()){
			System.out.println("El factura es invalido");
			factura.setPruFacCodigo(1);
			return factura;
		}
		fact = this.repo.findOne(factura.getPruFacCodigo());
		fact.setPruFacTota(factura.getPruFacTota());
		return this.repo.saveAndFlush(fact);
	}

}

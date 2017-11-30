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

import zede.model.PruDetalle;
import zede.repository.PruDetalleRepo;
@RestController
@RequestMapping("/detalle")
@CrossOrigin
public class PruDetalleController {
	@Autowired
	private PruDetalleRepo repo;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<PruDetalle>getAllDetalle(){
		return this.repo.findAll();
	}
	@RequestMapping(method=RequestMethod.GET,value = "/buscar/{id}")
	public PruDetalle findDetalle(@PathVariable("id") Integer id){
		PruDetalle p=this.repo.findOne(id);
		return p;
	}
	
	@RequestMapping(method=RequestMethod.GET,value = "/lista/{id}")
	public List<PruDetalle> getDetallesbyFactura(@PathVariable("id") Integer id){		
		return this.repo.getDetallesbyFactura(id);
	}
	
	@RequestMapping(method=RequestMethod.GET,value = "/pro/{nombrepruu}")
	public List<PruDetalle> getDetallesbyproductos(@PathVariable("nombrepruu") String nombrepruu){		
		return this.repo.getDetallesbyproductos(nombrepruu);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value ="{id}" )
	public PruDetalle eliminarDetalle(@PathVariable("id")Integer id){
		PruDetalle p=this.repo.findOne(id);
		this.repo.delete(id);
		return p;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/add")
	public List<PruDetalle> insertdetalle(@RequestBody @Valid List<PruDetalle> detalle,Errors errors){
		if(errors.hasErrors()){
			System.out.println("La detalle es invalidad");
			//detalle.setPruFacDetalle("El detalle es invalido");
			return detalle;
		}
		return this.repo.save(detalle);
	}

	@RequestMapping(method=RequestMethod.PUT)
	public PruDetalle editardetalle(@RequestBody @Valid PruDetalle detalle, Errors errors){
		if(detalle.getPruDetaCodigo() == null){
			System.out.println("El producto es invalido");
			//detalle.setPruDetaCliente("El codigo es invalido");;
			return detalle;
		}
		if(errors.hasErrors()){
			System.out.println("El producto es invalido");
			//detalle.setPruDetaCodigo(1);
			return detalle;
		}
		return this.repo.saveAndFlush(detalle);
	}
}

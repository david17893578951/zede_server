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

import zede.model.PruProducto;
import zede.repository.PruProductoRepo;

@RestController
@RequestMapping("/producto")
@CrossOrigin
public class PruProductoController {
	
	@Autowired
	private PruProductoRepo repo;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<PruProducto> gerAllProductos(){
		return this.repo.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/buscar/{id}")
	public PruProducto findProducto(@PathVariable("id") Integer id){
		PruProducto p = this.repo.findOne(id);
		return p;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public PruProducto eliminarProducto(@PathVariable("id") Integer id){
		PruProducto p = this.repo.findOne(id);
		this.repo.delete(id);
		return p;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/add")
	public PruProducto insertProducto(@RequestBody @Valid PruProducto producto, Errors errors){
		System.out.println("Entro.");
		if(errors.hasErrors()){
			System.out.println("El producto es invalido");
			producto.setPruProNombre("El producto es invalido");
			return producto;
		}
		return this.repo.saveAndFlush(producto);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public PruProducto editarProducto(@RequestBody @Valid PruProducto producto, Errors errors){
		if(producto.getPruProCodigo() == null){
			System.out.println("El producto es invalido");
			producto.setPruProNombre("El codigo es invalido");
			return producto;
		}
		if(errors.hasErrors()){
			System.out.println("El producto es invalido");
			producto.setPruProNombre("El producto es invalido");
			return producto;
		}
		return this.repo.saveAndFlush(producto);
	}
}

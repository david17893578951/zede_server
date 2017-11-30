package zede.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/")
public class ZedOperadorUsuarioRESTController {

  @RequestMapping(method=RequestMethod.GET)
  public String getData()
  {
    return "Servidor funcionando XD";
  }
}

package es.safareyes.schoolevents.controllers;

import es.safareyes.schoolevents.dtos.CategoriaDTO;
import es.safareyes.schoolevents.dtos.LugarDTO;
import es.safareyes.schoolevents.services.CatalogoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CatalogoController {
    private CatalogoService service;

    @GetMapping("/categorias")
    public List<CategoriaDTO> consultarCategorias() { return service.listarCategorias();}

    @GetMapping("/lugares")
    public List<LugarDTO> consultarLugares() { return service.listarLugares(); }
}

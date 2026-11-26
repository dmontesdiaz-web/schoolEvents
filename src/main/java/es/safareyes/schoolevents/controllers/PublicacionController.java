package es.safareyes.schoolevents.controllers;

import es.safareyes.schoolevents.dtos.PublicacionCreateDTO;
import es.safareyes.schoolevents.dtos.PublicacionDTO;
import es.safareyes.schoolevents.services.PublicacionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/eventos/{id}/publicaciones")
@AllArgsConstructor
public class PublicacionController {

    private PublicacionService publicacionService;

    // Publicar resultados de un evento finalizado
    @PostMapping
    public PublicacionDTO publicar (@PathVariable Integer id, @RequestParam Integer idUsuario, @Valid @RequestBody PublicacionCreateDTO dto) {
        return publicacionService.publicar(id, dto, idUsuario);
    }

    @GetMapping
    public List<PublicacionDTO> consultarPublicaciones(@PathVariable Integer id) { return publicacionService.listar(id);}
}

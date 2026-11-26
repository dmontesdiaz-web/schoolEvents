package es.safareyes.schoolevents.excepciones;

// Se usa para cuando no existe un elemento relacionado que llega en el DTO (lugar, categoria, curso...) (404)
public class RelacionNoEncontrada extends RuntimeException {
    public RelacionNoEncontrada(String message) {
        super(message);
    }
}

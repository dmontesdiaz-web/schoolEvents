package es.safareyes.schoolevents.excepciones;

public class AccesoDenegado extends RuntimeException {
    public AccesoDenegado(String message) {
        super(message);
    }
}

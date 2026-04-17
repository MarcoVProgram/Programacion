/**
 * Enumeración que define los géneros de videojuegos disponibles en el catálogo.
 * 
 * <p>Este enum proporciona una lista cerrada de categorías de géneros de videojuegos
 * que pueden ser asignadas a objetos de tipo VideoJuego.</p>
 * 
 * @author Marco
 * @version 1.0
 * @since 1.0
 */
public enum GeneroVideoJuego {
    
    /** Género de videojuegos de acción. */
    ACCION,
    
    /** Género de videojuegos de aventura. */
    AVENTURA,
    
    /** Género de videojuegos de rol (Role-Playing Game). */
    RPG,
    
    /** Género de videojuegos de estrategia. */
    ESTRATEGIA,
    
    /** Género de videojuegos de simulación. */
    SIMULACION,
    
    /** Género de videojuegos deportivos. */
    DEPORTES,
    
    /** Género de videojuegos de mundo abierto (Sandbox). */
    SANDBOX,
    
    /** Categoría para géneros de videojuegos no clasificados en las opciones anteriores. */
    OTROS
}
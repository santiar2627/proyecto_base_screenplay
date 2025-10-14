package co.com.client.project.utils;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static class Messages {

        private Messages() {
        }

        public static final String MSG_FIN_PRUEBA = "Finaliza test";
        public static final String MSG_INICIA_PRUEBA = "Inicia test";
        public static final String MSG_EXEC_ACCION = "Validando resultado de la accion: {}";
        public static final String MSG_EXEC_ACCION_PAGINA = "Accediendo a la pagina: {}";
        public static final String MSG_FIN_ACCION_PAGINA = "Página cargada con exito";
    }
}

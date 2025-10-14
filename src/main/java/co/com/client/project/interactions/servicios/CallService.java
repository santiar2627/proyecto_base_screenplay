package co.com.client.project.interactions.servicios;

import net.serenitybdd.screenplay.Interaction;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CallService {

    private CallService() { }

    /** @param resource ruta del recurso a consumir */
    public static Interaction restGet(String resource) {
        return instrumented(Get.class, resource);
    }

    /** @param resource ruta del recurso a consumir
     *  @param header   Map con las cabeceras del recurso a consumir */
    public static Interaction restGet(String resource, Map<String, Object> header) {
        return instrumented(GetWithHeader.class, resource, header);
    }

    /** @param resource ruta del recurso a consumir */
    public static Interaction restGetVerification(String resource, Map<String, Object> headers, int expected) {
        return instrumented(GetWHStatusVerification.class, resource, headers, expected);
    }

    /** @param resource ruta del recurso a consumir
     *  @param header   Map con las cabeceras del recurso a consumir */
    public static Interaction restGetVerification(String resource, Map<String, Object> header) {
        return instrumented(GetWHStatusVerification.class, resource, header);
    }

    /** @param resource ruta del recurso a consumir
     *  @param body     String con el body del servicio a consumir */
    public static Interaction restPost(String resource, String body) {
        return instrumented(Post.class, resource, body);
    }

    /** @param resource ruta del recurso a consumir
     *  @param body     String con el body del servicio a consumir */
    public static Interaction restPostVerification(String resource, String body, Map<String, Object> headers, int expected) {
        return instrumented(PostWHStatusVerification.class, resource, body, headers, expected);
    }

    /** @param resource ruta del recurso a consumir
     *  @param body     String con el body del servicio a consumir
     *  @param header   Map con las cabeceras del recurso a consumir */
    public static Interaction restPost(String resource, String body, Map<String, Object> header) {
        return instrumented(PostWithHeader.class, resource, body, header);
    }

    /** @param resource    ruta del recurso a consumir
     *  @param bodyRequest String con el body del servicio a consumir
     *  @param headers     headers para la consulta. */
    public static Interaction soap(String resource, String bodyRequest, Map<String, String> headers) {
        return instrumented(Soap.class, resource, bodyRequest, headers);
    }

    /** @param resource ruta del recurso a consumir
     *  @param body     String con el body del servicio a consumir */
    public static Interaction restPut(String resource, String body) {
        return instrumented(Put.class, resource, body);
    }

    /** @param resource ruta del recurso a consumir */
    public static Interaction restDelete(String resource) {
        return instrumented(Delete.class, resource);
    }
}

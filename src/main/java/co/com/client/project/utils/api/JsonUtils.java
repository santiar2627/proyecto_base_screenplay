package co.com.client.project.utils.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.serenitybdd.rest.SerenityRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtils() {
        throw new UnsupportedOperationException("Utility class - no instantiation allowed");
    }

    /** Devuelve el body de la última respuesta como String (o null si no hay). */
    private static String lastBodyString() {
        var last = SerenityRest.lastResponse();
        return (last == null || last.getBody() == null) ? null : last.getBody().asString();
    }

    /** Devuelve el body de la última respuesta como JsonNode. */
    public static JsonNode lastResponseNode() {
        String body = lastBodyString();
        if (body == null) {
            return MAPPER.nullNode();
        }
        try {
            return MAPPER.readTree(body);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("No fue posible parsear la última respuesta a JsonNode", e);
            }
            return MAPPER.nullNode();
        }
    }

    /** Mapea el body de la última respuesta a la clase indicada. */
    public static <T> T lastResponseAs(Class<T> type) {
        String body = lastBodyString();
        if (body == null) return null;
        try {
            return MAPPER.readValue(body, type);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("No fue posible deserializar la última respuesta a {}", type.getSimpleName(), e);
            }
            return null;
        }
    }

    /** Mapea el body de la última respuesta usando TypeReference (para tipos genéricos). */
    public static <T> T lastResponseAs(TypeReference<T> ref) {
        String body = lastBodyString();
        if (body == null) return null;
        try {
            return MAPPER.readValue(body, ref);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("No fue posible deserializar la última respuesta (TypeReference)", e);
            }
            return null;
        }
    }

    /** Log “bonito” del JSON de la última respuesta (evalúa toPrettyString() sólo si INFO está habilitado). */
    public static void logLastResponsePretty(String sectionName) {
        if (!LOGGER.isInfoEnabled()) {
            return; // evita calcular el JsonNode/toPrettyString innecesariamente
        }
        JsonNode node = lastResponseNode();
        if (node == null || node.isMissingNode() || node.isNull()) {
            LOGGER.warn("[{}] La última respuesta está vacía o no existe.", sectionName);
            return;
        }
        // Evita concatenaciones; usa placeholders
        LOGGER.info("[{}] JSON del servicio:\n{}", sectionName, node.toPrettyString());
    }

    // Útiles extra
    public static JsonNode toNode(String json) {
        if (json == null) return MAPPER.nullNode();
        try {
            return MAPPER.readTree(json);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("No fue posible parsear el JSON a JsonNode", e);
            }
            return MAPPER.nullNode();
        }
    }

    public static <T> T fromJson(String json, Class<T> type) {
        if (json == null) return null;
        try {
            return MAPPER.readValue(json, type);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("No fue posible deserializar JSON a {}", type.getSimpleName(), e);
            }
            return null;
        }
    }

    public static String toJson(Object obj) {
        if (obj == null) return "{}";
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("No fue posible serializar objeto a JSON", e);
            }
            return "{}";
        }
    }
}
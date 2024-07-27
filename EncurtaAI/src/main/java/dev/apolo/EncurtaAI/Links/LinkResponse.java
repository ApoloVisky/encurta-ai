package dev.apolo.EncurtaAI.Links;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LinkResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("urlLong")
    private String urlLong;

    @JsonProperty("urlEncurtada")
    private String urlEncurtada;

    @JsonProperty("urlCriadaEm")
    private String urlCriadaEm;

    @JsonProperty("urlQrCode")
    private String urlQrCode;

    @JsonProperty("message")
    private String message;

    // Construtores, getters e setters
    public LinkResponse(Long id, String urlLong, String urlEncurtada, String urlCriadaEm, String urlQrCode, String message) {
        this.id = id;
        this.urlLong = urlLong;
        this.urlEncurtada = urlEncurtada;
        this.urlCriadaEm = urlCriadaEm;
        this.urlQrCode = urlQrCode;
        this.message = message;
    }

    public LinkResponse(Long id, String urlLong, String gerarUrlDeRedirecionamentoDoUsuario, LocalDateTime urlCriadaEm, String urlQrCode, Object message) {
    }

    // Getters e setters omitidos para brevidade
}

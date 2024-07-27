package dev.apolo.EncurtaAI.Links;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class LinkService {

    private LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    //Gera URL aleatoria
    //TODO: REFATORAR PARA INCLUIR  PARTE DA URL ORIGINAL NO ALGORITMO DE GERAR URLS.
    public String gerarUrlAleatoria(){

        return RandomStringUtils.randomAlphanumeric(5,10);
    }

    public Link encurtarUrl(String urlOriginal) {
        Link link = new Link();
        link.setUrlLong(urlOriginal);
        link.setUrlEncurtada(gerarUrlAleatoria());
        link.setUrlCriadaEm(LocalDateTime.now());
        link.setUrlQrCode("QR CODE NO MOMENTO INDISPONIVEL");
        return linkRepository.save(link);
    }

    public Link obterUrlOriginal(String urlEncurtada){

    try {
        return linkRepository.findByUrlOriginal(urlEncurtada);
    }catch (Exception erro){
        throw  new RuntimeException("Url nao existe em nossos registros");
    }
    }
}


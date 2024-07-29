package dev.apolo.EncurtaAI.Links;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link encurtarUrl(String urlOriginal) {
        // Gera um identificador único para a URL encurtada
        String urlEncurtada = UUID.randomUUID().toString().substring(0, 8);

        // Cria um objeto Link com os dados
        Link link = new Link();
        link.setUrlOriginal(urlOriginal);
        link.setUrlLong(urlOriginal);
        link.setUrlEncurtada(urlEncurtada);
        link.setUrlCriadaEm(LocalDateTime.now());

        // Salva no banco de dados
        linkRepository.save(link);

        return link;
    }

    public Link obterUrlOriginal(String urlEncurtada) {
        // Recupera o link original usando o identificador da URL encurtada
        Optional<Link> linkOptional = linkRepository.findByUrlEncurtada(urlEncurtada);
        return linkOptional.orElse(null);
    }
}

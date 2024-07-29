package dev.apolo.EncurtaAI.Links;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/encurta-ai")
public class LinkController {

    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<LinkResponse> gerarUrlEncurtada(@RequestBody Map<String, String> request) {
        System.out.println("Recebendo requisição para encurtar URL...");
        String urlOriginal = request.get("urlOriginal");

        if (urlOriginal == null || urlOriginal.isEmpty()) {
            LinkResponse errorResponse = new LinkResponse(
                    null, null, null, null, null, Optional.of("URL original não fornecida")
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        Link link = linkService.encurtarUrl(urlOriginal);
        String gerarUrlDeRedirecionamentoDoUsuario = "http://localhost:8080/r/" + link.getUrlEncurtada();

        LinkResponse response = new LinkResponse(
                link.getId(),
                link.getUrlLong(),
                gerarUrlDeRedirecionamentoDoUsuario,
                link.getUrlCriadaEm(),
                link.getUrlQrCode(),
                null
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/r/{urlEncurtada}")
    public void redirecionarLink(@PathVariable String urlEncurtada, HttpServletResponse response) throws IOException {
        System.out.println("Recebendo requisição para redirecionar URL...");
        Link link = linkService.obterUrlOriginal(urlEncurtada);

        if (link != null) {
            response.sendRedirect(link.getUrlLong());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

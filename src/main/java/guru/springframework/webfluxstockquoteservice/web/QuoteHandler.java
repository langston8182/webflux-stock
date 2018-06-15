package guru.springframework.webfluxstockquoteservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import guru.springframework.webfluxstockquoteservice.domain.Quote;
import guru.springframework.webfluxstockquoteservice.service.QuoteGeneratorService;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.time.Duration;

@Component
public class QuoteHandler {

	private final QuoteGeneratorService quoteGeneratorService;

	@Autowired
	public QuoteHandler(QuoteGeneratorService quoteGeneratorService) {
		super();
		this.quoteGeneratorService = quoteGeneratorService;
	}
	
	public Mono<ServerResponse> fetchQuotes(ServerRequest request) {
		int size = Integer.parseInt(request.queryParam("size").orElse("10"));
		
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100))
						.take(size), Quote.class);
	}
	
	public Mono<ServerResponse> streamQuotes(ServerRequest request) {
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(200)), Quote.class);
	}
}

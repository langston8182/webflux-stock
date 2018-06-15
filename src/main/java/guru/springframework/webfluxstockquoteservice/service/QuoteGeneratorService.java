package guru.springframework.webfluxstockquoteservice.service;

import java.time.Duration;

import guru.springframework.webfluxstockquoteservice.domain.Quote;
import reactor.core.publisher.Flux;

public interface QuoteGeneratorService {

		Flux<Quote> fetchQuoteStream(Duration period);
	
}

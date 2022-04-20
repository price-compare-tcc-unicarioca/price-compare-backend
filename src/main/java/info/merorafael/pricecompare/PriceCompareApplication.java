package info.merorafael.pricecompare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class PriceCompareApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceCompareApplication.class, args);
	}

}

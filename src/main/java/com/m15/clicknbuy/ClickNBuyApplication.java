package com.m15.clicknbuy;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.m15.clicknbuy.entity.Product;
import com.m15.clicknbuy.repository.ProductRepository;

@SpringBootApplication
@EnableAsync
public class ClickNBuyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClickNBuyApplication.class, args);
	}

	@Bean
	CommandLineRunner seedPremiumSmartphones(ProductRepository productRepository) {
		return args -> {
			List<Product> premiumPhones = List.of(
					new Product(null, "iPhone 15 Pro Max", 159900.0, 15,
							"6.7-inch Super Retina XDR display, A17 Pro chip and advanced camera system.",
							"https://tse1.mm.bing.net/th/id/OIP.VCsEv3jNRlAGQt1qXF80BwHaHa?rs=1&pid=ImgDetMain&o=7&rm=3",
							"Smartphones", null),
					new Product(null, "iPhone 15", 79900.0, 20,
							"Dynamic Island, A16 Bionic chip and excellent all-day battery performance.",
							"https://i.gadgets360cdn.com/large/iphone_15_series_models_1694757260405.jpg",
							"Smartphones", null),
					new Product(null, "Samsung Galaxy S24 Ultra", 129999.0, 18,
							"Flagship Android phone with S Pen, AI features and 200MP pro-grade camera.",
							"https://m.media-amazon.com/images/I/81vxWpPpgNL.jpg",
							"Smartphones", null),
					new Product(null, "Google Pixel 8 Pro", 106999.0, 16,
							"Google Tensor G3, top-tier computational photography and clean Android experience.",
							"https://m.media-amazon.com/images/I/71XEjCc4yLL._AC_SL1500_.jpg",
							"Smartphones", null),
					new Product(null, "OnePlus 12", 64999.0, 22,
							"Snapdragon 8 Gen 3 performance, fast charging and smooth AMOLED display.",
							"https://www.mobiledokan.com/media/oneplus-12-flowy-emerald-official-image_2.webp",
							"Smartphones", null),
					new Product(null, "Xiaomi 14 Ultra", 99999.0, 12,
							"Leica-tuned camera setup, flagship chipset and premium design.",
							"https://cdn.mos.cms.futurecdn.net/HE2fXamFQW8qFZEhsbYFc5.jpg",
							"Smartphones", null));

			for (Product product : premiumPhones) {
				productRepository.findByName(product.getName()).ifPresentOrElse(existingProduct -> {
					existingProduct.setPrice(product.getPrice());
					existingProduct.setStock(product.getStock());
					existingProduct.setDescription(product.getDescription());
					existingProduct.setImageLink(product.getImageLink());
					existingProduct.setCategory(product.getCategory());
					productRepository.save(existingProduct);
				}, () -> productRepository.save(product));
			}
		};
	}

}
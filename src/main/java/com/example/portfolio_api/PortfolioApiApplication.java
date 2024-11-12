package com.example.portfolio_api;

import com.example.portfolio_api.model.*;
import com.example.portfolio_api.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PortfolioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(
			UserRepository userRepository,
			PortfolioRepository portfolioRepository,
			AssetRepository assetRepository,
			TransactionRepository transactionRepository,
			PasswordEncoder passwordEncoder  // Inject PasswordEncoder
	) {
		return args -> {
			// Initialize Faker
			Faker faker = new Faker();

			// Create admin user if not exists
			if (!userRepository.existsByEmail("admin@test.com")) {
				User adminUser = new User();
				adminUser.setName("Admin User");
				adminUser.setEmail("admin@test.com");
				adminUser.setPassword(passwordEncoder.encode("admin123"));
				adminUser.setAccountNumber("9999999999");
				adminUser.setDateOfBirth(new Date());
				adminUser.setPhoneNumber("555-9999");
				adminUser.setAddress("1 Admin Plaza");

				// Save admin user
				userRepository.save(adminUser);

				// Create a portfolio for the admin user
				Portfolio adminPortfolio = new Portfolio();
				adminPortfolio.setPortfolioName("Admin Portfolio");
				adminPortfolio.setCreationDate(new Date());
				adminPortfolio.setPortfolioType("Long-term");
				adminPortfolio.setUser(adminUser);
				portfolioRepository.save(adminPortfolio);

				// Create an asset for the admin portfolio
				Asset adminAsset = new Asset();
				adminAsset.setSymbol("GOOGL");
				adminAsset.setAssetType("Stock");
				adminAsset.setQuantity(100);
				adminAsset.setPurchasePrice(2800.00);
				adminAsset.setCurrentPrice(2900.00);
				adminAsset.setMarketValue(adminAsset.getCurrentPrice() * adminAsset.getQuantity());
				adminAsset.setPortfolio(adminPortfolio);
				assetRepository.save(adminAsset);

				// Create a transaction for the admin asset
				Transaction adminTransaction = new Transaction();
				adminTransaction.setTransactionType("Buy");
				adminTransaction.setTransactionDate(new Date());
				adminTransaction.setQuantity(100);
				adminTransaction.setPricePerUnit(2800.00);
				adminTransaction.setAsset(adminAsset);
				transactionRepository.save(adminTransaction);

				// Associate entities
				adminAsset.setTransactions(Arrays.asList(adminTransaction));
				assetRepository.save(adminAsset);

				adminPortfolio.setAssets(Arrays.asList(adminAsset));
				portfolioRepository.save(adminPortfolio);

				adminUser.setPortfolios(Arrays.asList(adminPortfolio));
				userRepository.save(adminUser);
			}

			// Create 100 users with portfolios, assets, and transactions
			for (int i = 0; i < 100; i++) {
				// Create a user
				User user = new User();
				user.setName(faker.name().fullName());
				user.setEmail(faker.internet().emailAddress());
				user.setAccountNumber(faker.number().digits(10));
				user.setDateOfBirth(faker.date().birthday());
				user.setPhoneNumber(faker.phoneNumber().phoneNumber());
				user.setAddress(faker.address().fullAddress());
				user.setPassword(passwordEncoder.encode("password123")); // Set and encode password

				// Save user
				userRepository.save(user);

				// Create a portfolio for the user
				Portfolio portfolio = new Portfolio();
				portfolio.setPortfolioName(faker.company().name());
				portfolio.setCreationDate(new Date());
				portfolio.setPortfolioType("Long-term");
				portfolio.setUser(user);

				// Save portfolio
				portfolioRepository.save(portfolio);

				// Create an asset for the portfolio
				Asset asset = new Asset();
				asset.setSymbol(faker.stock().nsdqSymbol());
				asset.setAssetType("Stock");
				asset.setQuantity(faker.number().numberBetween(10, 100));
				asset.setPurchasePrice(faker.number().randomDouble(2, 100, 500));
				asset.setCurrentPrice(faker.number().randomDouble(2, 100, 500));
				asset.setMarketValue(asset.getCurrentPrice() * asset.getQuantity());
				asset.setPortfolio(portfolio);

				// Save asset
				assetRepository.save(asset);

				// Create a transaction for the asset
				Transaction transaction = new Transaction();
				transaction.setTransactionType("Buy");
				transaction.setTransactionDate(faker.date().past(1000, TimeUnit.DAYS));
				transaction.setQuantity(asset.getQuantity());
				transaction.setPricePerUnit(asset.getPurchasePrice());
				transaction.setAsset(asset);

				// Save transaction
				transactionRepository.save(transaction);

				// Associate asset with transaction
				asset.setTransactions(Arrays.asList(transaction));
				assetRepository.save(asset);

				// Associate portfolio with asset
				portfolio.setAssets(Arrays.asList(asset));
				portfolioRepository.save(portfolio);

				// Associate user with portfolio
				user.setPortfolios(Arrays.asList(portfolio));
				userRepository.save(user);
			}
		};
	}
}

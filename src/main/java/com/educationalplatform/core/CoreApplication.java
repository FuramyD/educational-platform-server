package com.educationalplatform.core;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.educationalplatform.core.model.User;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CoreApplication {

	private final AtomicLong counter = new AtomicLong();

	private static final Log log = LogFactory.getLog(CoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);

		ConnectionString connectionString = new ConnectionString("mongodb+srv://educationPlatform:educationPlatform1234!@cluster0.zj6m5.mongodb.net/educationPlatform?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.serverApi(ServerApi.builder()
						.version(ServerApiVersion.V1)
						.build())
				.build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("test");

		MongoOperations mongoOps = new MongoTemplate(mongoClient, "database");
		mongoOps.insert(new User("Violet", "Ritz"));
		mongoOps.insert(new User("Thomas", "Riddle"));

		log.info(mongoOps.findOne(
			new Query(where("lastName").is("Riddle")),
			User.class
		));

		mongoOps.dropCollection("users");
	}

	@GetMapping("/hello")
	public User hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new User(name, "Ritz");
	}

	@GetMapping(value = "/users")
	public User getUserByLastName(@RequestParam(value = "lastName") String lastName) {
		return new User("", lastName);
	}

}
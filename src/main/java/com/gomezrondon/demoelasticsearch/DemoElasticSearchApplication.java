package com.gomezrondon.demoelasticsearch;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@SpringBootApplication
public class DemoElasticSearchApplication implements CommandLineRunner {


	private final TransportClient client;

	public DemoElasticSearchApplication(TransportClient client) {
		this.client = client;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoElasticSearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		//********* insert
		IndexResponse response = client.prepareIndex("employee", "id", "1")
				.setSource(jsonBuilder()
						.startObject()
						.field("name", "Javier")
						.field("salary", 1200)
						.field("teamName", "BackEnd Dev")
						.endObject()
				)
				.get();

		System.out.println(response.getResult().toString());

	}
}

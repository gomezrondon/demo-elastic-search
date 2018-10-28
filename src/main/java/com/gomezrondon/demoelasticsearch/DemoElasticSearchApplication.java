package com.gomezrondon.demoelasticsearch;

import com.gomezrondon.demoelasticsearch.entity.LogStat;
import com.gomezrondon.demoelasticsearch.repository.LogStatRepository;
import com.gomezrondon.demoelasticsearch.service.LoadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoElasticSearchApplication implements CommandLineRunner {

	@Resource
	LogStatRepository repository;

	private final LoadFileService loadFileService;

	@Autowired
	public DemoElasticSearchApplication(LoadFileService loadFileService) {
		this.loadFileService = loadFileService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoElasticSearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Truncate/Reload
		repository.deleteAll();
		loadProcess();

		//Search for 1 record
		Optional<LogStat> logStat = repository.findById("22883");
		logStat.ifPresent(x -> {
			System.out.println(">>>>>>>>>>>>>>>> record found by ID: "+x);
		});
		LogStat statList = repository.findByTarFileName("20181027060415.tar");
		System.out.println(">>>>>>>>>>>>>>>> record found by TarFile: "+statList);

		//Count all records
		long count = repository.count();
		System.out.println(">>>>>>>>> Total of Records: "+count);

	}

	private void loadProcess() throws IOException {
		List<String> strings = loadFileService.readFile("C:\\temp\\data-load-stats.txt");
		strings.forEach(line -> {
			LogStat logStat = LogStat.loadByString(line.trim(), "~");
			repository.save(logStat);
		});
		System.out.println(">>>>>>>>>>>>>>>> record indexed");
	}
}

package com.gomezrondon.demoelasticsearch.repository;

import com.gomezrondon.demoelasticsearch.entity.LogStat;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogStatRepository extends ElasticsearchRepository<LogStat,String> {

    LogStat findByTarFileName(String tarFileName);

}

package com.gomezrondon.demoelasticsearch.service;


import java.io.IOException;
import java.util.List;

public interface LoadFileService {

    List<String> readFile(String file) throws IOException;
}

package com.gomezrondon.demoelasticsearch.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.BaseStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LoadFileServiceImp implements LoadFileService {



    private static Flux<String> fromPath(Path path) {
        return Flux.using(() -> Files.lines(path),
                Flux::fromStream,
                BaseStream::close
        );
    }


    private static List<Path> getPathOfFilesInFolder(String folder) throws IOException {

        try (Stream<Path> paths = Files.walk(Paths.get(folder))) {
            return paths
                    .filter(Files::isRegularFile)
                    //.peek(System.out::println)
                    .collect(Collectors.toList());
        }
    }


     public List<String> readFile(String file) throws IOException {

          if(file != null){
              Path path = getPathOfFilesInFolder(file).get(0);
              System.out.println(path.toFile().getName());
              List<String> list =  fromPath(path).collect(Collectors.toList()).block();
              return list;
         }

         return new ArrayList<>();
     }

}
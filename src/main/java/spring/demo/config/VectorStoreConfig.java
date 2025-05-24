package spring.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@Slf4j
public class VectorStoreConfig {
    @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel, VectorStoreProperties properties) {
        var store = SimpleVectorStore.builder(embeddingModel).build();
        var vsf = new File(properties.getVectorStorePath());

        if (vsf.exists()) {
            log.info("Using existing vector store");
            store.load(vsf);
        } else {
            log.info("Creating a new vector store");

            properties.getDocumentsToLoad().forEach(document -> {
                log.info("Loading document {}", document.getFilename());

                var documentReader = new TikaDocumentReader(document);
                var docs = documentReader.get();
                var textSplitter = new TokenTextSplitter();
                var splitDocs = textSplitter.apply(docs);

                store.add(splitDocs);
            });

            store.save(vsf);
        }

        return store;
    }
}

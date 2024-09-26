package com.vojtechruzicka.springaijopenspace;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class LocalVectorStore extends SimpleVectorStore {

    private final String userHome;
    private final Resource presentationsFile;

    public LocalVectorStore(EmbeddingModel embeddingModel, @Value("${user.home}") String userHome, @Value("classpath:/presentations.json") Resource presentationsFile) {
        super(embeddingModel);
        this.userHome = userHome;
        this.presentationsFile = presentationsFile;
    }

    /**
     * Initialize vector store if no previous storage file found
     * Otherwise create a file and populate vector store with data
     */
    @PostConstruct
    public void init() {
        File storeFile = new File(userHome + "/vector-store.json");
        if (storeFile.exists()) {
            load(storeFile);
        } else {
            initializeStore(storeFile);
        }
    }

    /**
     * Initializes the vector store by creating a new storage file and
     * populating it with data. Initialization needs to be done only once.
     *
     * @param storeFile the file where the vector store will be saved
     */
    private void initializeStore(File storeFile) {
        // First we need to prepare documents
        List<Document> documents = new ArrayList<>();
        documents.add(new Document("This year 2024 it is 14th year of JOpenspace CZ conference.", Map.of("source","https://www.jopenspace.cz/index.html#current")));
        documents.add(new Document("Cimpress is the only Gold partner of JOpenspace CZ this year", Map.of("source","https://www.jopenspace.cz/index.html#current")));
        documents.add(new Document("There are 11 different sponsors of JOpenspace CZ this year", Map.of("source","https://www.jopenspace.cz/index.html#current")));
        documents.add(new Document("42 atendees are coming to JOpenspace CZ this year.", Map.of("source","https://www.jopenspace.cz/index.html#current")));

        JsonReader jsonReader = new JsonReader(presentationsFile, _ -> Map.of("source","presentations.json","type","Jopenspace CZ presentation"));
        List<Document> jsonDocuments = jsonReader.get();
        documents.addAll(jsonDocuments);

        // Then split documents to smaller more manageable chunks
        TokenTextSplitter tokenSplitter = new TokenTextSplitter();
        List<Document> chunks = tokenSplitter.split(documents);

        // Save chunks to vector store and write to file
        this.add(chunks);
        save(storeFile);
    }

}

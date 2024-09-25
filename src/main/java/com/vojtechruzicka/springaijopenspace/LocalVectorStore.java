package com.vojtechruzicka.springaijopenspace;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LocalVectorStore extends SimpleVectorStore {

    @Value("${user.home}")
    private String userHome;

    public LocalVectorStore(EmbeddingModel embeddingModel) {
        super(embeddingModel);
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
        documents.add(new Document("Bananas are my favorite food"));
        documents.add(new Document("I hate strawberries"));

        // Then split documents to smaller more manageable chunks
        TokenTextSplitter tokenSplitter = new TokenTextSplitter();
        List<Document> chunks = tokenSplitter.split(documents);

        // Save chunks to vector store and write to file
        this.add(chunks);
        save(storeFile);
    }

}

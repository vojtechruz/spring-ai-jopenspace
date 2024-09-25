package com.vojtechruzicka.springaijopenspace;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.stereotype.Repository;

@Repository
public class VectorStore extends SimpleVectorStore {

    public VectorStore(EmbeddingModel embeddingModel) {
        super(embeddingModel);
    }

    @PostConstruct
    public void init() {

    }

}

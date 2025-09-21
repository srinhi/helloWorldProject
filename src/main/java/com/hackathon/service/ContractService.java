package com.hackathon.contractsimplifier.service;

import com.hackathon.contractsimplifier.util.FileParser;
import com.hackathon.contractsimplifier.util.OpenAIClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ContractService {

    private final FileParser fileParser;
    private final OpenAIClient openAIClient;

    public ContractService(FileParser fileParser, OpenAIClient openAIClient) {
        this.fileParser = fileParser;
        this.openAIClient = openAIClient;
    }

    public String simplifyContract(MultipartFile file) throws Exception {
        String text = fileParser.extractText(file);
        return simplifyContract(text);
    }

    public String simplifyContract(String contractText) throws Exception {
        String prompt = """
                You are ContractSimplifierGPT — summarize contracts into JSON.
                CONTRACT TEXT:
                """ + contractText;

        return openAIClient.callOpenAI(prompt);
    }
}

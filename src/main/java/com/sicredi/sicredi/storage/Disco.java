package com.sicredi.sicredi.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sicredi.sicredi.services.ProcessamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Disco {

    @Value("${disco.raiz}")
    private String raiz;

    @Value("aprocessar")
    private String diretorioAprocessar;

    @Value("processado")
    private String diretorioProcessado;


    @Autowired
    private ProcessamentoService processamentoService;

    public void salvarAprocessar(MultipartFile aprocessar) {
        this.salvar(this.diretorioAprocessar, aprocessar);
    }

    public void salvar(String diretorio, MultipartFile arquivo) {
        Path diretorioPath = Paths.get(this.raiz, diretorio);
        Path diretorioProc = Paths.get(this.raiz, diretorioProcessado);
        Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());

        try {
            Files.createDirectories(diretorioProc);
            Files.createDirectories(diretorioPath);
            arquivo.transferTo(arquivoPath.toFile());
            processamentoService.processarCsv(arquivoPath.toString());
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }
    }
}
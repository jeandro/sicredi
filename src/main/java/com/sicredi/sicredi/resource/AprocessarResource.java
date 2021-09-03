package com.sicredi.sicredi.resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sicredi.sicredi.storage.Disco;

@RestController
@RequestMapping("/aprocessar")
public class AprocessarResource {

    @Autowired
    private Disco disco;

    @PostMapping
    public void upload(@RequestParam MultipartFile arquivo) {
        disco.salvarAprocessar(arquivo);
    }

}

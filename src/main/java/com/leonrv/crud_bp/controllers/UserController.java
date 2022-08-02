package com.leonrv.crud_bp.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonrv.crud_bp.models.*;
import com.leonrv.crud_bp.repositories.*;
import com.leonrv.crud_bp.services.GenericService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.leonrv.crud_bp.repositories.*;
import com.leonrv.crud_bp.services.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private AzureBlobService azureBlobAdapter;

    GenericService<User, Long> service;

    public UserController(IGenericRepository<User, Long> repositoryUser) {
        this.service = new GenericService<User, Long>(repositoryUser)
        {
        };
    }

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<?>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    // public ResponseEntity<Page<T>> getPage(Pageable pageable){
    //     return ResponseEntity.ok(service.getPage(pageable));
    // }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestParam(required = false) MultipartFile file,
    @RequestParam String user
    )throws IOException{
        System.out.println("updating");
        ObjectMapper objectMapper = new ObjectMapper();
        User userJson = new User();
        // System.out.println(user);
        userJson = objectMapper.readValue(user, User.class);


        // System.out.println(userJson);

        if(file!=null){
            String fileUrl = azureBlobAdapter.upload(file);
            userJson.setUrlImage(fileUrl);
        }
        return ResponseEntity.ok(service.save(userJson));
        // return ResponseEntity.ok("OK");
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> create(@RequestParam(required = false) MultipartFile file,
    @RequestParam String user
    )throws IOException{
        System.out.println("saving");
        ObjectMapper objectMapper = new ObjectMapper();
        User userJson = new User();
        // System.out.println(user);
        userJson = objectMapper.readValue(user, User.class);

        userJson.setId(null);

        // System.out.println(userJson);

        if(file!=null){
            String fileUrl = azureBlobAdapter.upload(file);
            userJson.setUrlImage(fileUrl);
        }
        return ResponseEntity.ok(service.save(userJson));
        // return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok(id);
    }


}

package com.leonrv.crud_bp.controllers;

import org.springframework.web.bind.annotation.*;
import com.leonrv.crud_bp.models.*;
import com.leonrv.crud_bp.repositories.*;

@RestController @RequestMapping("/api/v1/model") @CrossOrigin("*")
public class ModelController extends GenericController<Model, Long> {
    public ModelController(IGenericRepository<Model, Long> repository) {
        super(repository);
    }
}

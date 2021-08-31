package mwt.testsysback.controller;

import mwt.testsysback.common.CommonResult;
import mwt.testsysback.entity.Collection;
import mwt.testsysback.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @RequestMapping(value = "/collection/get",method = RequestMethod.GET)
    public CommonResult getAllCollection(){
        List<Collection> collection = collectionService.getAllCollection();
        return CommonResult.success(collection);
    }
}

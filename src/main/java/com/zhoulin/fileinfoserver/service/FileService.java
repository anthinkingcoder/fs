package com.zhoulin.fileinfoserver.service;

import com.zhoulin.fileinfoserver.domain.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FileService {

    File saveOne(File file);

    void removeOne(String id);

    List<File> listAll();

    Page<File> findAllByNameContaining(String name,Pageable pageable);

    File findOne(String id);
}

package com.zhoulin.fileinfoserver.service.impl;

import com.zhoulin.fileinfoserver.domain.File;
import com.zhoulin.fileinfoserver.respository.FileRepository;
import com.zhoulin.fileinfoserver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public File saveOne(File file) {
        return fileRepository.save(file);
    }

    @Override
    public void removeOne(String id) {
        fileRepository.delete(id);
    }

    @Override
    public List<File> listAll() {
        return fileRepository.findAll();
    }

    @Override
    public Page<File> findAllByNameContaining(String name, Pageable pageable) {
        return fileRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public File findOne(String id) {
        return fileRepository.findOne(id);
    }
}

package com.zhoulin.fileinfoserver.respository;

import com.zhoulin.fileinfoserver.domain.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<File, String> {
    Page<File> findAllByNameContaining(String name, Pageable pageable);
}

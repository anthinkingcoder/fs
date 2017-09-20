package com.zhoulin.fileinfoserver.respository;

import com.zhoulin.fileinfoserver.domain.File;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FileRepositoryTest {
    @Autowired
    private FileRepository fileRepository;


    @Test
    public void testSave() {
        File file = new File("123.jpg",
                "123123123123","123123".getBytes(),"image/jpeg",12L);
        file = fileRepository.save(file);
        Assert.assertNotNull(file);
    }

    @Test
    public void testList() {
        List<File> list = fileRepository.findAll();
        Assert.assertNotEquals(list.size(),0);
        log.info(list.toString());
    }


    @Test
    public void testPageList() {
        Pageable pageable = new PageRequest(0,20);
        Page<File> page = fileRepository.findAll(pageable);
        Assert.assertNotEquals(page.getSize(),0);
        log.info(page.getContent().toString());
    }

    @Test
    public void testRemoveById() {
        fileRepository.delete("59c0eedfe63276a33c5054bf");
    }


}
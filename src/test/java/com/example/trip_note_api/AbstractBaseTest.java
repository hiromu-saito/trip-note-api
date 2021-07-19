package com.example.trip_note_api;


import org.apache.commons.io.FileUtils;
import org.junit.runner.RunWith;
import org.seasar.doma.jdbc.Config;
import org.apache.commons.lang3.StringUtils;
import org.seasar.doma.jdbc.builder.UpdateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public abstract class AbstractBaseTest {

    @Autowired
    Config config;

    public void dataSetupByFile(String target) throws Exception{
        URL url = getClass().getClassLoader().getResource(target);
        File file = new File(url.toURI());

        FileUtils.readLines(file,"UTF8")
                .stream()
                .map(line -> line.trim())
                .filter(line -> StringUtils.isNotBlank(line) && !StringUtils.startsWith(line,"--"))
                .forEach(line -> UpdateBuilder.newInstance(config).sql(line).execute());

    }
}

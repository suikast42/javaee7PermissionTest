package com.siemag.jpatest.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Arquillian.class)
public class ArqtTest  {


    @Deployment
    public static WebArchive createArchiveTest() {
        File archive = new File("target/jpatest.war");
        return ShrinkWrap.createFromZipFile(WebArchive.class, archive);
    }

    @Test
    public void test(){
        System.out.println("OK");
    }

}

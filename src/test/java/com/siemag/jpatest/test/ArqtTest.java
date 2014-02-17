package com.siemag.jpatest.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

@RunWith(Arquillian.class)
public class ArqtTest  {


    public class PLCSocketTestImpl extends UnicastRemoteObject {

        protected PLCSocketTestImpl() throws RemoteException {
        }


        protected PLCSocketTestImpl(int port) throws RemoteException {
            super(port);
        }

        protected PLCSocketTestImpl(int port,
                                    RMIClientSocketFactory csf,
                                    RMIServerSocketFactory ssf)
                throws RemoteException {
            super(port, csf, ssf);
        }
    }

    @Deployment
    public static WebArchive createArchiveTest() {
        File archive = new File("target/javaee7PermissionTest.war");
        return ShrinkWrap.createFromZipFile(WebArchive.class, archive);
    }

    @Test
    public void test() throws Exception {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        final String lookupAdress = "//localhost:10097/CRM1_SOCKETSND";
        Naming.rebind(lookupAdress, new PLCSocketTestImpl());
    }

}

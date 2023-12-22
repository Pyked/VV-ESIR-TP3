package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    @Test
    public void preparedSocket_NullProtocols() {
        TLSSocketFactory tlsSocketFactory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        tlsSocketFactory.prepareSocket(mockSocket);

        // Verify that no methods were called on the mock socket
        verifyZeroInteractions(mockSocket);
    }

    @Test
    public void typical() {
        TLSSocketFactory tlsSocketFactory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);
    
        // Stubbing the behavior of the mock socket
        when(mockSocket.getSupportedProtocols()).thenReturn(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});
    
        tlsSocketFactory.prepareSocket(mockSocket);
    
        // Verifying that setEnabledProtocols was called with the expected arguments
        verify(mockSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}

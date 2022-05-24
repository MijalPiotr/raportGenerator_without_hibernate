package pl.pm.raportgenerator;

import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class HelloControllerTest {
    @Mock
    private AnchorPane ap;
    @InjectMocks
    private HelloController helloController;

    @Test
    public void testTestOnly(){
        helloController.addFile();
    }
}
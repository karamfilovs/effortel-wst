import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import soap.service.CalculatorLocator;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;


public class SOAPCalculatorTest {
    private CalculatorLocator locator = new CalculatorLocator();

    @Test
    @Tag("soap")
    @DisplayName("Can add numbers")
    void canAddNumbers() throws ServiceException, RemoteException {
        int result = locator
                .getCalculatorSoap12()
                .add(2, 2);
        Assertions.assertEquals(4, result);
    }

    @Test
    @Tag("soap")
    @DisplayName("Can divide numbers")
    void canDivideNumbers() throws ServiceException, RemoteException {
        int result = locator.getCalculatorSoap12()
                .divide(2, 2);
        Assertions.assertEquals(1, result);
    }

    @Test
    @Tag("soap")
    @DisplayName("Can multiply numbers")
    void canMultiplyNumbers() throws ServiceException, RemoteException {
        int result = locator.getCalculatorSoap12()
                .multiply(3, 3);
        Assertions.assertEquals(9, result);
    }


    @Test
    @Tag("soap")
    @DisplayName("Can substract numbers")
    void canSubtractNumbers() throws ServiceException, RemoteException {
        int result = locator.getCalculatorSoap12()
                .subtract(3, 3);
        Assertions.assertEquals(0, result);
    }
}

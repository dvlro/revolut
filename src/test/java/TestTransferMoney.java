import io.nobel.myapp.model.Account;
import io.nobel.myapp.rest.AccountRequests;
import org.json.simple.JSONObject;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static junit.framework.TestCase.*;

/**
 * Created by petre on 10/31/2018.
 */
public class TestTransferMoney {

    @Test
    public void testTransferMethod(){
        AccountRequests req = new AccountRequests();
        req.addAccount("brad@yahoo.com", new BigDecimal("100"));
        req.addAccount("fyona@gmail.com", new BigDecimal("300"));

        req.transferMoney("brad@yahoo.com", new BigDecimal("50"), "fyona@gmail.com");

        Response accountBrad = req.getAccount("brad@yahoo.com");
        Response accountFyona = req.getAccount("fyona@gmail.com");
        Account aBrad = (Account) accountBrad.getEntity();
        Account aFyona = (Account) accountFyona.getEntity();

        assertTrue(aBrad.getBalance().equals(new BigDecimal("50")));
        assertFalse(aFyona.getBalance().equals(new BigDecimal("300")));
        assertTrue(aFyona.getBalance().equals(new BigDecimal("350")));
    }

    @Test
    public void testFailToTransferMoneyUserNotExists(){
        AccountRequests req = new AccountRequests();
        req.addAccount("brad@yahoo.com", new BigDecimal("100"));
        Response response = req.transferMoney("brad@yahoo.com", new BigDecimal("50"), "geo@gmail.com");
        JSONObject obj = new JSONObject();
        obj.put("message", "Account/s doesn't exists");
        assertEquals(response.getEntity(), obj);

    }

    @Test
    public void testFailToTransferMoneyNoFunds(){
        AccountRequests req = new AccountRequests();
        req.addAccount("brad@yahoo.com", new BigDecimal("100"));
        req.addAccount("jack@yahoo.com", new BigDecimal("1"));

        Response response = req.transferMoney("jack@yahoo.com", new BigDecimal("10"), "brad@yahoo.com");

        JSONObject obj = new JSONObject();
        obj.put("message", "Account [jack@yahoo.com] has not enough balance to transfer 10");
        assertEquals(response.getEntity(), obj);
    }
}

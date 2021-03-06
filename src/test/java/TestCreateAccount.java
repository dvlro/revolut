import io.nobel.myapp.model.Account;
import io.nobel.myapp.rest.AccountRequests;
import org.json.simple.JSONObject;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by petre on 11/1/2018.
 */
public class TestCreateAccount {

    @Test
    public void testAlreadyExist() {
        AccountRequests req = new AccountRequests();
        String email = "brad@yahoo.com";
        req.addAccount(email, new BigDecimal("100"));
        Response r= req.addAccount(email, new BigDecimal("100"));

        JSONObject obj = new JSONObject();
        obj.put("message", "Account already exists");
        assertEquals(r.getEntity(), obj);
    }

    @Test
    public void testSuccessfullyCreateAccount() {
        AccountRequests req = new AccountRequests();
        String email = "brad@yahoo.com";
        req.addAccount(email, new BigDecimal("100"));
        Response r = req.getAccount(email);
        Account accountCreated = (Account) r.getEntity();
        assertEquals(accountCreated.getEmail(), email);
    }

}

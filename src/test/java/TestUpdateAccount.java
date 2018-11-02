import io.nobel.myapp.model.Account;
import io.nobel.myapp.rest.AccountRequests;
import org.json.simple.JSONObject;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by petre on 11/2/2018.
 */
public class TestUpdateAccount {

    @Test
    public void testAccountDoesntExist() {
        AccountRequests req = new AccountRequests();
        String email = "brad@yahoo.com";
        Response r= req.updateAccount(email, new BigDecimal("100"));

        JSONObject obj = new JSONObject();
        obj.put("message", "Account doesn't exists");
        assertEquals(r.getEntity(), obj);
    }

    @Test
    public void testSuccessfullyUpdateAccount() {
        AccountRequests req = new AccountRequests();
        String email = "brad@yahoo.com";
        req.addAccount(email, new BigDecimal("100"));
        req.updateAccount(email, new BigDecimal("500"));
        Response r = req.getAccount(email);
        Account accountUpdated = (Account) r.getEntity();
        assertEquals(accountUpdated.getBalance(), new BigDecimal("500"));
    }
}

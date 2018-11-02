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
public class TestGetAccount {

    @Test
    public void testAccountDoesntExist() {
        AccountRequests req = new AccountRequests();
        String email = "brad@yahoo.com";
        Response r = req.getAccount(email);

        JSONObject obj = new JSONObject();
        obj.put("message", "Account ["+email+"] does not exist");
        assertEquals(r.getEntity(), obj);
    }

    @Test
    public void testAccountFound() {
        AccountRequests req = new AccountRequests();
        String email = "brad@yahoo.com";
        Response accountCreated = req.addAccount(email, new BigDecimal("1"));
        Response accountFound = req.getAccount(email);

        assertEquals(accountFound.getEntity(), accountCreated.getEntity());
    }
}

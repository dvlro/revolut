import io.nobel.myapp.model.Account;
import io.nobel.myapp.rest.AccountRequests;
import org.json.simple.JSONObject;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by petre on 11/2/2018.
 */
public class TestGetAllAccounts {

    @Test
    public void testMoreAccounts() {
        AccountRequests req = new AccountRequests();
        String email = "brad@yahoo.com";
        String email1 = "brad1@yahoo.com";
        String email2 = "brad2@yahoo.com";
        req.addAccount(email, new BigDecimal("1"));
        req.addAccount(email1, new BigDecimal("2"));
        req.addAccount(email2, new BigDecimal("3"));
        Response accountsFound = req.getAccounts();
        List<Account> accounts = (List<Account>) accountsFound.getEntity();
        assertTrue(accounts.size() > 0);
    }

    @Test
    public void testZeroAccounts(){
        AccountRequests req = new AccountRequests();
        Response response = req.getAccounts();
        JSONObject obj = new JSONObject();
        obj.put("message", "We have 0 accounts.");
        assertEquals(response.getEntity(), obj);

    }


}

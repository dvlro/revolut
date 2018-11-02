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
public class TestDeleteAccount {

    @Test
    public void testAccountDeleted() {
        AccountRequests req = new AccountRequests();
        String email = "brad@yahoo.com";
        req.addAccount(email, new BigDecimal("10"));
        Response r = req.deleteAccount(email);

        JSONObject obj = new JSONObject();
        obj.put("message", "Account ["+email+"] has been deleted");
        assertEquals(r.getEntity(), obj);
    }

    @Test
    public void testAccountDoesntExist() {
        AccountRequests req = new AccountRequests();
        String email = "brad@yahoo.com";
        Response r = req.deleteAccount(email);

        JSONObject obj = new JSONObject();
        obj.put("message", "Account ["+email+"] does not exist and can't be deleted");
        assertEquals(r.getEntity(), obj);
    }


}

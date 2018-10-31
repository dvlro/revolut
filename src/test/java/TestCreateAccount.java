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
        req.addAccount("brad@yahoo.com", new BigDecimal("100"));
        Response r= req.addAccount("brad@yahoo.com", new BigDecimal("100"));

        JSONObject obj = new JSONObject();
        obj.put("message", "Account already exists");
        assertEquals(r.getEntity(), obj);
    }

}

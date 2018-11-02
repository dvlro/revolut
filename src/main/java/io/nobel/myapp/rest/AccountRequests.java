package io.nobel.myapp.rest;

import io.nobel.myapp.LoadConfigurationListener;
import io.nobel.myapp.dao.AccountDAO;
import io.nobel.myapp.model.Account;
import io.nobel.myapp.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by petre on 10/31/2018.
 */
@Path("/account")
public class AccountRequests {


    private static final Logger LOG = LoggerFactory.getLogger(AccountRequests.class);

    private AccountService accountService = new AccountService();

    // URI:
    // /contextPath/servletPath/account
    @GET
    @Path("/all")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAccounts() {
        LOG.info("getAccounts");
        return accountService.listAccounts();
    }

    // URI:
    // /contextPath/servletPath/account/{email}
    @GET
    @Path("/{email}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAccount(@PathParam("email") String email) {
        LOG.info("getAccount [" + email + "]");
        return accountService.getAccount(email);
    }

    // URI:
    // /contextPath/servletPath/account
    @POST
    @Path("/add/{email}/{balance}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response addAccount(@PathParam("email") String email, @PathParam("balance") BigDecimal balance) {
        LOG.info("addAccount [" + email + "|" + balance+"]");
        return accountService.createAccount(email, balance);
    }

    // URI:
    // /contextPath/servletPath/account
    @PUT
    @Path("/update/{email}/{balance}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response updateAccount(@PathParam("email") String email, @PathParam("balance") BigDecimal balance) {
        LOG.info("updateAccount [" + email + "|" + balance+"]");
        return accountService.updateAccount(email, balance);
    }

    // URI:
    // /contextPath/servletPath/account
    @PUT
    @Path("/transfer/{from}/{balance}/{to}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response transferMoney(@PathParam("from") String from, @PathParam("balance") BigDecimal balance, @PathParam("to") String to) {
        LOG.info("transferMoney [" + from + "|" + balance+ "|" +to+"]");
        return accountService.transfer(from, balance, to);
    }

    @DELETE
    @Path("/delete/{email}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteAccount(@PathParam("email") String email) {
        LOG.info("deleteAccount");
        return accountService.deleteAccount(email);
    }

}

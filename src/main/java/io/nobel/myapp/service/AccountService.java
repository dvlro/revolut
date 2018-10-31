package io.nobel.myapp.service;

import io.nobel.myapp.dao.AccountDAO;
import io.nobel.myapp.model.Account;
import org.json.simple.JSONObject;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by petre on 10/31/2018.
 */
public class AccountService {

    public Response createAccount(String email, BigDecimal balance){
        Account account = AccountDAO.create(email, balance);
        if (account != null){
            return Response.status(Response.Status.OK).entity(account).build();}
        else {
            return Response.status(Response.Status.OK).entity(convertToJSON("Account already exists")).build();
        }
    }

    public Response updateAccount(String email, BigDecimal balance){
        Account account = AccountDAO.update(email, balance);
        if (account != null){
            return Response.status(Response.Status.OK).entity(account).build();}
        else {
            return Response.status(Response.Status.OK).entity(convertToJSON("Account doesn't exists")).build();
        }
    }

    public Response transfer(String from, BigDecimal amount, String to){
        List<Account> accounts = AccountDAO.transferMoney(from, amount, to);
        if (accounts == null){
            return Response.status(Response.Status.OK).entity(convertToJSON("Account/s doesn't exists")).build();
        }
        else if (accounts.size() == 0) {
            return Response.status(Response.Status.OK).entity(convertToJSON("Account ["+from+"] has not enough balance to transfer " + amount)).build();
        } else {
            return Response.status(Response.Status.OK).entity(accounts).build();
        }
    }

    public Response deleteAccount(String email){
        boolean hasBeenDeleted = AccountDAO.delete(email);
        if (hasBeenDeleted){
            return Response.status(Response.Status.OK).entity(convertToJSON("Account ["+email+"] has been deleted")).build();
        }
        else {
            return Response.status(Response.Status.OK).entity(convertToJSON("Account ["+email+"] does not exist and can't be deleted")).build();
        }
    }

    public Response getAccount(String email){
        Account account = AccountDAO.get(email);
        if (account != null){
            return Response.status(Response.Status.OK).entity(account).build();
        }
        else {
            return Response.status(Response.Status.OK).entity(convertToJSON("Account ["+email+"] does not exist")).build();
        }
    }

    public Response listAccounts(){
        List<Account> accounts = AccountDAO.listUsers();
        if (accounts.size() > 0){
            return Response.status(Response.Status.OK).entity(accounts).build();
        }
        else {
            return Response.status(Response.Status.OK).entity(convertToJSON("We have 0 accounts.")).build();
        }
    }

    private JSONObject convertToJSON(String string){
            JSONObject obj = new JSONObject();
            obj.put("message", string);
        return obj;
    }


}

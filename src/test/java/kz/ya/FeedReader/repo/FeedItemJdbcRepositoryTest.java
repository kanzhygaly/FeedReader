/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.ya.FeedReader.repo;

import java.util.List;
import java.util.Optional;
import kz.ya.FeedReader.model.FeedItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author yerlana
 */
@RunWith(SpringRunner.class)
public class FeedItemJdbcRepositoryTest {
    
    @Autowired
    private FeedItemRepository repository;

    @Test
    public void shouldGetAccountWhenItExists() {
//        final FeedItem expResult = AccountDao.getInstance().create(BigDecimal.TEN);
//        String number = expResult.getNumber();
//
//        Account result = AccountDao.getInstance().get(number).get();
//
//        Assert.assertEquals(expResult, result);
    }

    @Test
    public void shouldGetEmptyResultWhenAccountDoesNotExist() {
//        String number = "wrongNumber";
//
//        Optional<Account> result = AccountDao.getInstance().get(number);
//
//        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void shouldCreateNewAccount() {
//        final Account account = AccountDao.getInstance().create(BigDecimal.TEN);
//
//        final Account createdAccount = AccountDao.getInstance().get(account.getNumber()).get();
//
//        Assert.assertEquals(createdAccount, account);
//        Assert.assertEquals(createdAccount.getNumber(), account.getNumber());
//        Assert.assertEquals(createdAccount.getBalance(), account.getBalance());
//        Assert.assertEquals(createdAccount.getCreatedAt(), account.getCreatedAt());
//        Assert.assertEquals(createdAccount.getModifiedAt(), account.getModifiedAt());
    }
}

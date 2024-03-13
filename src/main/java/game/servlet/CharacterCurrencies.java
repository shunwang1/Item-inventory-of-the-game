package main.java.game.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.game.model.*;
import main.java.game.dal.*;

@WebServlet("/currencies")
public class CharacterCurrencies  extends HttpServlet {

    protected CharacterCurrencyDao  characterCurrencyDao;
    protected Each_CharacterDao eachCharacterDao;

    @Override
    public void init() throws ServletException {
    	characterCurrencyDao = CharacterCurrencyDao.getInstance();
        eachCharacterDao = Each_CharacterDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
    	int characterId = Integer.parseInt(req.getParameter("characterId"));
        //int currencyId = Integer.parseInt(req.getParameter("currencyId"));
        //Currencies currency = new Currencies(currencyId);
        
        try {
            Each_Character character = eachCharacterDao.getCharacterFromCharacterId(characterId);
            if(character==null){
                messages.put("success", "character does not exist. ");
            }
            int currencyId = character.getCurrencyId();
            Currencies characterCurrency = characterCurrencyDao.getCharacterCurrencyByCharacter_idAndCurrency_id(characterId, currencyId);
            messages.put("success", "characterCurrency does not exist.");
            if(characterCurrency==null){
                messages.put("success", "characterCurrency does not exist.");
            }
            req.setAttribute("characterCurrency", characterCurrency);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        req.getRequestDispatcher("/Currencies.jsp").forward(req, resp);
    }
}
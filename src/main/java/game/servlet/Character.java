package main.java.game.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

@WebServlet("/playercharacter")
public class Character extends HttpServlet {
	
	protected Each_CharacterDao characterDao;
	
	@Override
	public void init() throws ServletException {
		characterDao = Each_CharacterDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        List<Each_Character> characters = new ArrayList<>();
        
        
        String username = req.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Please provide a valid username.");
        } else {
            try {
                // Get characters associated with the provided username.
                characters = characterDao.getCharacterFromPlayerId(username);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying characters for " + username);
        }
        req.setAttribute("playercharacter", characters);
        req.getRequestDispatcher("/Each_Character.jsp").forward(req, resp);
	}
}

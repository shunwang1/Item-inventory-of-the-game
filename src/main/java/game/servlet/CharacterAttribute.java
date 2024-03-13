package main.java.game.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.game.model.*;
import main.java.game.dal.*;
import main.java.game.model.Attribute;

@WebServlet("/attributes")
public class CharacterAttribute extends HttpServlet {
    private AttributeDao attributeDao;

    public CharacterAttribute() {
    	attributeDao = attributeDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int characterId = Integer.parseInt(req.getParameter("characterId"));
        Attribute characterAttribute = null;
        try {
        	characterAttribute = attributeDao.getAttributeByCharacterId(characterId);
            req.setAttribute("characterAttribute", characterAttribute);
            req.getRequestDispatcher("/Attribute.jsp").forward(req, resp);
        } catch (SQLException | ServletException e) {
            ((Throwable) e).printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
package main.java.game.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.game.model.*;
import main.java.game.dal.*;

@WebServlet("/equippedgear")
public class EquippedGear extends HttpServlet {

    protected EquippedGearDao equippedGearDao;

    @Override
    public void init() throws ServletException {
        equippedGearDao = EquippedGearDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int characterId = Integer.parseInt(req.getParameter("characterId"));
        main.java.game.model.EquippedGear equippedGear;
        try {
            // Get the equipped gear information 需要补充方法
            equippedGear = equippedGearDao.getEquippedGearByEquippedGear_id(characterId);
            req.setAttribute("equippedGear", equippedGear);
            req.getRequestDispatcher("/EquippedGear.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
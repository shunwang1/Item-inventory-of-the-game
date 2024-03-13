package main.java.game.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.game.model.*;
import main.java.game.dal.*;

@WebServlet("/inventorydetails")
public class InventoryDetails extends HttpServlet {
    
    protected SlotDao slotDao;
    protected InventoryDao inventoryDao;

    
    @Override
    public void init() throws ServletException {
        slotDao = SlotDao.getInstance();
        inventoryDao = InventoryDao.getInstance();

    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Slot> slots = new ArrayList<>();
        int characterId = Integer.parseInt(req.getParameter("characterId")); 
        try {
        	Inventory inventory = inventoryDao.getInventoryByCharacterId(characterId);
            
            if (inventory != null) {
                slots = slotDao.getSlotsByInventoryId(inventory.getInventory_id());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        
        req.setAttribute("slots", slots);
        req.getRequestDispatcher("/InventoryDetails.jsp").forward(req, resp);
    }
}
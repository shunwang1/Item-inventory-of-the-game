package main.java.game.servlet;

import main.java.game.model.*;
import main.java.game.dal.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/playerdelete")
public class DeletePlayers extends HttpServlet{
    protected PlayerDao playerDao;

    @Override
    public void init() throws ServletException{
        playerDao = PlayerDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Player");       
        req.getRequestDispatcher("/DeletePlayers.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String playerName = req.getParameter("username");
        if (playerName == null || playerName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
            // Retrieve Player, and store as a message.
            try {

                Player thisPlayer = playerDao.getPlayerByUserName(playerName);
                if(thisPlayer==null){
                    messages.put("success", "UserName does not exist. No delete to perform.");
                }
                else {
                    playerDao.delete(thisPlayer);
                    if (thisPlayer == null) {
    		            messages.put("title", "Successfully deleted " + playerName);
    		            messages.put("disableSubmit", "true");
    		        } else {
    		        	messages.put("title", "Failed to delete " + playerName);
    		        	messages.put("disableSubmit", "false");
    		        }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/DeletePlayers.jsp").forward(req, resp);
    }
}

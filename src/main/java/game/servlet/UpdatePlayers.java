package main.java.game.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.game.model.*;
import main.java.game.dal.*;
@WebServlet("/playerupdate")
public class UpdatePlayers extends HttpServlet{
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

        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "username not valid");
        } else {
            // Retrieve Player, and store as a message.
            try {
                Player thisPlayer = playerDao.getPlayerByUserName(req.getParameter("username"));
                String playername = req.getParameter("playername")!=null?req.getParameter("playername"):thisPlayer.getPlayerName();
                String playeremail = req.getParameter("playeremail")!=null?req.getParameter("playeremail"):thisPlayer.getEmail();
                String playeraddress = req.getParameter("playeraddress")!=null?req.getParameter("playeraddress"):thisPlayer.getAddress();
                String playerphone = req.getParameter("playerphone")!=null?req.getParameter("playerphone"):thisPlayer.getPhone();
                boolean status = "true".equals(req.getParameter("playerstatus"));
                playerDao.updatePlayer(thisPlayer,playername,playeremail,playeraddress,playerphone,status);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/UpdatePlayer.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String playerName = req.getParameter("playername");
        if (playerName == null || playerName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
            // Retrieve Player, and store as a message.
            try {

                Player thisPlayer = playerDao.getPlayerByUserName(req.getParameter("username"));
                if(thisPlayer==null){
                    messages.put("success", "UserName does not exist. No update to perform.");
                }
                else {
                    String playername = req.getParameter("playername")!=null?req.getParameter("playername"):thisPlayer.getPlayerName();
                    String playeremail = req.getParameter("playeremail")!=null?req.getParameter("playeremail"):thisPlayer.getEmail();
                    String playeraddress = req.getParameter("playeraddress")!=null?req.getParameter("playeraddress"):thisPlayer.getAddress();
                    String playerphone = req.getParameter("playerphone")!=null?req.getParameter("playerphone"):thisPlayer.getPhone();
                    boolean status = "true".equals(req.getParameter("playerstatus"));
                    playerDao.updatePlayer(thisPlayer,playername,playeremail,playeraddress,playerphone,status);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/UpdatePlayer.jsp").forward(req, resp);
    }
}

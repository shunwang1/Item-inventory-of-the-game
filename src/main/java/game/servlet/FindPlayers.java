package main.java.game.servlet;

import main.java.game.model.*;
import main.java.game.dal.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * FindPlayers is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findplayers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/findplayers.
 */
@WebServlet("/findplayers")
public class FindPlayers extends HttpServlet {
	
	protected PlayerDao playerDao;
	
	@Override
	public void init() throws ServletException {
		playerDao = PlayerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Player> player = new ArrayList<Player>();
        
        // Retrieve and validate name.
        // playername is retrieved from the URL query string.
        String playerName = req.getParameter("playername");
        if (playerName == null || playerName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Player, and store as a message.
        	try {
            	player = playerDao.getPlayerFromPlayerName(playerName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + playerName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindPlayers.jsp.
        	messages.put("previousPlayerName", playerName);
        }
        req.setAttribute("blogUsers", player);
        
        req.getRequestDispatcher("/FindPlayers.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Player> player = new ArrayList<Player>();
        
        // Retrieve and validate name.
        // playername is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindPlayers.jsp).
        String playerName = req.getParameter("playername");
        if (playerName == null || playerName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	player = playerDao.getPlayerFromPlayerName(playerName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + playerName);
        }
        req.setAttribute("players", player);
        
        req.getRequestDispatcher("/FindPlayers.jsp").forward(req, resp);
    }
}
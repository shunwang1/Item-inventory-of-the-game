package main.java.game.servlet;

import main.java.game.model.*;
import main.java.game.dal.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UserCreate")
public class PlayerCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("UserName");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Create the Player.
        	String player_name = req.getParameter("player_name");
        	String email = req.getParameter("email");
        	String address = req.getParameter("address");
        	String phone = req.getParameter("phone");
        	// Get the parameter value as a String
        	String is_active_str = req.getParameter("is_active");
        	// Initialize a Boolean variable
        	Boolean is_active = false;

        	try {
        	    // Check if the parameter is not null and not empty before parsing
        	    if (is_active_str != null && !is_active_str.isEmpty()) {
        	        // Parse the String into a Boolean
        	        is_active = Boolean.parseBoolean(is_active_str);
        	    }

        	    // Now, 'is_active' will either be a Boolean value or null

        	    // For example, assuming BlogUsers has a field 'isActive' of type Boolean
        	    Player player = new Player (userName, player_name, email, address, phone, is_active);

        	    try {
        	        // Assuming playerDao.create method may throw SQLException
        	    	playerDao.create(player);
        	        messages.put("success", "Successfully created " + userName);
        	    } catch (SQLException e) {
        	        e.printStackTrace();
        	        throw new IOException(e);
        	    }

        	} catch (Exception e) {
        	    // Handle any unexpected exception
        	    e.printStackTrace();
        	    throw new IOException(e);
        	}

        	req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
        	
        	}
       	}
	}

        	
        	
        	
        	
        	
        


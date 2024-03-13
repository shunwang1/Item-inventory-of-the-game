package main.java.game.servlet;
import java.io.IOException;
import java.sql.SQLException;
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

@WebServlet("/job")
public class CharacterJob extends HttpServlet {
    private final Job_of_CharacterDao characterJobDao;
    protected Each_CharacterDao eachCharacterDao;
    public CharacterJob() {
        characterJobDao = Job_of_CharacterDao.getInstance();
        eachCharacterDao = Each_CharacterDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        int characterId = Integer.parseInt(req.getParameter("characterId"));
        Job_of_Character characterJobs;
        try {
            Each_Character character = eachCharacterDao.getCharacterFromCharacterId(characterId);
            int jobId = character.getJobId();
            characterJobs = characterJobDao.getJob_of_CharacterByJobAndCharacter(jobId, character.getCharacterId());
            if(characterJobs==null){
                messages.put("success", "Invalid character");
            }
            req.setAttribute("characterJobs", characterJobs);
            req.getRequestDispatcher("/Job.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
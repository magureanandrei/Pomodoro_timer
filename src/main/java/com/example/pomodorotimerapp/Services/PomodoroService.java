package com.example.pomodorotimerapp.Services;

import com.example.pomodorotimerapp.Repos.SessionRepo;
import com.example.pomodorotimerapp.Repos.UserRepo;
import com.example.pomodorotimerapp.models.PomodoroSession;
import com.example.pomodorotimerapp.models.SessionStatus;
import com.example.pomodorotimerapp.models.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PomodoroService {
    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    public PomodoroService(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @Transactional
    public PomodoroSession startPomodoroSession(Integer workDuration, Integer breakDuration) {
        PomodoroSession session = new PomodoroSession();
        session.setWorkDuration(workDuration);
        session.setBreakDuration(breakDuration);
        session.setStatus(SessionStatus.RUNNING);

        return sessionService.createSession(session);
    }

    @Transactional
    public void pauseSession(Long sessionId) {
        PomodoroSession session = sessionService.getSessionById(sessionId);
        if (session == null) {
            throw new RuntimeException("Session not found");
        }

        if (session.getStatus() != SessionStatus.RUNNING) {
            throw new RuntimeException("Session is not running");
        }

        session.setStatus(SessionStatus.PAUSED);
        sessionService.updateSession(session);
    }

    public List<PomodoroSession> getUserSessions(Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        List<PomodoroSession> sessions = sessionService.getSessionsByUser(userId);
        return sessions;
    }


}

package com.example.pomodorotimerapp.Repos;

import com.example.pomodorotimerapp.models.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepo extends JpaRepository<UserSettings, Long> {
    UserSettings findTopByOrderByIdDesc();
}

package com.example.pomodorotimerapp.Services;

import com.example.pomodorotimerapp.Repos.SettingsRepo;
import com.example.pomodorotimerapp.models.UserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {
    private final SettingsRepo settingsRepo;

    @Autowired
    public SettingsService(SettingsRepo settingsRepo) {
        this.settingsRepo = settingsRepo;
    }


}

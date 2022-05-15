package com.example.mil_app.repository;

public class GameController {
    private boolean isActive;
    private boolean hasPAF;
    private boolean hasFF;
    private boolean hasATA;

    public GameController() {
        isActive = true;
        hasPAF = true;
        hasFF = true;
        hasATA = true;
    }
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isHasPAF() {
        return hasPAF;
    }

    public void setHasPAF(boolean hasPAF) {
        this.hasPAF = hasPAF;
    }

    public boolean isHasFF() {
        return hasFF;
    }

    public void setHasFF(boolean hasFF) {
        this.hasFF = hasFF;
    }

    public boolean isHasATA() {
        return hasATA;
    }

    public void setHasATA(boolean hasATA) {
        this.hasATA = hasATA;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.frc869.robot.code2014;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author mechinn
 */
public class Lights {
    private static Lights instance;
    public static final int OFF = 0;
    public static final int BLUE = 1;
    public static final int RED = 2;
    public static final int GREEN = 3;
    public static final int PURPLE = 4;
    public static final int YELLOW = 5;
    public static final int TEAL = 6;
    public static final int WHITE = 7;
    public static final int ALLIANCE = -1;
    
    private final Relay count;
    private final Relay pulse;
    private final Relay safety;
    private final Relay victory;
    private final Relay shoot;
    private final Relay red;
    private final Relay green;
    private final Relay blue;
    
    private Lights() {
        safety = new Relay(1);
        victory = new Relay(2);
        shoot = new Relay(3);
        red = new Relay(4);
        green = new Relay(5);
        blue = new Relay(6);
        count = new Relay(7);
        pulse = new Relay(8);
        safety.setDirection(Relay.Direction.kForward);
        victory.setDirection(Relay.Direction.kForward);
        shoot.setDirection(Relay.Direction.kForward);
        red.setDirection(Relay.Direction.kForward);
        green.setDirection(Relay.Direction.kForward);
        blue.setDirection(Relay.Direction.kForward);
        count.setDirection(Relay.Direction.kForward);
        pulse.setDirection(Relay.Direction.kForward);
    }
    public static Lights getInstance() {
        if(null==instance) {
            instance = new Lights();
        }
        return instance;
    }
    public void fire() {
        if(Catapult.getInstance().isFiring()) {
            shoot.set(Relay.Value.kOn);
        } else {
            shoot.set(Relay.Value.kOff);
        }
    }
    public void setColor(int color) {
        switch(color) {
            default:
                if(DriverStation.getInstance().getAlliance()==DriverStation.Alliance.kRed) {
                    red.set(Relay.Value.kOn);
                    green.set(Relay.Value.kOff);
                    blue.set(Relay.Value.kOff);
                } else {//if we are blue or not connected to an FMS make us blue
                    red.set(Relay.Value.kOff);
                    green.set(Relay.Value.kOff);
                    blue.set(Relay.Value.kOn);
                }
                break;
            case BLUE:
                red.set(Relay.Value.kOff);
                green.set(Relay.Value.kOff);
                blue.set(Relay.Value.kOn);
                break;
            case RED:
                red.set(Relay.Value.kOn);
                green.set(Relay.Value.kOff);
                blue.set(Relay.Value.kOff);
                break;
            case GREEN:
                red.set(Relay.Value.kOff);
                green.set(Relay.Value.kOn);
                blue.set(Relay.Value.kOff);
                break;
            case PURPLE:
                red.set(Relay.Value.kOn);
                green.set(Relay.Value.kOff);
                blue.set(Relay.Value.kOn);
                break;
            case YELLOW:
                red.set(Relay.Value.kOn);
                green.set(Relay.Value.kOn);
                blue.set(Relay.Value.kOff);
                break;
            case TEAL:
                red.set(Relay.Value.kOff);
                green.set(Relay.Value.kOn);
                blue.set(Relay.Value.kOn);
                break;
            case WHITE:
                red.set(Relay.Value.kOn);
                green.set(Relay.Value.kOn);
                blue.set(Relay.Value.kOn);
                break;
            case OFF:
                red.set(Relay.Value.kOff);
                green.set(Relay.Value.kOff);
                blue.set(Relay.Value.kOff);
                break;
        }
    }
    public void safety() {
        if(Catapult.getInstance().isSafetyIn()) {
            safety.set(Relay.Value.kOn);
        } else {
            safety.set(Relay.Value.kOff);
        }
    }
    public void victory() {
        if(Logitech.getInstance().getStartButton()) {
            victory.set(Relay.Value.kOn);
        } else {
            victory.set(Relay.Value.kOff);
        }
    }
    public void checkCountdown() {
        if(DriverStation.getInstance().getMatchTime() >= 145) {
            countdown();
        } else {
            count.set(Relay.Value.kOff);
        }
    }
    public void countdown() {
        count.set(Relay.Value.kOn);
        if(Math.floor(DriverStation.getInstance().getMatchTime()) % 2 == 0) {
            pulse.set(Relay.Value.kOn);
        } else {
            pulse.set(Relay.Value.kOff);
        }
    }
}

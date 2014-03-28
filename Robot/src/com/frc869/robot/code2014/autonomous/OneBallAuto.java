/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frc869.robot.code2014.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Kevvers
 */
public class OneBallAuto extends Autonomous {
    public void routine() {
        switch(getMode()) {
            default:
                increaseMode();
                break;
            case 0:
                if(drive(DISTANCE,.5)) {
                    increaseMode();
                }
                break;
            case 1:
                if(getDidlers().moveDidlers(.5)) {
                    increaseMode();
                }
                break;
            case 2:
                if(SmartDashboard.getBoolean("hot") || DriverStation.getInstance().getMatchTime() >= 8.5){
                    increaseMode();
                }
                break;
            case 3:
                if(getCatapult().fire()) {
                    increaseMode();
                }
                break;
        }
    }
}
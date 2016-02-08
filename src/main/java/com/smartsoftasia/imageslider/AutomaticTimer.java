package com.smartsoftasia.imageslider;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nott on 2/3/2016 AD.
 * AutomaticTimer
 */
public class AutomaticTimer {
  Timer timer;

  public AutomaticTimer(int seconds) {
    timer = new Timer();
    timer.schedule(new RemindTask(), seconds * 1000);
  }

  class RemindTask extends TimerTask {
    public void run() {
      System.exit(0);
    }
  }
}


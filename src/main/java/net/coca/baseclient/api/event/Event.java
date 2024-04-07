package net.coca.baseclient.api.event;

import java.util.ArrayList;

public class Event {
  public Event call() {
    ArrayList<EventData> dataList = EventManager.get(getClass());
    if (dataList != null)
      for (EventData data : dataList) {
        try {
          data.target.invoke(data.source, new Object[] { this });
        } catch (Exception e) {
          e.printStackTrace();
        } 
      }  
    return this;
  }
}

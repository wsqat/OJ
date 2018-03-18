package pd.ObserveTest;

import java.util.Observable;
import java.util.Observer;

public class AObserver implements Observer{

    public AObserver(ServerManager sm){
        super();
        sm.addObserver(this);//注册加入观察者
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("AObserver receive:Data has changed to "+((ServerManager) o).getData());
    }
}

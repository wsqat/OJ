package pd.ObserveTest;

import java.util.Observable;

public class ServerManager extends Observable{
    private int data = 0;

    public int getData(){
        return data;
    }

    public void setData(int i){
        if (this.data != i){
            this.data = i;
            setChanged();
        }
        //ֻ����setChange()�����ú�notifyObservers()�Ż�ȥ����update()������ʲô�����ɡ�
        notifyObservers();

    }
}

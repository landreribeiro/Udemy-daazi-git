package maddo.foo.poopolimorfirmouniversalsubtipagem;

import android.util.Log;

public class Aviao extends Transporte {

    public void tipo() {
        super.tipo();
        Log.i("Polimorfismo", "Todo AVIÃO transporta voando pelos ares!");
    }

}

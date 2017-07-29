package concurrencia;

import java.util.Map;
import java.util.HashMap;

public class WordFrequencies {

    private Map<String, Integer> palabras = new HashMap<>();

    public void addFrequencies(Map<String, Integer> f) {
        Integer count;
        for (Map.Entry<String, Integer> mapa : f.entrySet()) {
            count = palabras.get(mapa.getKey());
            String aux = mapa.getKey();
            if(palabras.containsKey(aux)){
                count += mapa.getValue();
                palabras.put(mapa.getKey(), count);
            }else{
                palabras.put(mapa.getKey(), mapa.getValue());
            }
           /** if (count == null) {
                palabras.put(mapa.getKey(), mapa.getValue());
            } else {
                count += mapa.getValue();
                palabras.put(mapa.getKey(), count);
            }*/
        }
    }

    public Map<String, Integer> getFrequencies() {
        return palabras;
    }
}

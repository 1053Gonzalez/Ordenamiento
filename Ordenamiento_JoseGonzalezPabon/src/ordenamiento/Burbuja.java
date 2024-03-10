package ordenamiento;

public class Burbuja {

    private int[] array;

    public Burbuja(int[] array) {
        this.array = array;

    }
    
    //logica para ordenar el arreglo
    public void ordenar() {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
    //retornamos el arreglo
    public int[] getArray() {
        return array;
    }
}
